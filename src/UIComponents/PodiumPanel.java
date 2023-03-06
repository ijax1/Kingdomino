package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Backend.GameManager;
import Backend.Kingdomino;
import UIComponents.Render.Coordinate;
import resources.OurColors;
import resources.Resources;

@SuppressWarnings("serial")
public class PodiumPanel extends JPanel implements MouseListener, MouseMotionListener {
	private ArrayList<Component>components = new ArrayList<Component>();
	private Font medievalLg;
	private CloseButton close;
	private PlayAgainButton playAgain;
	private GameManager gm;
	public PodiumPanel(Kingdomino k) {
		setPreferredSize(new Dimension(1280,720));
		setOpaque(true);
		setBackground(new Color(100,100,100));
		addMouseListener(this);
		addMouseMotionListener(this);
		gm = k.getManager();
		medievalLg = Resources.getMedievalFont(100);
		close = new CloseButton(new Coordinate(300,100,0),k);
		playAgain = new PlayAgainButton(new Coordinate(700,100,0),k);

		//button = new PlayerTabButton(new Coordinate(0,160,0), k, new Player());

		components.add(close);
		components.add(playAgain);
	}
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;

		GamePanel.applyHints(g);
		g.setColor(OurColors.BACKGROUND);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(OurColors.BACKGROUND_CIRCLE);
		g.fillOval(100,50,getWidth()-200, getHeight()-100);

		//g.setFont(medievalLg);
		for(Component component: components) {
			Graphics2D componentg = (Graphics2D) g.create();
			double x = component.getPosition().getX();
			double y = component.getPosition().getY();
			componentg.translate(x,y);
			component.draw(componentg);
		}
	}
	public void drawError(String header, String message) {

	}

	//Mouse, Key Event Handlers

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("Clicked at " + "X: " + x + ", Y:" + y);
		//TODO: this won't work, just a placeholder.
		Coordinate mouseCoord = new Coordinate(x,y,0);

		for(Component component: components) {
			if (component instanceof Button){
				//this won't work either, just a placeholder
				if(component.onComponent(mouseCoord)) {
					component.whenClicked();
				}
			}
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		int mousex = e.getX();
		int mousey = e.getY();
		System.out.println("mousex: "+mousex + " mousey: "+mousey);
	}
	public void mouseEvent(boolean isClicked, boolean isDragged, boolean isScrolling) {

	}
	public void keyEvent(int keyCode) {

	}


	//Empty methods
	@Override
	public void mouseClicked(MouseEvent e) {
		//this method is annoying, it only counts as clicked if you don't
		//move your mouse as all, i'm not using it

	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
