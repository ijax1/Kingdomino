package UIComponents;

import java.awt.AlphaComposite;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Backend.Player;
import resources.Resources;

public class PlayerSelectPanel extends JPanel {
	public static final String COMPUTER = "COMPUTER";
	public static final String HUMAN = "HUMAN";
	public static final String NONE = "NONE";
	private JPanel inputPanel;
	private CardLayout card;
	private JTextField textBox;
	private JComboBox<String>computerBox;
	private JLabel noneBox;
	
	private JRadioButton humanButton;
	private JRadioButton computerButton;
	private JRadioButton noneButton;
	
	private BufferedImage humanImg;
	private BufferedImage computerImg;
	private BufferedImage noneImg;
	
	private ImageIcon humanIcon;
	private ImageIcon computerIcon;
	private ImageIcon noneIcon;
	
	private JLabel avatarHolder;
	
	public PlayerSelectPanel(Color color, int playerNo, String defaultPlayer) {
		
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		setBackground(color);
		
		inputPanel = new JPanel();
		card = new CardLayout();
		inputPanel.setLayout(card);

		
		String[]choices = {"Random Strategy", "Skilled Strategy"};
		//TODO: make JCombobox use Strategy instead of String?
		computerBox = new JComboBox<String>(choices);
		computerBox.setSelectedItem("Skilled Strategy");
		textBox = new NameTextField("Player " + playerNo);
		noneBox = new JLabel("---", SwingConstants.CENTER);

		humanButton = new JRadioButton("Human");
		computerButton = new JRadioButton("Computer");
		noneButton = new JRadioButton("None");
		
//		humanButton.setFocusPainted(false);
//		computerButton.setFocusPainted(false);
//		noneButton.setFocusPainted(false);
		
		humanImg = Resources.loadImage("player_icon.png");
		computerImg = Resources.loadImage("computer_icon.png");
		noneImg = Resources.loadImage("none_icon.png");
		
		tint(humanImg, color);
		tint(computerImg, color);
		tint(noneImg, color);

		humanIcon = toImageIcon(humanImg);
		computerIcon = toImageIcon(computerImg);
		noneIcon = toImageIcon(noneImg);

		//TODO: not centered
		avatarHolder = new JLabel(humanIcon, SwingConstants.CENTER);
		
		humanButton.setFont(Resources.getMedievalFont(40));
		computerButton.setFont(Resources.getMedievalFont(40));
		noneButton.setFont(Resources.getMedievalFont(40));
		
		humanButton.setOpaque(false);
		computerButton.setOpaque(false);
		noneButton.setOpaque(false);
		
		humanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showHumanCard();
			}
		});
		computerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showComputerCard();
			}
		});
		noneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showNoneCard();
			}
		});

		ButtonGroup group = new ButtonGroup();
		
		group.add(humanButton);
		group.add(computerButton);
		group.add(noneButton);
		
		inputPanel.add(computerBox, COMPUTER);
		inputPanel.add(textBox, HUMAN);
		inputPanel.add(noneBox, NONE);
		
		add(avatarHolder);
		add(inputPanel);
		add(computerButton);
		add(humanButton);
		add(noneButton);
		
		//this only works after input panel is added
		if(defaultPlayer.equals(HUMAN)) {
			humanButton.setSelected(true);
			showHumanCard();
		} else if(defaultPlayer.equals(COMPUTER)) {
			computerButton.setSelected(true);
			showComputerCard();
		} else if(defaultPlayer.equals(NONE)) {
			noneButton.setSelected(true);
			showNoneCard();
		}
	}
	private void showHumanCard() {
		avatarHolder.setIcon(humanIcon);
		inputPanel.setVisible(true);
		card.show(inputPanel, HUMAN);
	}
	private void showComputerCard() {
		avatarHolder.setIcon(computerIcon);
		inputPanel.setVisible(true);
		card.show(inputPanel, COMPUTER);
	}
	private void showNoneCard() {
		avatarHolder.setIcon(noneIcon);
		inputPanel.setVisible(true);
		card.show(inputPanel, NONE);
	}
	private void tint(BufferedImage img, Color color) {
		Graphics2D g = img.createGraphics();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g.setColor(color);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
	}
	private ImageIcon toImageIcon(BufferedImage img){
		Image resizedImage = img.getScaledInstance(150,150, Image.SCALE_SMOOTH);
		ImageIcon playerIcon = new ImageIcon(resizedImage);
		return playerIcon;
	}
	/** gets the player based on the selected options.
	 * 
	 * @return the new player, null if "None" option selected
	 */
	/*
	public Player createPlayer() {
		if(noneButton.isSelected()) {
			return null;
		} else if(computerButton.isSelected()) {
			if(computerBox.getSelectedItem().equals("Random Strategy")) {
				return new RandomStrategy();
			} else if(computerBox.getSelectedItem().equals("Skilled Strategy")) {
				return new SkilledStrategy();
			}
		} else if(playerButton.isSelected()) {
			return new HumanPlayer(color, textBox.getText());
		}
	}
	*/
}
