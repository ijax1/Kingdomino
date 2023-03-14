package Tests;

import java.awt.GridBagLayout;
import javax.swing.JFrame;

import Backend.Kingdomino;
import UIComponents.AnalysisPanel;

public class AnalysisPanelTester {
	
	public static void main(String[]args) {
		new AnalysisPanelTester().runGUI();
	}
	public void runGUI() {
		JFrame frame = new JFrame(getClass().getName());
		Kingdomino kingdomino = new Kingdomino();
		AnalysisPanel panel = new AnalysisPanel(new GridBagLayout(), kingdomino);
		frame.setSize(1280,720);
		frame.add(panel);
		

		
		//frame.pack();
		frame.setVisible(true);
		panel.repaint();
		// new ErrorDialog(frame);
	}
}
