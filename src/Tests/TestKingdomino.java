package Tests;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

import Backend.GameManager;
import Backend.GameManager.GameState;
import Backend.Kingdomino;
import UIComponents.AnalysisPanel;
import UIComponents.GamePanel;


public class TestKingdomino extends Kingdomino {


	public TestKingdomino() {

	}

	// GamePanel needs to be initialized after startPanel initializes players
	@Override
	public JFrame getFrame() {
		return new JFrame();
	}
	@Override
	public void setGameAndPanelState(GameState state) {
		System.out.println("Panel changed to "+ state);
	}
	@Override
	public GamePanel getGamePanel() {
		GamePanel fake = new GamePanel(this);
		return fake;
	}
	@Override
	public AnalysisPanel getAnalysisPanel() {
		return new AnalysisPanel(new GridBagLayout(), this);
	}
}
