package Backend;

import java.util.ArrayList;

public class GameManager {
    private boolean firstTurn;
    private GameState state;
    private ArrayList<Player> players;
    private int currentPlayer;
    private Deck deck;
    private Kingdomino game;
    private boolean fastMode;

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
        firstTurn = true;
        state = GameState.INITIAL;
//        deck = new Deck();
        players = new ArrayList<>();
        currentPlayer = 0;
    }

    public GameState getGameState() {
        return state;
    }

    public void setGameState(GameState state) {
        this.state = state;
        if (state == GameState.INITIAL) {
            game.changePanel(GameState.INITIAL);
//            deck = new Deck();
            players = new ArrayList<Player>();
            currentPlayer = 0;
        } else if (state == GameState.PLAYER_TURN) {
            game.changePanel(GameState.PLAYER_TURN);
//            while (play continues) {
//                turn();
//            }
        } else if (state == GameState.END_ROUND) {
            game.changePanel(GameState.END_ROUND);
        } else if (state == GameState.TALLY_SCORE) {
            game.changePanel(GameState.TALLY_SCORE);
        } else if (state == GameState.ENDSCREEN) {
            setResults();
            game.changePanel(GameState.ENDSCREEN);
        } else if (state == GameState.STRATEGY) {
            game.changePanel(GameState.STRATEGY);
        }
    }

    private void turn() {
        for (int i = 0; i < players.size(); i++) {
            currentPlayer = i;
            Player current = players.get(currentPlayer);
            while (!current.hasSelected()) {}
            if (firstTurn) {
                while (!current.hasPlaced()) {}
            }
            current.setSelected(false);
            current.setPlaced(false);
        }
        updateTurnOrder();
        firstTurn = false;
    }

    public boolean getFirstTurn() {
        return firstTurn;
    }

    public void setMode(boolean fastMode) {
        this.fastMode = fastMode;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players.clear();
        for (Player player : players) {
            this.players.add(player);
        }
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void setCurrentPlayer() {
        currentPlayer++;
        if (currentPlayer>players.size()-1) {
            currentPlayer = 0;
        }
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
}