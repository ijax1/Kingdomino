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
	public TopScroll(String title, String subtitle, GameManager gm) {
		setPreferredSize(new Dimension(500,100));
	}
	@Override
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setFont(Resources.getLogoFontShadow(60));
		g.drawString("Kingdomino", 100, 100);
	}
}
