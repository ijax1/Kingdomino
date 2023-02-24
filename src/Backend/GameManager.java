package Backend;

import java.util.ArrayList;

public class GameManager {
    final Deck STARTING_DECK; //probably unnecessary to be final

    private GameState state;
    private ArrayList<Player> players;
    private int currentPlayer;
    private Deck deck;

    public enum GameState {
        INITIAL,
        FIRST_TURN,
        PLAYER_TURN,
        END_ROUND,
        TALLY_SCORE,
        ENDSCREEN,
        STRATEGY
    }


    public GameManager() {

    }

    public GameState getGameState() {
        return state;
    }

    public void setGameState(GameState state) {
        this.state = state;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void updateTurnOrder() {

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

    }
}
