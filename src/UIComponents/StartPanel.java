package UIComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JPanel;

import resources.Resources;

public class StartPanel extends JPanel {
	BufferedImage player;
	BufferedImage computer;
	BufferedImage none;
	
	public StartPanel() {
		player = Resources.loadImage("player_icon.png");
		computer = Resources.loadImage("computer_icon.png");
		none = Resources.loadImage("none_icon.png");
	}
	@Override
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D)g1;
		Graphics2D g2 = player.createGraphics();
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
