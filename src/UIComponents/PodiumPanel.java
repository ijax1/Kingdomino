package UIComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Backend.GameManager;
import Backend.GameManager.GameState;
import Backend.Kingdomino;
import Backend.Player;
import resources.OurColors;
import resources.Resources;

public class PodiumPanel extends JPanel {
	private BufferedImage playerImg;
	private BufferedImage computerImg;
	private BufferedImage noneImg;
	private CloseButton close;
	private JFrame root;
	GameManager gm;
	Kingdomino k;
	private ArrayList<Player> order;

	public PodiumPanel(GridBagLayout g, final Kingdomino kdomino) {
		GridBagConstraints c = new GridBagConstraints();
		k = kdomino;
		gm = k.getManager();
		order = k.getManager().getPlayers();
		// order.get(0).getName();
//		class first extends JPanel{
//			BufferedImage img;
//			Image newimg;
//			public first() {
//			img = Resources.loadImage("FirstPlace.png");
//			newimg = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
//            setPreferredSize(new Dimension(350, 500));
//			}
//			protected void paintComponent(Graphics g) {
//	            super.paintComponent(g);
//	            g.drawImage(newimg, 0, 0, null);
//	        }
//	        public boolean isOpaque() {
//	            return false;
//	        }
//		}
//		class second extends JPanel{
//			BufferedImage img = Resources.loadImage("FirstPlace.png");
//			Image newimg = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
//			protected void paintComponent(Graphics g) {
//	            super.paintComponent(g);
//	            g.drawImage(newimg, 0, 0, null);
//	        }
//	        public boolean isOpaque() {
//	            return false;
//	        }
//		}
//		class third extends JPanel{
//			BufferedImage img = Resources.loadImage("FirstPlace.png");
//			Image newimg = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
//			protected void paintComponent(Graphics g) {
//	            super.paintComponent(g);
//	            g.drawImage(newimg, 0, 0, null);
//	        }
//	        public boolean isOpaque() {
//	            return false;
//	        }
//		}
		JLabel scroll = new JLabel("", SwingConstants.CENTER);
		// scroll.setFont(Resources.getMedievalFont(50));
		// scroll.setForeground(OurColors.FONT_LIGHT);
		RoyalButton board = new RoyalButton("GO TO BOARD");
		RoyalButton exit = new RoyalButton("EXITETH");
		RoyalButton play = new RoyalButton("PLAYETH AGAIN");
		board.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GamePanel gp = k.getGamePanel();
				gm.setGameState(GameState.TALLY_SCORE);
				gp.setViewedPlayer(gm.getPlayers().get(0));
				gm.nextPlayer();
				gp.repaint();
				//gp.revalidate();
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gm.setGameState(GameState.INITIAL);
			}
		});

		// close = new CloseButton(new Coordinate(1200,800,0), null);

		// Settings for whole layout
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		c.anchor = GridBagConstraints.CENTER;
		
		c.ipady = 20;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		g.setConstraints(exit, c);
		add(exit);
		c.ipady = 20;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 2;
		g.setConstraints(board, c);
		add(board);

		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 2;
		g.setConstraints(play, c);
		add(play);
	}

	public void updatePlayers() {

	};

	public void paintComponent(Graphics g1) {
		order = k.getManager().getPlayers();
		Collections.sort(order, new Comparator<Player>() {
			public int compare (Player p1, Player p2) {
				int output = 0;
				if (p1.getScore() < p2.getScore()) {
					output = 1;
				} else if (p2.getScore() < p1.getScore()) {
					output = -1;
				}
			return output;
			}
		});
		Graphics2D g = (Graphics2D) g1;
		GamePanel.applyHints(g);
		// BufferedImage img = Resources.loadImage("PodiumScreen.png");
		// Image newimg = img.getScaledInstance(this.getWidth(), this.getHeight(),
		// Image.SCALE_SMOOTH);
		g.setColor(OurColors.BACKGROUND);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(OurColors.BACKGROUND_CIRCLE);
		g.fillOval(100, 50, getWidth() - 200, getHeight() - 100);
		// Resources.loadImage(order.get(0).getTitle());
		// g.drawImage(order.get(0), 0, 0, null);
		g.drawImage(toImage(Resources.loadImage("king_domino_scroll.png")), (int) (this.getWidth() * 0.13), 0, null);
		// g.drawImage(toImage(Resources.loadImage("king_domino_scroll.png")), 150/1266,
		// 0, null);
		/*
		 * BufferedImage icon1 = Resources.loadImage("player_icon_win.png"); Image
		 * newiconimage1 = icon1.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		 * g.drawImage(newiconimage1, 500, 190, null);
		 * 
		 * BufferedImage icon2 = Resources.loadImage("player_icon.png"); Image
		 * newiconimage2 = icon2.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		 * g.drawImage(newiconimage2, 160, 250, null);
		 * 
		 * BufferedImage icon3 = Resources.loadImage("computer_icon.png"); Image
		 * newiconimage3 = icon3.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		 * g.drawImage(newiconimage3, 850, 310, null);
		 * 
		 * BufferedImage img1 = Resources.loadImage("FirstPlace.png"); Image newimg1 =
		 * img1.getScaledInstance(350, 500, Image.SCALE_SMOOTH); int x1 = (getWidth() -
		 * newimg1.getWidth(null)) / 2; int y1 = getHeight() - newimg1.getHeight(null);
		 * g.drawImage(newimg1, x1, y1, null);
		 * 
		 * 
		 * BufferedImage img2 = Resources.loadImage("FirstPlace.png"); Image newimg2 =
		 * img2.getScaledInstance(350, 450, Image.SCALE_SMOOTH); int x2 = x1 -
		 * newimg2.getWidth(null); int y2 = y1+50; g.drawImage(newimg2, x2, y2, null);
		 * 
		 * BufferedImage img3 = Resources.loadImage("FirstPlace.png"); Image newimg3 =
		 * img3.getScaledInstance(350, 400, Image.SCALE_SMOOTH); int x3 = x1 +
		 * newimg2.getWidth(null); int y3 = y1+100; g.drawImage(newimg3, x3, y3, null);
		 */
		g.setColor(Color.white);
		g.setFont(Resources.getMedievalFont(40));
		for (int i = 0; i < order.size(); i++) {
			if (i == 0) {
				g.setColor(OurColors.YELLOW);
			} else if (i == 1) {
				g.setColor(Color.LIGHT_GRAY);
			} else if (i == 2) {
				g.setColor(OurColors.BRONZE);
			} else {
				g.setColor(Color.WHITE);
			}
			g.drawString(Integer.toString(i + 1) + ": " + order.get(i).getName() + " " + order.get(i).getTitle(), (int) (this.getWidth() * 0.2),
					250 + i * 100);
			g.drawString("Score " + order.get(i).getScore(), (int)
				(this.getWidth() * 0.65), 250 + i*100);
		}
	}

	private Image toImage(BufferedImage img) {
		Image image = img.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
		return image;
	}

}