package Tests;

import java.util.ArrayList;

import javax.swing.JFrame;

import Backend.GameManager;
import Backend.GameManager.GameState;
import Backend.Kingdomino;
import Backend.Player;
import Backend.RandomStrategy;
import UIComponents.GamePanel;
import resources.OurColors;

public class GamePanelTester {
	public static void main(String[]args) {
		new GamePanelTester().runGUI();
	}
	public void runGUI() {
		JFrame frame = new JFrame(getClass().getName());
		ArrayList<Player>fakePlayers = new ArrayList<Player>(4);
		TestKingdomino k = new TestKingdomino();
		k.changePanel(GameState.PLAYER_TURN);
//		GameManager gm = k.getManager();
//		
//		fakePlayers.add(new TestPlayer(OurColors.RED, "Don Quixote", "The Ingenious"));
//		fakePlayers.add(new TestPlayer(OurColors.BLUE, "King Arthur", "The Round"));
//		fakePlayers.add(new TestPlayer(OurColors.GREEN, "Sir Gawain", "The Green"));
//		fakePlayers.add(new TestPlayer(OurColors.YELLOW, "Ian Jackson", "The Glorious"));
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
