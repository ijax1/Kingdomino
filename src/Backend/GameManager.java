package Backend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class GameManager {
    private boolean firstTurn;
    private GameState state;
    private ArrayList<Player> players;
    private int currPlayerIdx;
    private Deck deck;
    private Kingdomino game;
    private boolean isFastMode;
    private boolean strategyMode;
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
        reset();
    }

    public GameState getGameState() {
        return state;
    }

    public void setNumGames(int numGames) {
        this.numGames = numGames;
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
        if (!strategyMode) {
            while (!deck.isEmpty()) {
                turn();
            }
        } else if (isFastMode) {
            fastMode();

        } else {
            slowMode();
        }
    }

    private void fastMode() {

    }

    private void slowMode() {

    }

    private void turn() {
        for (int i = 0; i < players.size(); i++) {
            currPlayerIdx = i;
            Player currentPlayer = players.get(currPlayerIdx);
            if (currentPlayer instanceof ComputerPlayer) {
                ((ComputerPlayer) currentPlayer).calculateChoice();
                ((ComputerPlayer) currentPlayer).placeDomino();
            } else {
                Timer timer = new Timer(1, null);
                timer.addActionListener(e -> {
                    if (currentPlayer.hasSelected())
                        timer.stop();
                });
                if (!firstTurn) {
                    timer.start();
                    if (currentPlayer.hasPlaced())
                        timer.stop();
                }
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
        ArrayList<Integer> dominoValues = new ArrayList<Integer>();
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
        players = new ArrayList<Player>();
        currPlayerIdx = 0;
    }
}