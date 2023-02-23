package UIComponents;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import resources.Resources;

public class PlayerPanel extends JPanel {
	JTextField textBox;
	
	public PlayerPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		String[]choices = {"Choice 1", "Choice 2"};
		JComboBox<String>computerBox = new JComboBox<String>(choices);
		textBox = new NameTextField("Player 1");
		add(textBox);
		add(computerBox);

		JRadioButton playerButton = new JRadioButton("Human");
		JRadioButton computerButton = new JRadioButton("Computer");
		JRadioButton noneButton = new JRadioButton("none");
		
		playerButton.setFont(Resources.getMedievalFont(40));
		computerButton.setFont(Resources.getMedievalFont(40));
		noneButton.setFont(Resources.getMedievalFont(40));
		computerButton.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		
		group.add(playerButton);
		group.add(computerButton);
		group.add(noneButton);
		
		add(computerButton);
		add(playerButton);
		add(noneButton);
	}
}
