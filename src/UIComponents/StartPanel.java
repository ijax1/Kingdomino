package UIComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import resources.Resources;

public class StartPanel extends JPanel {
	BufferedImage player;
	BufferedImage computer;
	BufferedImage none;
	
	
	public StartPanel() {
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		PlayerPanel player1 = new PlayerPanel();
		PlayerPanel player2 = new PlayerPanel();
		PlayerPanel player3 = new PlayerPanel();
		PlayerPanel player4 = new PlayerPanel();
		TopScroll scroll = new TopScroll();

		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		add(scroll);
		c.gridx = 0;
		c.gridy = 1;
		add(player1);
		c.gridx = 1;
		c.gridy = 1;
		add(player2);
		c.gridx = 2;
		c.gridy = 1;
		add(player3);
		c.gridx = 3;
		c.gridy = 1;
		add(player4);
		player = Resources.loadImage("player_icon.png");
		computer = Resources.loadImage("computer_icon.png");
		none = Resources.loadImage("none_icon.png");
	}
	@Override
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D)g1;
		Graphics2D g2 = player.createGraphics();
		GamePanel.applyHints(g2);
		player = tint(player, new Color(0xe06666));
		g.drawImage(player, 100,100,200,200, null);
		//g.drawImage(player, 100,100,100,100,null);
	}
	private BufferedImage tint(BufferedImage img, Color color) {
		Graphics2D g2 = img.createGraphics();
		g2.setXORMode(color);
		g2.fillRect(0,0,img.getWidth(), img.getHeight());
		return img;
	}
}
