 package UIComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Backend.GameManager;
import Backend.Kingdomino;
import resources.OurColors;
import resources.Resources;

public class PodiumPanel extends JPanel {
	private BufferedImage playerImg;
	private BufferedImage computerImg;
	private BufferedImage noneImg;
	private CloseButton close;
	private JFrame root;
	GameManager gm;
	private PlayerSelectPanel[]playerPanels = new PlayerSelectPanel[4];
	public PodiumPanel(GridBagLayout g, Kingdomino k) {
//		super(g);
//		GridBagConstraints c = new GridBagConstraints();
//		gm = k.getManager();
//		gm.getPlayers().get(0).getScore();
//		
//		playerPanels[0] = new PlayerSelectPanel(OurColors.RED, 1, PlayerSelectPanel.HUMAN, k);
//		playerPanels[1] = new PlayerSelectPanel(OurColors.BLUE, 2, PlayerSelectPanel.COMPUTER, k);
//		playerPanels[2] = new PlayerSelectPanel(OurColors.GREEN, 3, PlayerSelectPanel.COMPUTER, k);
//		playerPanels[3] = new PlayerSelectPanel(OurColors.YELLOW, 4, PlayerSelectPanel.COMPUTER, k);
//		//JButton scrollB = new JButton("Quiteth");
//		JLabel scroll = new JLabel("Kingdomino", SwingConstants.CENTER);
//		scroll.setFont(Resources.getMedievalFont(50));
//		scroll.setForeground(OurColors.FONT_LIGHT);
//		RoyalButton exit = new RoyalButton("Exiteth");
//		RoyalButton play = new RoyalButton("Playeth");
//		ImageIcon PodiumScreen = new ImageIcon("PodiumScreen.png");
//		exit.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}	
//		});
//		play.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ArrayList<Player> players = getAllPlayers();
//				if(players.size() < 2) {
//					new ErrorDialog(root);
//				} else {
//					gm.setPlayers(players);
//					gm.setGameState(GameState.PLAYER_TURN);
//				}
//			}	
//		});
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx=0.5;
//		c.gridheight = 1;
//		c.insets = new Insets(10,10,10,10);
//		c.anchor = GridBagConstraints.CENTER;
//		c.ipady = 20;
//		c.gridx = 0;
//		c.gridy = 2;
//		c.gridwidth = 2;
//		g.setConstraints(exit, c);
//		add(exit);
//		
//		c.gridx = 2;
//		c.gridy = 2;
//		c.gridwidth = 2;
//		g.setConstraints(play, c);
//		add(play);
//		
//
//		c.gridx = 2;
//		c.gridy = 2;
//		c.gridwidth = 2;
//		g.setConstraints(play, c);
		
		
		
	}
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D)g1;
		GamePanel.applyHints(g);
		BufferedImage img = Resources.loadImage("PodiumScreen.png");
		Image newimg = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
		g.setColor(OurColors.BACKGROUND);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(OurColors.BACKGROUND_CIRCLE);
		g.fillOval(100,50,getWidth()-200, getHeight()-100);
		g.drawImage(newimg, 0, 0, null);
	}
//	private ArrayList<Player> getAllPlayers(){
//		ArrayList<Player>players = new ArrayList<Player>();
//		for(PlayerSelectPanel panel: playerPanels) {
//			Player newPlayer = panel.createPlayer();
//			if(newPlayer != null) {
//				players.add(newPlayer);
//			}
//		}
//		return players;
//	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Podium Panel");
		PodiumPanel panel = new PodiumPanel(new GridBagLayout(), new Kingdomino());
		frame.setSize(1280,720);
		frame.add(panel);

		
		//frame.pack();
		frame.setVisible(true);
		panel.repaint();
		new ErrorDialog(frame);
	}
}
