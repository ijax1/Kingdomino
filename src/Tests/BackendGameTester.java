package Tests;

import java.util.ArrayList;

import Backend.GameManager;
import Backend.GameManager.GameState;
import Backend.Kingdomino;
import Backend.Player;
import resources.OurColors;

public class BackendGameTester {
	public static void main(String[]args) {
		Kingdomino k = new Kingdomino ();
		//GameManager g = new GameManager(k);
		//g.setGameState(GameState.PLAYER_TURN);
		ArrayList<Player>fakePlayers = new ArrayList<Player>(4);
		fakePlayers.add(new TestPlayer(OurColors.RED, "Don Quixote", "The Ingenious",20));
		fakePlayers.add(new TestPlayer(OurColors.BLUE, "King Arthur", "The Round",30));
		fakePlayers.add(new TestPlayer(OurColors.GREEN, "Sir Gawain", "The Green",25));
		fakePlayers.add(new TestPlayer(OurColors.YELLOW, "Ian Jackson", "The Glorious",25));
		k.getManager().setPlayers(fakePlayers);
		k.getManager().setGameState(GameState.PLAYER_TURN);
		k.getManager().getCurrentPlayer().setNextDomino(k.getManager().getDominoesToSelect()[0]);
	}
}
