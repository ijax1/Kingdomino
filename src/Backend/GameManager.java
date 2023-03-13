package Backend;

import resources.OurColors;
import resources.Titles;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameManager {
    private boolean firstTurn;
    private GameState state;
    private ArrayList<Player> players;
    private int currPlayerIdx;
    private Deck deck;
    private Kingdomino game;
    private boolean isFastMode;
    private int numGames;
    private Domino[] dominoesToSelect;

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
        firstTurn = true;
        deck = new Deck();
        //players = new ArrayList<Player>();
        currPlayerIdx = 0;
    }

    public void setGameState(GameState state) {
        this.state = state;
        game.changePanel(state);

        if (state == GameState.INITIAL) {
            reset();
        } else if (state == GameState.PLAYER_TURN) {
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
        while (!deck.isEmpty()) {
            if (!strategyMode || isFastMode) {
                turn();
            } else {
                slowMode();
            }
        }
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
        firstTurn = false;
    }


    public Domino[] getDominoesToSelect() {
        return dominoesToSelect;
    }

    // new turn
    private void turn() {
        dominoesToSelect = deck.getDominoesToSelect();
        playerTurn();
    }


    // individual player in a turn
    private void playerTurn() {
        Player currentPlayer = getCurrentPlayer();
        if (currentPlayer instanceof ComputerPlayer) {
            ((ComputerPlayer) currentPlayer).calculateChoice();
            ((ComputerPlayer) currentPlayer).placeDomino();
            nextPlayer();
        }
    }

    // called when player finishes turn
    // updates player and calls playerTurn()
    public boolean nextPlayer() {
//        if (getCurrentPlayer().hasPlaced())
//            System.out.println("has placed");
//        if (getCurrentPlayer().hasSelected())
//            System.out.println("has selected");
        if (getCurrentPlayer().hasPlaced() && getCurrentPlayer().hasSelected()) {
            getCurrentPlayer().setSelected(false);
            getCurrentPlayer().setPlaced(false);
            if (currPlayerIdx == players.size() - 1) {
                updatePlayerOrder();
                firstTurn = false;
            } else {
                updatePlayerIdx();
                playerTurn();
            }
            return true;
        }
        return false;
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
                if (dominoValues.get(j) > dominoValues.get(j + 1)) {
                    Player temp = players.get(j);
                    players.set(j, players.get(j + 1));
                    players.set(j + 1, temp);
                }
            }
        }
    }

//    private void turn() {
//        dominoesToSelect = deck.getDominoesToSelect();
//        for (int i = 0; i < players.size(); i++) {
//            currPlayerIdx = i;
//            Player currentPlayer = players.get(currPlayerIdx);
//            if (currentPlayer instanceof ComputerPlayer) {
//                ((ComputerPlayer) currentPlayer).calculateChoice();
//                ((ComputerPlayer) currentPlayer).placeDomino();
//            } else {
//                Thread newThread = new Thread();
//                while (!currentPlayer.hasSelected()) {
//                    synchronized (this) {
//                        try {
//                            newThread.wait();
//                        } catch (InterruptedException e) {
//                            System.out.println("Thread interrupted");
//                        }
//                    }
//                }
//                newThread.notifyAll();
//                while (!currentPlayer.hasPlaced()) {
//                    synchronized (this) {
//                        try {
//                            newThread.wait();
//                        } catch (InterruptedException e) {
//                            System.out.println("Thread interrupted");
//                        }
//                    }
//                }
//                newThread.notifyAll();
//
//
////                Timer timer1 = new Timer(1, null);
////                timer1.addActionListener(new ActionListener() {
////                    public void actionPerformed(ActionEvent e) {
////                        if (currentPlayer.hasSelected())
////                            timer1.stop();
////                    }
////                });
////                timer1.start();
////
////                Timer timer2 = new Timer(1, null);
////                timer2.addActionListener(new ActionListener() {
////                    public void actionPerformed(ActionEvent e) {
////                        if (currentPlayer.hasPlaced())
////                            timer2.stop();
////                    }
////                });
////                if (!firstTurn)
////                    timer2.start();
//
//            }
//            currentPlayer.setSelected(false);
//            currentPlayer.setPlaced(false);
//        }
//        updateTurnOrder();
//        firstTurn = false;
//    }

    public boolean isFirstTurn() {
        return firstTurn;
    }

    public void setMode(boolean fastMode) {
        this.isFastMode = fastMode;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players.clear();
        this.players.addAll(players);
    }

    public Player getCurrentPlayer() {
        return players.get(currPlayerIdx);
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
    }


}