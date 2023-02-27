package UIComponents;

import java.awt.GridBagLayout;
import javax.swing.JFrame;

import resources.Resources;

public class StartPanelTester {
	
	public static void main(String[]args) {
		new StartPanelTester().runGUI();
	}
	public void runGUI() {
		JFrame frame = new JFrame(getClass().getName());
		StartPanel panel = new StartPanel(new GridBagLayout());
		frame.setSize(1280,720);
		frame.add(panel);
		

		
		//frame.pack();
		frame.setVisible(true);
		panel.repaint();
		new ErrorDialog(frame);
	}
}
