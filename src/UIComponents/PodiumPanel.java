package UIComponents;
import java.util.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Backend.GameManager;
import Backend.Kingdomino;
import Backend.Player;
import resources.OurColors;
import resources.Resources;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.SwingConstants;

import Backend.GameManager.GameState;
import Backend.*;
import resources.*;
import resources.Titles;
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
		k=kdomino;
		gm=k.getManager();
		order=k.getManager().getPlayers();
		for(int i=0;i<order.size()-1;i++) {
			for(int j=0;j<order.size();j++) {
				if(order.get(i).getScore()<order.get(j).getScore()) {
					Player temp = order.get(i);
					order.set(i, order.get(j));
					order.set(j, temp);
				}
			}
		}
		//order.get(0).getName();
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
		
	}
	public void updatePlayers(){

	};
	public void paintComponent(Graphics g1) {
		order=k.getManager().getPlayers();
		for(int i=0;i<order.size()-1;i++) {
			for(int j=0;j<order.size();j++) {
				if(order.get(i).getScore()<order.get(j).getScore()) {
					Player temp = order.get(i);
					order.set(i, order.get(j));
					order.set(j, temp);
				}
			}
		}
		for(Player p: order) {
			System.out.print(p.getScore());
		}
		Graphics2D g = (Graphics2D)g1;
		GamePanel.applyHints(g);
		//BufferedImage img = Resources.loadImage("PodiumScreen.png");
		//Image newimg = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
		g.setColor(OurColors.BACKGROUND);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(OurColors.BACKGROUND_CIRCLE);
		g.fillOval(100,50,getWidth()-200, getHeight()-100);				
		//Resources.loadImage(order.get(0).getTitle());
        //g.drawImage(order.get(0), 0, 0, null);
		g.drawImage(toImage(Resources.loadImage("king_domino_scroll.png")), (int)(this.getWidth()*0.13), 0, null);
		//g.drawImage(toImage(Resources.loadImage("king_domino_scroll.png")), 150/1266, 0, null);
		/*
		BufferedImage icon1 = Resources.loadImage("player_icon_win.png");
        Image newiconimage1 = icon1.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        g.drawImage(newiconimage1, 500, 190, null);
		
		BufferedImage icon2 = Resources.loadImage("player_icon.png");
        Image newiconimage2 = icon2.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        g.drawImage(newiconimage2, 160, 250, null);
		
        BufferedImage icon3 = Resources.loadImage("computer_icon.png");
        Image newiconimage3 = icon3.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        g.drawImage(newiconimage3, 850, 310, null);
		
        BufferedImage img1 = Resources.loadImage("FirstPlace.png");
        Image newimg1 = img1.getScaledInstance(350, 500, Image.SCALE_SMOOTH);
        int x1 = (getWidth() - newimg1.getWidth(null)) / 2; 
        int y1 = getHeight() - newimg1.getHeight(null); 
        g.drawImage(newimg1, x1, y1, null);
        
        
        BufferedImage img2 = Resources.loadImage("FirstPlace.png");
        Image newimg2 = img2.getScaledInstance(350, 450, Image.SCALE_SMOOTH);
        int x2 = x1 - newimg2.getWidth(null);
        int y2 = y1+50; 
        g.drawImage(newimg2, x2, y2, null);

        BufferedImage img3 = Resources.loadImage("FirstPlace.png");
        Image newimg3 = img3.getScaledInstance(350, 400, Image.SCALE_SMOOTH);
        int x3 = x1 + newimg2.getWidth(null); 
        int y3 = y1+100; 
        g.drawImage(newimg3, x3, y3, null);
        */
		g.setColor(Color.white);
		g.setFont(Resources.getMedievalFont(40));
		for(int i=0;i<order.size();i++) {
			g.drawString(Integer.toString(1+i) +") "+ order.get(i).getName() + " Score " + order.get(i).getScore(), (int)(this.getWidth()*0.35), 250+ i*100);
		}
	}
	private Image toImage(BufferedImage img) {
        Image image = img.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
        return image;
    }

}
