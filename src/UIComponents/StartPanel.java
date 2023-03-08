package UIComponents;

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

import Backend.GameManager;
import Backend.GameManager.GameState;
import Backend.Kingdomino;
import Backend.Player;
import resources.OurColors;
import resources.Resources;
import resources.Titles;

@SuppressWarnings("serial")
public class StartPanel extends JPanel {
	private BufferedImage player;
	private BufferedImage computer;
	private BufferedImage none;
	private CloseButton close;
	GameManager gm;
	private final Kingdomino k;
	
	private PlayerSelectPanel[]playerPanels = new PlayerSelectPanel[4];
	
	public StartPanel(GridBagLayout g, Kingdomino kdomino) {
		super(g);
		//setBackground(OurColors.BACKGROUND);
		GridBagConstraints c = new GridBagConstraints();
		k = kdomino;
		gm = k.getManager();
		playerPanels[0] = new PlayerSelectPanel(OurColors.RED, 1, PlayerSelectPanel.HUMAN, k);
		playerPanels[1] = new PlayerSelectPanel(OurColors.BLUE, 2, PlayerSelectPanel.COMPUTER, k);
		playerPanels[2] = new PlayerSelectPanel(OurColors.GREEN, 3, PlayerSelectPanel.COMPUTER, k);
		playerPanels[3] = new PlayerSelectPanel(OurColors.YELLOW, 4, PlayerSelectPanel.COMPUTER, k);
		
		//JButton scrollB = new JButton("Quiteth");
		JLabel scroll = new JLabel("Kingdomino", SwingConstants.CENTER);
		scroll.setFont(Resources.getMedievalFont(50));
		scroll.setForeground(OurColors.FONT_LIGHT);
		RoyalButton exit = new RoyalButton("Exiteth");
		RoyalButton play = new RoyalButton("Playeth");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}	
		});
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Player> players = getAllPlayers();
				if(players.size() < 2) {
					new ErrorDialog(k.getFrame());
				} else {
					boolean finished = false;
					for(Player p: players) {
						if(p.isHuman()) {
							gm.setPlayers(players);
							gm.setGameState(GameState.PLAYER_TURN);
							finished = true;
						}
					}
					if(!finished) {
						//only computer players
						new StrategyAnalysisDialog(k.getFrame(), kdomino);
					}
				}
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
		g.setConstraints(playerPanels[0], c);
		add(playerPanels[0]);
		c.gridx = 1;
		c.gridy = 1;
		g.setConstraints(playerPanels[1], c);
		add(playerPanels[1]);
		c.gridx = 2;
		c.gridy = 1;
		g.setConstraints(playerPanels[2], c);
		add(playerPanels[2]);
		c.gridx = 3;
		c.gridy = 1;
		g.setConstraints(playerPanels[3], c);
		add(playerPanels[3]);
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
	private ArrayList<Player> getAllPlayers(){
		ArrayList<Player>players = new ArrayList<Player>();
		Titles t = new Titles();
		for(PlayerSelectPanel panel: playerPanels) {
			Player newPlayer = panel.createPlayer(t);
			if(newPlayer != null) {
				players.add(newPlayer);
			}
		}
		System.out.println(players);
		return players;
	}
}
