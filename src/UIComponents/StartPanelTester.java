package UIComponents;

import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class StartPanelTester {
	public static void main(String[]args) {
		new StartPanelTester().runGUI();
	}
	public void runGUI() {
		JFrame frame = new JFrame(getClass().getName());
		StartPanel panel = new StartPanel();
		frame.setSize(1280,720);
		frame.add(panel);
		String[]choices = {"Choice 1", "Choice 2"};
		JComboBox<String>computerBox = new JComboBox<String>(choices);
		
		JTextField textBox = new JTextField("Choose a somethign");
		textBox.setMinimumSize(new Dimension(50,100));
		panel.add(textBox);
		
		panel.add(computerBox);
		
		JRadioButton playerButton = new JRadioButton("Player");
		JRadioButton computerButton = new JRadioButton("Computer");
		JRadioButton noneButton = new JRadioButton("none");
		computerButton.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		
		group.add(playerButton);
		group.add(computerButton);
		group.add(noneButton);
		
		panel.add(computerButton);
		panel.add(playerButton);
		panel.add(noneButton);

		
		//frame.pack();
		frame.setVisible(true);
		panel.repaint();
	}
}
