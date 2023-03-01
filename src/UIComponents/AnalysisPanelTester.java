package UIComponents;

import java.awt.GridBagLayout;
import javax.swing.JFrame;

import resources.Resources;

public class AnalysisPanelTester {
	
	public static void main(String[]args) {
		new AnalysisPanelTester().runGUI();
	}
	public void runGUI() {
		JFrame frame = new JFrame(getClass().getName());
		AnalysisPanel panel = new AnalysisPanel(new GridBagLayout());
		frame.setSize(1280,720);
		frame.add(panel);
		

		
		//frame.pack();
		frame.setVisible(true);
		panel.repaint();
		new ErrorDialog(frame);
	}
}
