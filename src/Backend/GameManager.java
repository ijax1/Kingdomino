package Backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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
    private Domino[] dominoesToSelect;
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
        dominoesToSelect = deck.getDominoesToSelect();
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


    public Domino[] getDominoesToSelect() {
        return dominoesToSelect;
    }

    private void round() {
        if (roundNum == 24) {
//            endGame();
        } else {
            roundNum++;
        }
        dominoesToSelect = deck.getDominoesToSelect();
        if (firstRound)
            game.getGamePanel().initDominoes();
        playerTurn();
    }


    // individual player in a turn
    private void playerTurn() {
        if (getCurrentPlayer() instanceof ComputerPlayer) {
            //instead of computer player...
            //if we store what strategy is selected...idk. lol.

            ((ComputerPlayer) getCurrentPlayer()).calculateChoice();
            ((ComputerPlayer) getCurrentPlayer()).placeDomino();
            nextPlayer();
        }
    }

    public void finishTurn() {
        if ((firstRound || getCurrentPlayer().hasPlaced()) && getCurrentPlayer().hasSelected()) {
            if (currPlayerIdx == players.size()-1) {
                game.getGamePanel().changePlayer(getCurrentPlayer());
            }
        }
    }

    // called when player finishes turn
    // updates player and calls playerTurn()
    public void nextPlayer() {
        if ((firstRound || getCurrentPlayer().hasPlaced()) && getCurrentPlayer().hasSelected()) {
            getCurrentPlayer().setSelected(false);
            getCurrentPlayer().setPlaced(false);
            updatePlayerIdx();
            if (currPlayerIdx == 0) {
                // next round
                firstRound = false;
//                updatePlayerOrder();
                game.getGamePanel().changePlayer(getCurrentPlayer());
                round();
            } else {
                game.getGamePanel().changePlayer(getCurrentPlayer());
                playerTurn();
            }
        }
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
        for (int i = 0; i < dominoValues.size() - 1; i++) {
            for (int j = 0; j < dominoValues.size() - 1 - i; j++) {
                if (dominoValues.get(j) < dominoValues.get(j + 1)) {
                    int temp = playerOrder.get(j);
                    playerOrder.set(j, playerOrder.get(j + 1));
                    playerOrder.set(j + 1, temp);
                }
            }
        }
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