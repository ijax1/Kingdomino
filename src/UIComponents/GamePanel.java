package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Backend.Kingdomino;
import UIComponents.Render.Coordinate;
import res.Resources;
class Dummy extends Component {
	Dummy(Coordinate position, Kingdomino k) {
		super(position, k);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setPosition(Coordinate coordinate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onComponent(Coordinate c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void whenClicked() {
		// TODO Auto-generated method stub
		
	}};
public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	private static final long serialVersionUID = 7381080659172927952L;
	private ArrayList<Component>components = new ArrayList<Component>();
	private Font medieval;
	private Font medievalLg;
	private int mousedx;
	private int mousedy;
	private UIDomino domino;
	private int mousex, mousey;
	
	public GamePanel() {
		setPreferredSize(new Dimension(1280,720));
		setOpaque(true);
		setBackground(new Color(100,100,100));
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		medieval = Resources.loadFont("fonts/MedievalSharp-Regular.ttf");
		medievalLg = medieval.deriveFont(100f);
		
		
		components.add(new Dummy(null,null));
		//components.add(new Hitbo)
	}
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, 1280, 720);
		
		if(medieval==null)System.out.println("true");
		g.setFont(medievalLg);
		g.drawString("Hello world", 200,200);
		for(Component component: components) {
			//TODO: currently, every component uses the same graphics object. Is this ok?
			//We may need to copy the graphics object using g.create() or g.copyarea()
			component.draw(g);
		}
	}
	private void drawBg(Graphics2D g) {
		

	}
	private void drawStaticTiles() {
		
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
				//this won't work either, just a placeholder
				if(component.getPosition().equals(mouse)) {
				component.whenClicked();
				}
			}
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		mousex = e.getX();
		mousey = e.getY();
		System.out.println("mousex: "+mousex + " mousey: "+mousey);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
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
