package Backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import UIComponents.DominoButton;
import UIComponents.UIGrid;
import resources.OurColors;
import resources.Titles;

public class GameManager {
    private boolean firstRound;
    private GameState state;

    // shouldn't change order to preserve maintenance in other classes
    private ArrayList<Player> players;

    private Deck deck;
    //private Kingdomino game;
    private boolean isFastMode;
    private int numGames;
    private int numGamesLeft;
    private ArrayList<Domino> availableDominoes;
    private int roundNum;
    private boolean noMovePossible;
    private ArrayList<Integer> winners;
    // will contain integers 0,1,2,3 representing players, ordered in their desired order
    // ex: {3,0,2,1} = player 3 --> player 0 --> player 2 --> player 1
    private ArrayList<Integer> playerOrder;

    private ArrayList<GameEventListener> listeners = new ArrayList<>();

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


    public GameManager() {
        //this.game = game;
        state = GameState.INITIAL;
        roundNum = 0;
        //making default players:
        Titles t = new Titles();
        players = new ArrayList<>(4);
        players.add(new HumanPlayer(OurColors.RED, "Player 1", Titles.generateTitle(), this));
        //players.add(new SkilledStrategy(OurColors.RED, this));

        players.add(new SkilledStrategy(OurColors.BLUE, this));
        players.add(new SkilledStrategy(OurColors.GREEN, this));
        players.add(new SkilledStrategy(OurColors.YELLOW, this));
        winners = new ArrayList<>();
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
        if (state == GameState.INITIAL) {
            reset();
        } else if (state == GameState.PLAYER_TURN) {
            initPlayerTurns();
        } else if (state == GameState.STRATEGY) {
            //play computer # games
            initPlayerTurns();
        } else if (state == GameState.ENDSCREEN) {
            setResults();
        }
        //notify listeners last
        for (GameEventListener gl : listeners) {
            gl.onStateChangedTo(state);
        }
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

    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }

    private void round() {
        // as nextPlayer() is called for each player, noMovePossible is set to false if player can place the domino
        // if no player can place the domino, noMovePossible remains true --> endGame().
        noMovePossible = true;
        Domino[] d = deck.getNewDominoes();
        //two checks here
        if (d == null || roundNum == 24) {
            endGame();
        } else {
            roundNum++;
        }
        if (firstRound) {
            playerTurn();
        }
    }


    // individual player in a turn
    private void playerTurn() {
        // check if there are any possible moves left
        boolean currNoMovePossible = true;
        if (!firstRound) {
            if (getCurrentPlayer().hasLegalMoves(false)) {
                currNoMovePossible = false;
                noMovePossible = false;
            }
        } else {
            currNoMovePossible = false;
        }
        if (currNoMovePossible) {
            if (noMovePossible && currPlayerIdx == players.size() - 1) {
                endGame();
            } else {
//                getCurrentPlayer().setPlaced(true);
//                getCurrentPlayer().setSelected(true);
//                nextPlayer();
                if (!(getCurrentPlayer() instanceof ComputerPlayer)) {
                    getCurrentPlayer().setPlaced(true);
                } else {
                    computerPlayerTurn(false);
                }
            }
        } else {
            computerPlayerTurn(true);
        }
    }

    private void computerPlayerTurn(boolean canPlace) {
        if (getCurrentPlayer() instanceof ComputerPlayer) {
            if (!firstRound && canPlace) {
                ((ComputerPlayer) getCurrentPlayer()).placeDomino(getDeck().getDominoesToSelect(), getPlayers());
                // TODO: update gamepanel
//                for (GameEventListener gl : listeners) {
//                    gl.onDominoSelected();
//                }
                getCurrentPlayer().setPlaced(true);
            }
            if (!canPlace)
                getCurrentPlayer().setPlaced(true);
            if (getDeck().getDominoesToSelect().length != 0) {
                ((ComputerPlayer) getCurrentPlayer()).calculateChoice(getDeck().getDominoesToSelect(), getPlayers());
                for (GameEventListener gl : listeners) {
                    gl.onDominoSelected(getCurrentPlayer().getNextDomino(), false);
                }
            }
            getCurrentPlayer().setSelected(true);
            for (GameEventListener gl : listeners) {
                gl.onFinishTurn();
            }
            nextPlayer();
//            for (GameEventListener gl : listeners) {
//                gl.onNextPlayer();
//            }
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
        updatePlayerIdx();

        if (currPlayerIdx == 0) {
            // next round
            firstRound = false;
            updatePlayerOrder();
            round();
        }
        //notify listeners last
        for (GameEventListener gl : listeners) {
            gl.onNextPlayer();
        }
        System.out.println(getCurrentPlayer());
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

    public void endGame() {
        for (Player player : players) {
            player.setCurrentDomino(null);
            player.setNextDomino(null);
            player.setPlaced(true);
        }

    	System.out.println("game over");
    	
    	setResults();
    			//last # of playerOrder won game = player #
    	Integer winner = getPlayerOrder().get(players.size()-1);
    	winners.add(winner);
    	        
    	
        setGameState(GameState.TALLY_SCORE);
        
//        numGamesLeft--;
//        if (isFastMode && numGamesLeft == ) {
//
//        }
//        if (isFastMode) {
//            game.getAnalysisPanel().displayAnalysis();
//        }
    }


    private void slowMode() {
        for (int i = 0; i < players.size(); i++) {
            playerTurn();
            /*
            currPlayerIdx = i;
            Player currentPlayer = players.get(currPlayerIdx);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception ignored) {

            }
            ((ComputerPlayer) currentPlayer).calculateChoice(getDeck().getDominoesToSelect(), getPlayers());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception ignored) {

            }
            ((ComputerPlayer) currentPlayer).placeDomino(getDeck().getDominoesToSelect(), getPlayers());
            currentPlayer.setSelected(false);
            currentPlayer.setPlaced(false);
             */
        }
        //updatePlayerOrder();
        firstRound = false;
    }


    public boolean isFirstRound() {
        return firstRound;
    }

    public void setMode(boolean fastMode) {
        this.isFastMode = fastMode;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    public ArrayList<Integer> getWinners() {
        return winners;
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

    public ArrayList<GameEventListener> getListeners() {
        return listeners;
    }


}