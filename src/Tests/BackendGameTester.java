package Tests;

import java.util.ArrayList;

import Backend.GameManager;
import Backend.GameManager.GameState;
import Backend.Player;
import resources.OurColors;

public class BackendGameTester {
	public static void main(String[]args) {
		TestKingdomino k = new TestKingdomino ();
		GameManager g = k.getManager();
		g.setGameState(GameState.PLAYER_TURN);
		ArrayList<Player>fakePlayers = new ArrayList<Player>(4);
		fakePlayers.add(new TestPlayer(OurColors.RED, "Don Quixote", "The Ingenious",20));
		fakePlayers.add(new TestPlayer(OurColors.BLUE, "King Arthur", "The Round",30));
		fakePlayers.add(new TestPlayer(OurColors.GREEN, "Sir Gawain", "The Green",25));
		fakePlayers.add(new TestPlayer(OurColors.YELLOW, "Ian Jackson", "The Glorious",25));
		g.setPlayers(fakePlayers);
		g.setGameState(GameState.PLAYER_TURN);
		g.getCurrentPlayer().setNextDomino(g.getDominoesToSelect()[0]);
	}
}
