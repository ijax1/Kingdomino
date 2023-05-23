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

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Backend.ComputerPlayer;
import Backend.GameManager;
import Backend.GameManager.GameState;
import Backend.Kingdomino;
import Backend.Player;
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



	public AnalysisPanel(GridBagLayout g, final Kingdomino kingdomino) {
		super(g);
		//setBackground(OurColors.BACKGROUND);
		GridBagConstraints c = new GridBagConstraints();
		this.kingdomino = kingdomino;


		ArrayList<Player> players = kingdomino.getManager().getPlayers();
		int totalPlayers = kingdomino.getManager().getPlayers().size();
		int totalGames = kingdomino.getManager().getNumGames();
		//order of arraylist: player 0 ++ ...
		ArrayList<String> strategyType = new ArrayList<String>();
		ArrayList<Integer> percentageWon = new ArrayList<Integer>();
		GameManager gm = kingdomino.getManager();


			//VALUES:

			//total games won for each player:
//			for (int y = 0; y<totalPlayers; y++) {
//				int playerWinCount = 0;
//				for (int x = 0; x<totalGames; x++) {
//					if(y==kingdomino.getManager().getWinners().get(x)) {
//						playerWinCount = playerWinCount ++;
//					}
//				}
//				playerWins.add(playerWinCount);
//			}

			for (int x = 0; x<totalPlayers; x++) {
				//totalGames++;
				int percentage = 100;
				if(totalGames != 0)
					percentage = gm.getWinners().get(x)/totalGames;
				percentageWon.add(percentage);
			}
			for (int x = 0; x<totalPlayers; x++) {
				String stratType;
				if(players.get(x) instanceof ComputerPlayer) {
					stratType = ((ComputerPlayer) players.get(x)).getStrategyName();
					strategyType.add(stratType);
				}
			}
			//BOX 1 INFO:
			JPanel box1 = new JPanel();
			box1.setSize(width, height);
			box1.setLayout(new BoxLayout(box1, BoxLayout.PAGE_AXIS));
			if(strategyType.size() >= 0+1 && strategyType.get(0) != null) {
				JLabel player0Strat = new JLabel(strategyType.get(0), SwingConstants.CENTER);
				box1.add(player0Strat);
				JLabel player0GamesPlayed = new JLabel("Games played: " + totalGames, SwingConstants.CENTER);
				box1.add(player0GamesPlayed);
				JLabel player0GamesWon = new JLabel("Games won: " + gm.getWinners().get(0), SwingConstants.CENTER);
				box1.add(player0GamesWon);
				JLabel player0PercentageWon = new JLabel("Percentage won: " + percentageWon.get(0), SwingConstants.CENTER);
				box1.add(player0PercentageWon);
			}

			JPanel box2 = new JPanel();
			box2.setSize(width, height);
			box2.setLayout(new BoxLayout(box2, BoxLayout.PAGE_AXIS));
			if(strategyType.size() >= 1+1 && strategyType.get(1) != null) {
				JLabel player1Strat = new JLabel(strategyType.get(1), SwingConstants.CENTER);
				box2.add(player1Strat);
				JLabel player1GamesPlayed = new JLabel("Games played: " + totalGames, SwingConstants.CENTER);
				box2.add(player1GamesPlayed);
				JLabel player1GamesWon = new JLabel("Games won: " + gm.getWinners().get(1), SwingConstants.CENTER);
				box2.add(player1GamesWon);
				JLabel player1PercentageWon = new JLabel("Percentage won: " + percentageWon.get(1), SwingConstants.CENTER);
				box2.add(player1PercentageWon);
			}


			JPanel box3 = new JPanel();
			box3.setSize(width, height);
			box3.setLayout(new BoxLayout(box3, BoxLayout.PAGE_AXIS));

			if(strategyType.size() >= 2+1 && strategyType.get(2) != null) {
				JLabel player2Strat = new JLabel(strategyType.get(2), SwingConstants.CENTER);
				box3.add(player2Strat);
				JLabel player2GamesPlayed = new JLabel("Games played: " + totalGames, SwingConstants.CENTER);
				box3.add(player2GamesPlayed);
				JLabel player2GamesWon = new JLabel("Games won: " + gm.getWinners().get(2), SwingConstants.CENTER);
				box3.add(player2GamesWon);
				JLabel player2PercentageWon = new JLabel("Percentage won: " + percentageWon.get(2), SwingConstants.CENTER);
				box3.add(player2PercentageWon);
			}


			JPanel box4 = new JPanel();
			box4.setSize(width, height);
			box4.setLayout(new BoxLayout(box4, BoxLayout.PAGE_AXIS));

			if(strategyType.size() >= 3+1 && strategyType.get(3) != null) {
				JLabel player3Strat = new JLabel(strategyType.get(3), SwingConstants.CENTER);
				box4.add(player3Strat);
				JLabel player3GamesPlayed = new JLabel("Games played: " + totalGames, SwingConstants.CENTER);
				box4.add(player3GamesPlayed);
				JLabel player3GamesWon = new JLabel("Games won: " + gm.getWinners().get(3), SwingConstants.CENTER);
				box4.add(player3GamesWon);
				JLabel player3PercentageWon = new JLabel("Percentage won: " + percentageWon.get(3), SwingConstants.CENTER);
				box4.add(player3PercentageWon);
			}
			
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
