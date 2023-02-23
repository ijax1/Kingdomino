package UIComponents;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import resources.Resources;

public class StartPanelTester {
	
	public static void main(String[]args) {
		new StartPanelTester().runGUI();
	}
	public void runGUI() {
		JFrame frame = new JFrame(getClass().getName());
		StartPanel panel = new StartPanel();
		frame.setSize(1280,720);
		frame.add(panel);
		

		
		//frame.pack();
		frame.setVisible(true);
		panel.repaint();
	}
}
