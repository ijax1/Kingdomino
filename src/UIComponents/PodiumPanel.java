 package UIComponents;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Backend.Kingdomino;

@SuppressWarnings("serial")
public class PodiumPanel extends JPanel {
	public PodiumPanel(Kingdomino k) {
		
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Podium Panel");
		PodiumPanel panel = new PodiumPanel(new Kingdomino());
		frame.setSize(1280,720);
		frame.add(panel);

		
		//frame.pack();
		frame.setVisible(true);
		panel.repaint();
		new ErrorDialog(frame);
	}
}
