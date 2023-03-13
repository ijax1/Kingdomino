package Tests;

import java.util.ArrayList;

import Backend.GameManager.GameState;
import Backend.Player;
import resources.OurColors;

public class TallyPanelTester {
	public static void main(String[]args) {
		new TallyPanelTester().runGUI();
	}
	public void runGUI() {
		//Creating 4 fake players with scores 20, 30, 25 and 25 (this should test ties well)
		ArrayList<Player>fakePlayers = new ArrayList<Player>(4);
		fakePlayers.add(new TestPlayer(OurColors.RED, "Don Quixote", "The Ingenious",20));
		fakePlayers.add(new TestPlayer(OurColors.BLUE, "King Arthur", "The Round",30));
		fakePlayers.add(new TestPlayer(OurColors.GREEN, "Sir Gawain", "The Green",25));
		fakePlayers.add(new TestPlayer(OurColors.YELLOW, "Ian Jackson", "The Glorious",25));
		
		TestKingdomino k = new TestKingdomino();
		k.getManager().setPlayers(fakePlayers);
		k.changePanel(GameState.TALLY_SCORE);
//		GameManager gm = k.getManager();
//		

//		GamePanel panel = new GamePanel(fakePlayers, k);
//		frame.setSize(1280,720);
//		frame.add(panel);
//		panel.add(new UIDomino());
//		panel.add(new UIGrid());
//
//		frame.pack();
//		frame.setVisible(true);
//		panel.repaint();
	}
}
