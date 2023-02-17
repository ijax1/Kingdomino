package UIComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import UIComponents.Render.Coordinate;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
	private ArrayList<Component>components;
	public GamePanel() {
		
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for(Component component: components) {
			//TODO: currently, every component uses the same graphics object. Is this ok?
			//We may need to copy the graphics object using g.create() or g.copyarea()
			component.draw(g2);
		}
	}
	public void drawError(String header, String message) {
		
	}

	//Mouse, Key Event Handlers
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		//TODO: this won't work, just a placeholder.
		Coordinate mouse = new Coordinate(x,y,0);
		
		for(Component component: components) {
			if (component instanceof Button){
				if(component.getPosition().equals(mouse)) {
				component.whenClicked();
				}
			}
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	public void mouseEvent(boolean isClicked, boolean isDragged, boolean isScrolling) {
		
	}
	public void keyEvent(int keyCode) {
		
	}
	
	
	//Empty methods
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {	
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
