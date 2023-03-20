package UIComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Backend.Kingdomino;
import Backend.Player;
import Backend.ComputerPlayer;
import Backend.GameManager.GameState;
import resources.OurColors;
import resources.Resources;

@SuppressWarnings("serial")
public class AnalysisPanel extends JPanel {
	private BufferedImage player;
	private BufferedImage computer;
	private BufferedImage none;
	private CloseButton close;
	private int width = 200;
	private int height = 500;
	private Kingdomino kingdomino;

	public AnalysisPanel(GridBagLayout g, Kingdomino kingdomino) {
		super(g);
		//setBackground(OurColors.BACKGROUND);
		GridBagConstraints c = new GridBagConstraints();
		this.kingdomino = kingdomino;
		JPanel box1 = new JPanel();
		box1.setSize(width, height);
		
		
		//VALUES:
		ArrayList<Player> players = kingdomino.getManager().getPlayers();
		int totalPlayers = kingdomino.getManager().getPlayers().size();
		
		int totalGames = kingdomino.getManager().getNumGames();
		
		kingdomino.getManager().setResults();
		//last # of playerOrder won game = player #
		Integer winner = kingdomino.getManager().getPlayerOrder().get(totalPlayers-1);
		
		
		
		String stratType;
		
		
		
		//System.out.println("totalPlayers: " + totalPlayers);

		for (int x = 0; x<totalPlayers; x++) {
			if(players.get(x) instanceof ComputerPlayer) {
				stratType = ((ComputerPlayer) players.get(x)).getStrategyName();
			}
		}
			
			
			
			//System.out.println("Player " + (x+1) + ": " + players.get(x).getName() + ", " + stratType);
				
				
		JLabel box1Text = new JLabel("box1", SwingConstants.CENTER);
		box1.add(box1Text);

		
		JPanel box2 = new JPanel();
		box2.setSize(width, height);
		JPanel box3 = new JPanel();
		box3.setSize(width, height);
		JPanel box4 = new JPanel();
		box4.setSize(width, height);

		//JButton scrollB = new JButton("Quiteth");
		JLabel scroll = new JLabel("Strategy Analysis", SwingConstants.CENTER);
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
				kingdomino.getManager().setGameState(GameState.INITIAL);				 
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

//	public void displayAnalysis() {
//		kingdomino.getManager()
//	}
}
