package UIComponents;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class UIDomino extends JComponent implements MouseListener, MouseMotionListener, MouseWheelListener {
	int x, y, startScreenx, startScreeny, startx, starty;
	public UIDomino() {
		setPreferredSize(new Dimension(100,100));
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
	}
	
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D)g1;
		g.setStroke(new BasicStroke(3f));
		g.drawRect(5,5,85,85);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)) {
			System.out.println("right mouse");
		} else {
			startx = getX();
			starty = getY();
			startScreenx = e.getXOnScreen();
			startScreeny = e.getYOnScreen();
			System.out.println("X:"+startx+" Y:"+starty+" scrx:"+startScreenx+" scry:"+startScreeny);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//TODO: make these relative
		x=e.getXOnScreen();
		y=e.getYOnScreen();
//		x=getX();
//		y=getY();
		

		int dx = e.getXOnScreen()-startScreenx;
		int dy = e.getYOnScreen()-startScreeny;
//		if(x>1280) {
//			x=1280;
//			dx=0;
//		}
//		if(y > 720) {
//			y=720;
//			dy=0;
//		}
		System.out.println("X:"+x+" Y:"+y+"dx:"+dx+" dy:"+dy);
		
		//fix this snapping with relative coords
		if(closeTo(x, 500, 20) && closeTo(y, 500, 20)) {
			setLocation(500, 500);
		} else {
			setLocation(startx + dx, starty + dy);
		}
		
	}
	private boolean closeTo(int x, int num, int tolerance) {
		return (x>=num-tolerance && x<=num+tolerance);
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getWheelRotation());
	}
	
	//Empty methods
	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mouseMoved(MouseEvent e) {
	}
}
