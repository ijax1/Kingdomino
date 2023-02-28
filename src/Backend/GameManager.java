package Backend;

import java.util.ArrayList;

public class GameManager {
    private Kingdomino game;
    private GameState state;
    private ArrayList<Player> players;
    private int currentPlayer;
    private Deck deck;

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
    }

    public GameState getGameState() {
        return state;
    }

    public void setGameState(GameState state) {
        this.state = state;
        if (state == GameState.INITIAL) {
            deck = new Deck();
            game.changePanel(GameState.INITIAL);
        } else if (state == GameState.PLAYER_TURN) {

            game.changePanel(GameState.PLAYER_TURN);
        } else if (state == GameState.END_ROUND) {
            game.changePanel(GameState.END_ROUND);
        } else if (state == GameState.TALLY_SCORE) {
            game.changePanel(GameState.TALLY_SCORE);
        } else if (state == GameState.ENDSCREEN) {
            game.changePanel(GameState.ENDSCREEN);
        } else if (state == GameState.STRATEGY) {
            game.changePanel(GameState.STRATEGY);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = new ArrayList<Player>();
        for (Player player : players) {
            this.players.add(player);
        }
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public Deck getDeck() {
        return deck;
    }

    public void updateTurnOrder() {
        ArrayList<Integer> dominoValues = new ArrayList<Integer>();
        for (int i = 0; i < players.size(); i++) {
            dominoValues.add(players.get(i).getNextDomino().getValue());
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

    public void reset() {
        deck = new Deck();
    }
}
