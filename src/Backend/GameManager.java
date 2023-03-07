package Backend;

import resources.OurColors;
import resources.Titles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public GameState getGameState() {
        return state;
    }

    public void setNumGames(int numGames) {
        this.numGames = numGames;
    }

    public void setGameState(GameState state) throws InterruptedException {
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
        updateTurnOrder();
        firstTurn = false;
    }

    private void turn() {
        for (int i = 0; i < players.size(); i++) {
            currPlayerIdx = i;
            Player currentPlayer = players.get(currPlayerIdx);
            if (currentPlayer instanceof ComputerPlayer) {
                ((ComputerPlayer) currentPlayer).calculateChoice();
                ((ComputerPlayer) currentPlayer).placeDomino();
            } else {
                Timer timer1 = new Timer(1, null);
                timer1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (currentPlayer.hasSelected())
                            timer1.stop();
                    }
                });
                timer1.start();

                Timer timer2 = new Timer(1, null);
                timer2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (currentPlayer.hasPlaced())
                            timer2.stop();
                    }
                });
                if (!firstTurn)
                    timer2.start();
            }
            currentPlayer.setSelected(false);
            currentPlayer.setPlaced(false);
        }
        updateTurnOrder();
        firstTurn = false;
    }

    public boolean getFirstTurn() {
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

    public void updateCurrentPlayer() {
        currPlayerIdx++;
        if (currPlayerIdx > players.size() - 1) {
            currPlayerIdx = 0;
        }
    }

    public Deck getDeck() {
        return deck;
    }

    public void updateTurnOrder() {
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

    private void reset() {
        firstTurn = true;
        deck = new Deck();
        //players = new ArrayList<Player>();
        currPlayerIdx = 0;
    }
}