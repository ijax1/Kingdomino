package UIComponents;

import java.util.ArrayList;

import javax.swing.JFrame;

import Backend.Kingdomino;
import Backend.Player;
import Backend.RandomStrategy;
import resources.OurColors;

public class GamePanelTester {
	public static void main(String[]args) {
		new GamePanelTester().runGUI();
	}
	public void runGUI() {
		JFrame frame = new JFrame(getClass().getName());
		ArrayList<Player>fakePlayers = new ArrayList<Player>(4);
		Kingdomino k = new 
		fakePlayers.add(new RandomStrategy(OurColors.RED, "Don Quixote", "The Ingenious", gm));
		fakePlayers.add(new RandomStrategy(OurColors.BLUE, "King Arthur", "The Round", gm));
		fakePlayers.add(new RandomStrategy(OurColors.GREEN, "Sir Gawain", "The Green", gm));
		fakePlayers.add(new RandomStrategy(OurColors.YELLOW, "Ian Jackson", "The Glorious", gm));
		GamePanel panel = new GamePanel(fakePlayers,new Kingdomino());
		frame.setSize(1280,720);
		frame.add(panel);
		//panel.add(new UIDomino());
		//panel.add(new UIGrid());

		//frame.pack();
		frame.setVisible(true);
		panel.repaint();
	}
}
