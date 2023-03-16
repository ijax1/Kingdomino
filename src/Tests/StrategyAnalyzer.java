package Tests;

import java.util.ArrayList;

import Backend.GameManager;
import Backend.GameManager.GameState;
import Backend.Player;
import Backend.SkilledStrategy;
import resources.OurColors;

public class StrategyAnalyzer {
	public static void main(String[]args) {
		TestKingdomino k = new TestKingdomino ();
		GameManager g = k.getManager();
		g.setGameState(GameState.PLAYER_TURN);
		ArrayList<Player>fakePlayers = new ArrayList<Player>(4);
		fakePlayers.add(new SkilledStrategy(OurColors.RED, "Don Quixote", "The Ingenious",g));
		fakePlayers.add(new SkilledStrategy(OurColors.BLUE, "King Arthur", "The Round",g));
		fakePlayers.add(new SkilledStrategy(OurColors.GREEN, "Sir Gawain", "The Green",g));
		fakePlayers.add(new SkilledStrategy(OurColors.YELLOW, "Ian Jackson", "The Glorious",g));
		g.setPlayers(fakePlayers);
		g.setMode(true);
		g.setGameState(GameState.PLAYER_TURN);
//			printarr(g.getDominoesToSelect());
//			g.getCurrentPlayer().setNextDomino(g.getDominoesToSelect()[0]);
	}
	private static void printarr(Object[] arr) {
		for(int i=0; i<arr.length; i++){
			System.out.print(arr[i]);
		}
		System.out.println();
	}
}
