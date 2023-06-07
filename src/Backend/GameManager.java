package Backend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Timer;

import resources.OurColors;
import resources.Titles;

public class GameManager {
    private boolean firstRound;
    private GameState state;

    // shouldn't change order to preserve maintenance in other classes
    private ArrayList<Player> players;

    private Deck deck;
    //private Kingdomino game;
    private boolean isFastMode = false;
    private int numGames = 1;
    private int numGamesLeft = 1;
    private int roundNum;
    private static final int delayMillis = 400;
    private ArrayList<Integer> winners = new ArrayList<>(4);
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
        STRATEGY,
        ANALYSIS_PANEL
    }


    public GameManager() {
        //this.game = game;
        state = GameState.INITIAL;
        roundNum = 0;
        //making default players:
        players = new ArrayList<>(4);
        players.add(new HumanPlayer(OurColors.RED, "Player 1", Titles.generateTitle(), this));
        //players.add(new SkilledStrategy(OurColors.RED, this));

        players.add(new SkilledStrategy(OurColors.BLUE, this));
        players.add(new SkilledStrategy(OurColors.GREEN, this));
        players.add(new SkilledStrategy(OurColors.YELLOW, this));

        for (int i = 0; i < players.size(); i++) {
            winners.add(0);
        }
        reset();
    }

    private void reset() {
        deck = new Deck();
        //players = new ArrayList<Player>();
        firstRound = true;
        currPlayerIdx = 0;
        roundNum=0;
        playerOrder = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        for (Player p : players)
            p.reset();
//        System.out.println("Reset!");
    }

    public void setGameState(GameState state) {
        this.state = state;
        ArrayList<GameEventListener> listenersCopy = new ArrayList<>(listeners);
        for (GameEventListener gl : listenersCopy) {
            gl.onStateChangedTo(state);
        }
        if (state == GameState.INITIAL) {
            reset();
        } else if (state == GameState.PLAYER_TURN) {
            round();
        } else if (state == GameState.STRATEGY) {
            if(!isFastMode)
                round();
            else {
                while(numGamesLeft > 0) {
                    reset();
                    round();
                    //System.out.println("Loop!");
                }
            }
            setGameState(GameState.ANALYSIS_PANEL);
        } else if (state == GameState.ENDSCREEN) {
            setResults();
        }

    }

    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }

    private void round() {
        //System.out.println("Round " +roundNum);
        deck.getNewDominoes();
        if (firstRound) {
            for (GameEventListener gl : listeners) {
                gl.initDominoes();
            }
        }
        //two checks here
        if (roundNum == 13) {
            endGame();
            //System.out.println("ended");
        } else {
//            System.out.println("round "+roundNum);
            roundNum++;
            if (/*firstRound &&*/ numGamesLeft > 0) {
                playerTurn();
                //System.out.println("Finished Turn!");
            }
        }
    }


    // individual player in a turn
    private void playerTurn() {
        // check if there are any possible moves left
        boolean canMove = !firstRound & getCurrentPlayer().hasLegalMoves(false);
        if (!canMove) {
            getCurrentPlayer().setPlaced(true);
            if (isComputerPlayer()) {
                computerPlayerTurn(false);
            }
        } else if (isComputerPlayer()) {
            computerPlayerTurn(true);
        }
    }

    private boolean isComputerPlayer() {
        return getCurrentPlayer() instanceof ComputerPlayer;
    }

    private void computerPlayerTurn(boolean canPlace) {
        if (isFastMode) {
            if (!firstRound && canPlace) {
                ((ComputerPlayer) getCurrentPlayer()).placeDomino(getDeck().getDominoesToSelect(), getPlayers());
                getCurrentPlayer().setPlaced(true);
            }

            // Pretend it has been placed
            if (!canPlace)
                getCurrentPlayer().setPlaced(true);

            if (getDeck().getDominoesToSelect().length != 0) {
                ((ComputerPlayer) getCurrentPlayer()).calculateChoice(getDeck().getDominoesToSelect(), getPlayers());
                for (GameEventListener gl : listeners) {
                    gl.onDominoSelected(getCurrentPlayer().getNextDomino(), false);
                }
                getDeck().setLocked(getCurrentPlayer().getNextDomino(), getCurrentPlayer());
            }
            getCurrentPlayer().setSelected(true);
            for (GameEventListener gl : listeners) {
                gl.onFinishTurn();
            }
            //System.out.println(getCurrentPlayer());
            if(numGamesLeft > 0)
                nextPlayer();
        } else {
            computerPlaceDomino(canPlace);
        }

    }

    private void computerPlaceDomino(final boolean canPlace) {
        final Timer timer = new Timer(1, null);
        timer.addActionListener(new ActionListener() {
            long currentTime = System.currentTimeMillis();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentTime + delayMillis < System.currentTimeMillis()) {
                    if (!firstRound && canPlace) {
                        ((ComputerPlayer) getCurrentPlayer()).placeDomino(getDeck().getDominoesToSelect(), getPlayers());
                        getCurrentPlayer().setPlaced(true);
                    }
                    if (!canPlace)
                        getCurrentPlayer().setPlaced(true);
                    computerChooseDomino();
                    timer.stop();
                }

            }
        });
        timer.start();
    }

    private void computerChooseDomino() {
        final Timer timer = new Timer(1, null);
        timer.addActionListener(new ActionListener() {
            long currentTime = System.currentTimeMillis();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentTime + delayMillis < System.currentTimeMillis()) {
                    if (getDeck().getDominoesToSelect().length != 0) {
                        ((ComputerPlayer) getCurrentPlayer()).calculateChoice(getDeck().getDominoesToSelect(), getPlayers());
                        for (GameEventListener gl : listeners) {
                            if (getCurrentPlayer().getNextDomino() != null) {
                                gl.onDominoSelected(getCurrentPlayer().getNextDomino(), false);
                                getDeck().setLocked(getCurrentPlayer().getNextDomino(), getCurrentPlayer());
                            }
                        }
                    }
                    getCurrentPlayer().setSelected(true);
                    computerFinishTurn();
                    timer.stop();
                }

            }
        });
        timer.start();
    }

    private void computerFinishTurn() {
        final Timer timer = new Timer(1, null);
        timer.addActionListener(new ActionListener() {
            long currentTime = System.currentTimeMillis();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentTime + delayMillis < System.currentTimeMillis()) {
                    for (GameEventListener gl : listeners) {
                        gl.onFinishTurn();
                    }
                    if(numGamesLeft > 0)
                        nextPlayer();
                    timer.stop();
                }

            }
        });
        timer.start();
    }

    // called when player finishes turn
    // updates player and calls playerTurn()
    public void nextPlayer() {
        if (!getCurrentPlayer().hasSelected() && !(firstRound || getCurrentPlayer().hasPlaced())) {
            return;
        }
        getCurrentPlayer().setSelected(false);
        getCurrentPlayer().setPlaced(false);
        updatePlayerIdx();

        // new round (back to first player)
        if (currPlayerIdx == 0) {
            // next round
            firstRound = false;
            updatePlayerOrder();
            for (GameEventListener gl : listeners) {
                gl.onNextPlayer();
            }
            round();
        } else {
            //notify listeners last
            for (GameEventListener gl : listeners) {
                gl.onNextPlayer();
            }
            if(roundNum<=13)
                playerTurn();
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
            if(player.getNextDomino() != null)
                dominoValues.add(player.getNextDomino().getValue());
            else
                dominoValues.add(0);
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


        setResults();
        //last # of playerOrder won game = player #
        int highScore = 0;
        int winner = -1;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getScore() >= highScore) {
                highScore = players.get(i).getScore();
                //System.out.println(players.get(i));
                winner = getPlayerOrder().get(i);
            }
        }
        //System.out.println(getPlayerOrder());
        //System.out.println(winner);
        Integer num = winners.get(winner) + 1;
        if (winner != -1) {
            winners.set(winner, num);
        }

        //System.out.println(winners);

        /*if (!isFastMode()) {
            setGameState(GameState.ENDSCREEN);
        } else {*/
        numGamesLeft--;
        if (numGamesLeft > 0) {
            roundNum=0;
            if(!isFastMode) {
                reset();
                round();
            }
        } else {
            //System.out.println("Done games");
            boolean hasHuman = false;
            for(Player p : getPlayers()) 
                if(p instanceof HumanPlayer)
                    hasHuman = true;
            if(!hasHuman) {
                setGameState(GameState.ANALYSIS_PANEL);
            } else {
                setGameState(GameState.ENDSCREEN);
            }
        }
        //}

//        numGamesLeft--;
//        if (isFastMode && numGamesLeft == ) {
//
//        }
//        if (isFastMode) {
//            game.getAnalysisPanel().displayAnalysis();
//        }
    }

    public boolean isFirstRound() {
        return firstRound;
    }

    public void setMode(boolean fastMode) {
        this.isFastMode = fastMode;
    }

    public boolean isFastMode() {
        return isFastMode;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Integer> getWinners() {
        return winners;
    }

    public void setPlayers(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            winners.set(i, 0);
        }
        for (Player p : players) {

        }
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
        //System.out.println(numGames);
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