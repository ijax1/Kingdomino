package UIComponents;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import resources.Resources;

public class PlayerPanel extends JPanel {
	JTextField textBox;
	
	public PlayerPanel(Color color) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(color);
		String[]choices = {"Choice 1", "Choice 2"};
		JComboBox<String>computerBox = new JComboBox<String>(choices);
		textBox = new NameTextField("Player 1");

		JRadioButton playerButton = new JRadioButton("Human");
		JRadioButton computerButton = new JRadioButton("Computer");
		JRadioButton noneButton = new JRadioButton("none");
		
		BufferedImage player = Resources.loadImage("player_icon.png");
		
		Image resizedImage = player.getScaledInstance(100,100, Image.SCALE_SMOOTH);
		JLabel avatarHolder = new JLabel(new ImageIcon(resizedImage));
		
		
		playerButton.setFont(Resources.getMedievalFont(40));
		computerButton.setFont(Resources.getMedievalFont(40));
		noneButton.setFont(Resources.getMedievalFont(40));
		
		playerButton.setOpaque(false);
		computerButton.setOpaque(false);
		noneButton.setOpaque(false);
		
		playerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		computerButton.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		
		group.add(playerButton);
		group.add(computerButton);
		group.add(noneButton);
		
		add(avatarHolder);
		add(textBox);
		add(computerBox);
		add(computerButton);
		add(playerButton);
		add(noneButton);
	}
}
