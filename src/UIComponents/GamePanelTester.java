package UIComponents;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GamePanelTester {
	public static void main(String[]args) {
		new GamePanelTester().runGUI();
	}
	public void runGUI() {
		JFrame frame = new JFrame(getClass().getName());
		GamePanel panel = new GamePanel();
		frame.setSize(1280,720);
		frame.add(panel);
		panel.add(new UIDomino());
		panel.add(new UIGrid());
		
		//frame.pack();
		frame.setVisible(true);
		panel.repaint();
	}
}
