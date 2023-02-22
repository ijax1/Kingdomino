package Backend;

import java.util.ArrayList;

public class GameManager {
    final Deck STARTING_DECK; //probably unnecessary to be final

    GameState state;
    ArrayList<Player> players;
    int currentPlayer;
    Deck deck;

    enum GameState {
        INITIAL,
        FIRST_TURN,
        PLAYER_TURN,
        END_ROUND,
        TALLY_SCORE,
        ENDSCREEN,
        STRATEGY
    }


        GameManager(){

    }

    GameState getGameState(){

    }

    void setGameState(){

    }

    ArrayList<Player> getPlayers(){
        return null;
    }

    Player getCurrentPlayer(){

    }

    void updateTurnOrder(){

    }

    void setResults(){

    }

    void reset(){

    }
}
