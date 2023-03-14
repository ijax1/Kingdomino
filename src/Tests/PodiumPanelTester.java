package Tests;

import java.util.ArrayList;

import javax.swing.JFrame;

import Backend.GameManager.GameState;
import Backend.Player;
import resources.OurColors;

public class PodiumPanelTester {
	public static void main(String[]args) {
		new PodiumPanelTester().runGUI();
	}
	public void runGUI() {
		JFrame frame = new JFrame(getClass().getName());
		//Creating 4 fake players with scores 20, 30, 25 and 25 (this should test ties well)
		ArrayList<Player>fakePlayers = new ArrayList<Player>(4);
		fakePlayers.add(new TestPlayer(OurColors.RED, "Don Quixote", "The Ingenious",20));
		fakePlayers.add(new TestPlayer(OurColors.BLUE, "King Arthur", "The Round",30));
		fakePlayers.add(new TestPlayer(OurColors.GREEN, "Sir Gawain", "The Green",25));
		fakePlayers.add(new TestPlayer(OurColors.YELLOW, "Ian Jackson", "The Glorious",25));
		TestKingdomino k = new TestKingdomino();
		k.getManager().setPlayers(fakePlayers);
//		for(Player p: fakePlayers) {
//			System.out.println(p.getScore());
//		}
		k.getManager().setGameState(GameState.ENDSCREEN);
	}
}
