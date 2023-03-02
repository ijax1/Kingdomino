 package UIComponents;

import java.awt.*;
import javax.swing.*;
import Backend.Kingdomino;

@SuppressWarnings("serial")
public class PodiumPanel extends JPanel {
	public PodiumPanel(Kingdomino k) {
		
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Podium Panel");
		PodiumPanel panel = new PodiumPanel(new GridBagLayout(), new Kingdomino());
		frame.setSize(1280,720);
		frame.add(panel);

		
		//frame.pack();
		frame.setVisible(true);
		panel.repaint();
		new ErrorDialog(frame);
	}
}
