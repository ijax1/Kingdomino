package UIComponents;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import UIComponents.Render.Coordinate;
import resources.OurColors;
import resources.Resources;

@SuppressWarnings("serial")
public class AnalysisPanel extends JPanel {
	BufferedImage player;
	BufferedImage computer;
	BufferedImage none;
	CloseButton close;
	int width = 200;
	int height = 500;

	public AnalysisPanel(GridBagLayout g) {
		super(g);
		//setBackground(OurColors.BACKGROUND);
		GridBagConstraints c = new GridBagConstraints();

		JPanel box1 = new JPanel();
		box1.setSize(width, height);
		JPanel box2 = new JPanel();
		box2.setSize(width, height);
		JPanel box3 = new JPanel();
		box3.setSize(width, height);
		JPanel box4 = new JPanel();
		box4.setSize(width, height);

		//JButton scrollB = new JButton("Quiteth");
		JLabel scroll = new JLabel("Kingdomino", SwingConstants.CENTER);
		scroll.setFont(Resources.getMedievalFont(50));
		scroll.setForeground(OurColors.FONT_LIGHT);
		RoyalButton exit = new RoyalButton("Exiteth");
		RoyalButton play = new RoyalButton("Playeth Once More");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				changePanel(GameState.PLAYER_TURN);
				 */
			}
		});

		//close = new CloseButton(new Coordinate(1200,800,0), null);

		//Settings for whole layout
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx=0.5;
		c.gridheight = 1;
		c.insets = new Insets(10,10,10,10);
		c.anchor = GridBagConstraints.CENTER;

		//Settings for individual components
		c.gridwidth = 4;
		c.weighty = 0;	//Title can get cut off
		c.gridx = 0;
		c.gridy = 0;
		//c.gridwidth = GridBagConstraints.REMAINDER;
		g.setConstraints(scroll, c);
		add(scroll);
		//c.weighty = 0.2;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		g.setConstraints(box1, c);
		add(box1);
		c.gridx = 1;
		c.gridy = 1;
		g.setConstraints(box2, c);
		add(box2);
		c.gridx = 2;
		c.gridy = 1;
		g.setConstraints(box3, c);
		add(box3);
		c.gridx = 3;
		c.gridy = 1;
		g.setConstraints(box4, c);
		add(box4);
		c.ipady = 20;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		g.setConstraints(exit, c);
		add(exit);

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
		//close.draw((Graphics2D) g.create());
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
