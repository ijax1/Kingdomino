package UIComponents;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import resources.OurColors;
import resources.Resources;

@SuppressWarnings("serial")
public class StartPanel extends JPanel {
	BufferedImage player;
	BufferedImage computer;
	BufferedImage none;
	
	public StartPanel(GridBagLayout g) {
		super(g);
		//setBackground(OurColors.BACKGROUND);
		GridBagConstraints c = new GridBagConstraints();
		
		PlayerSelectPanel player1 = new PlayerSelectPanel(OurColors.RED, 1, PlayerSelectPanel.HUMAN);
		PlayerSelectPanel player2 = new PlayerSelectPanel(OurColors.BLUE, 2, PlayerSelectPanel.COMPUTER);
		PlayerSelectPanel player3 = new PlayerSelectPanel(OurColors.GREEN, 3, PlayerSelectPanel.COMPUTER);
		PlayerSelectPanel player4 = new PlayerSelectPanel(OurColors.YELLOW, 4, PlayerSelectPanel.COMPUTER);
		//JButton scrollB = new JButton("Quiteth");
		JLabel scroll = new JLabel("Kingdomino", SwingConstants.CENTER);
		scroll.setFont(Resources.getMedievalFont(50));
		scroll.setForeground(OurColors.FONT_LIGHT);
		JButton quit = new JButton("Quiteth");
		JButton play = new JButton("Playeth");

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx=0;
		c.gridheight = 1;
		
		c.gridx = 0;
		c.gridy = 0;
		
		c.gridwidth = 4;
		c.anchor = GridBagConstraints.CENTER;
		//c.gridwidth = GridBagConstraints.REMAINDER;
		g.setConstraints(scroll, c);
		add(scroll);
		
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		g.setConstraints(player1, c);
		add(player1);
		c.gridx = 1;
		c.gridy = 1;
		g.setConstraints(player2, c);
		add(player2);
		c.gridx = 2;
		c.gridy = 1;
		g.setConstraints(player3, c);
		add(player3);
		c.gridx = 3;
		c.gridy = 1;
		g.setConstraints(player4, c);
		add(player4);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		g.setConstraints(quit, c);
		add(quit);
		
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 2;
		g.setConstraints(play, c);
		add(play);
		player = Resources.loadImage("player_icon.png");
		computer = Resources.loadImage("computer_icon.png");
		none = Resources.loadImage("none_icon.png");
	}
	@Override
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D)g1;
		GamePanel.applyHints(g);
		g.setColor(OurColors.BACKGROUND);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(OurColors.BACKGROUND_CIRCLE);
		g.fillOval(100,50,getWidth()-200, getHeight()-100);
		//AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
		//g.setComposite(ac);
		//player = tint(player, new Color(0xe06666));
		//g.drawImage(player, 100,100,200,200, null);
		//g.drawImage(player, 100,100,100,100,null);
	}
	private BufferedImage tint(BufferedImage img, Color color) {
		Graphics2D g2 = img.createGraphics();
		//g2.setXORMode(color);
		//g2.fillRect(0,0,img.getWidth(), img.getHeight());
		return img;
	}
}
