package UIComponents;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Backend.GameManager;
import resources.Resources;

public class TopScroll extends JPanel {
	private String title;
	private String subtitle;
	private int width;
	//private int height;
	
	public TopScroll(String title, String subtitle, GameManager gm) {
		setPreferredSize(new Dimension(500,100));
		
		width = 500;
		//height = 100; 
		
		this.title = title; 
		this.subtitle = subtitle; 
	}
	@Override
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		
		// need image file path - presumably called scroll.png or smth
		// first parameter should be the width of the screen/ 2 - width of the scroll
		//g.drawImage(new Image("scroll.png"), 600 - width/2, 0, null)
//		g.setFont(Resources.getLogoFontShadow(60));
		
// 		int titleWidth = g.getFontMetrics().stringWidth(title);
// 		int subWidth = g.getFontMetrics().stringWidth(subtitle);
		
		// 400 is the x coordinate of the scroll atm, third parameter is the y coordinate atm
// 		g.drawString(title, (400 + width/2 -titleWidth/2), 0);
// 		g.drawString(subtitle, (400 + width/2 - subWidth/2, 50); 
		
	}
}
