package Backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import UIComponents.UIGrid;
import resources.OurColors;
import resources.Titles;

public class GameManager {
    private boolean firstRound;
    private GameState state;

    // shouldn't change order to preserve maintenance in other classes
    private ArrayList<Player> players;

    private Deck deck;
    private Kingdomino game;
    private boolean isFastMode;
    private int numGames;
    private int numGamesLeft;
    private Domino[] currDominoes;
    private ArrayList<Domino> availableDominoes;
    private int roundNum;

    // will contain integers 0,1,2,3 representing players, ordered in their desired order
    // ex: {3,0,2,1} = player 3 --> player 0 --> player 2 --> player 1
    private ArrayList<Integer> playerOrder;

    // this is index for playerOrder
    private int currPlayerIdx;

    public enum GameState {
        INITIAL,
        PLAYER_TURN,
        END_ROUND,
        TALLY_SCORE,
        ENDSCREEN,
        STRATEGY
    }


    public GameManager(Kingdomino game) {
        this.game = game;
        state = GameState.INITIAL;
        roundNum = 0;
        //making default players:
        Titles t = new Titles();
        players = new ArrayList<>(4);
        players.add(new HumanPlayer(OurColors.RED, "Player 1", t.generateTitle(), this));
        players.add(new SkilledStrategy(OurColors.BLUE, "Player 2", t.generateTitle(), this));
        players.add(new SkilledStrategy(OurColors.GREEN, "Player 3", t.generateTitle(), this));
        players.add(new SkilledStrategy(OurColors.YELLOW, "Player 4", t.generateTitle(), this));
        reset();
    }

    private void reset() {
        firstRound = true;
        deck = new Deck();
        //players = new ArrayList<Player>();
        currPlayerIdx = 0;
        playerOrder = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
    }

    public void setGameState(GameState state) {
        this.state = state;
        if (game != null) {
            game.changePanel(state);
        }
        if (state == GameState.INITIAL) {
            reset();
        } else if (state == GameState.PLAYER_TURN) {
            initPlayerTurns();
        } else if (state == GameState.STRATEGY) {
            //play computer # games
            initPlayerTurns();
        } else if (state == GameState.ENDSCREEN)
            setResults();
    }

    private void initPlayerTurns() {
        boolean strategyMode = true;
        for (Player p : players) {
            if (!(p instanceof ComputerPlayer)) {
                strategyMode = false;
                break;
            }
        }
        if (!strategyMode || isFastMode)
            round();
        else
            slowMode();
    }

    private void slowMode() {
        currDominoes = deck.getDominoesToSelect();
        for (int i = 0; i < players.size(); i++) {
            currPlayerIdx = i;
            Player currentPlayer = players.get(currPlayerIdx);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception ignored) {

            }
            ((ComputerPlayer) currentPlayer).calculateChoice();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception ignored) {

            }
            ((ComputerPlayer) currentPlayer).placeDomino();
            currentPlayer.setSelected(false);
            currentPlayer.setPlaced(false);
        }
        updatePlayerOrder();
        firstRound = false;
    }


    public Domino[] getCurrDominoes() {
        return currDominoes;
    }

    private void round() {
        if (roundNum == 24) {
//            endGame();
        } else {
            roundNum++;
        }
        currDominoes = deck.getDominoesToSelect();
        if (firstRound) {
            game.getGamePanel().initDominoes();
            playerTurn();
        }
    }


    // individual player in a turn
    private void playerTurn() {
        if (getCurrentPlayer() instanceof ComputerPlayer) {
            if (!firstRound) {
                ((ComputerPlayer) getCurrentPlayer()).placeDomino();
                getCurrentPlayer().setPlaced(true);
            }
            ((ComputerPlayer) getCurrentPlayer()).calculateChoice();
            getCurrentPlayer().hasSelected();
            nextPlayer();
        }
    }

    // called when player finishes turn
    // updates player and calls playerTurn()
    public void nextPlayer() {
        if (!getCurrentPlayer().hasSelected() && !(firstRound || getCurrentPlayer().hasPlaced())) {
            System.out.println("cannot progress");
            return;
        }
        getCurrentPlayer().setSelected(false);
        getCurrentPlayer().setPlaced(false);
        game.getGamePanel().finishTurn();
        if (getCurrentPlayer() instanceof HumanPlayer && getCurrentPlayer().getCurrentDomino() != null) {
            UIGrid uiGrid = game.getGamePanel().getUIGrid();
            getCurrentPlayer().getGrid().placeDomino(uiGrid.getDominoLocation()[1], uiGrid.getDominoLocation()[0], getCurrentPlayer().getCurrentDomino());
        }
        updatePlayerIdx();
        if (currPlayerIdx == 0) {
            // next round
            firstRound = false;
            updatePlayerOrder();
            round();
        }
        game.getGamePanel().changePlayer(getCurrentPlayer());
        playerTurn();

    }

    private void updatePlayerIdx() {
        currPlayerIdx++;
        if (currPlayerIdx > players.size() - 1) {
            currPlayerIdx = 0;
        }
    }


    public void updatePlayerOrder() {
        ArrayList<Integer> dominoValues = new ArrayList<>();
        for (Player player : players) {
            dominoValues.add(player.getNextDomino().getValue());
        }
        playerOrder = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        for (int i = 0; i < dominoValues.size() - 1; i++) {
            for (int j = 0; j < dominoValues.size() - 1 - i; j++) {
                if (dominoValues.get(j) > dominoValues.get(j + 1)) {
                    swap(playerOrder, j, j + 1);
                    swap(dominoValues, j, j + 1);
                }
            }
        }
    }

    private void swap(ArrayList<Integer> list, int i1, int i2) {
        int temp = list.get(i1);
        list.set(i1, list.get(i2));
        list.set(i2, temp);
    }

//    public void endGame() {
//        numGamesLeft--;
//        if (isFastMode && numGamesLeft == ) {
//
//        }
//        if (isFastMode) {
//            game.getAnalysisPanel().displayAnalysis();
//        }
//    }

    public boolean isFirstRound() {
        return firstRound;
    }

    public void setMode(boolean fastMode) {
        this.isFastMode = fastMode;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return players.get(playerOrder.get(currPlayerIdx));
    }

    public int getOrigPlayerIdx() {
        return playerOrder.get(currPlayerIdx);
    }

    public boolean isFirstPlayer() {
        return currPlayerIdx == 0;
    }

    public Deck getDeck() {
        return deck;
    }


    public void setResults() {
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = 0; j < players.size() - 1 - i; j++) {
                if (players.get(j).getScore() > players.get(j + 1).getScore()) {
                    Player temp = players.get(j);
                    players.set(j, players.get(j + 1));
                    players.set(j + 1, temp);
                }
            }
        }
    }


    public GameState getGameState() {
        return state;
    }

    public Kingdomino getGame() {
        return game;
    }

    public void setNumGames(int numGames) {
        this.numGames = numGames;
        numGamesLeft = numGames;
    }

    public int getNumGames() {
        return numGames;
    }

    public ArrayList<Integer> getPlayerOrder() {
        return playerOrder;
    }


}