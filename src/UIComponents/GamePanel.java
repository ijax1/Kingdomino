package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Backend.GameManager;
import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;
import UIComponents.Render.RectangularPrism;
import resources.OurColors;
import resources.Resources;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	private static final long serialVersionUID = 7381080659172927952L;
	private ArrayList<Component>components = new ArrayList<Component>();
	private Font medieval;
	private Font medievalLg;
	private int mousedx;
	private int mousedy;
	private UIDomino domino;
	private int mousex, mousey;
	private PlayerTabGroup group;
	private PlayerTabButton playerTab;
	private Banner banner;
	private FinishTurnButton finishTurn;
	private MessageTextBox textBox;
	private MinimizeComponentButton minimizeComp;
	private GameManager gm;
	
	//From InteractionPanel
	UIDomino d = new UIDomino(new Coordinate(400,400,0),null,new Color(0,255,0),new Color(255,0,255));
    UIGrid grid = new UIGrid(new Coordinate(800,600,0),null);
    RectangularPrism r = new RectangularPrism(new Coordinate(200,200,200),100,200,25);
    boolean dragging = false;
    boolean draggingCube = false;

	public GamePanel(ArrayList<Player> tempPlayers, Kingdomino k) {
		setPreferredSize(new Dimension(1280,720));
		setOpaque(true);
		setBackground(new Color(100,100,100));
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		gm = k.getManager();
		medieval = Resources.getMedievalFont(20);
		medievalLg = Resources.getMedievalFont(100);
		

	    
		group = new PlayerTabGroup(tempPlayers,k);
		banner = new Banner(new Coordinate(1100,100,0), k);
		finishTurn = new FinishTurnButton(new Coordinate(0,0,0),k);
		
		textBox = new MessageTextBox(new Coordinate(200,400,0),k);
		//TODO: sorry, i can't provide a graphics to pass in here
		//and this can be switched to relative coordinates
		minimizeComp = new MinimizeComponentButton(new Coordinate(200,400,0), k, null, textBox);
		textBox.minimize();
		
		//button = new PlayerTabButton(new Coordinate(0,160,0), k, new Player());

		components.add(group);
		components.add(banner);
		components.add(finishTurn);
		components.add(textBox);
		components.add(minimizeComp);
		//components.add(new Hitbo)
	}
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;

		applyHints(g);
		g.setColor(OurColors.BACKGROUND);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(OurColors.BACKGROUND_CIRCLE);
		g.fillOval(100,50,getWidth()-200, getHeight()-100);
		
        //From InteractionPanel
        grid.render(g, dragging);
        checkDomino();
        d.draw((Graphics2D) g);
        
		//close.draw((Graphics2D) g.create());
		//AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
		//g.setComposite(ac);
		//player = tint(player, new Color(0xe06666));
		//g.drawImage(player, 100,100,200,200, null);
		//g.drawImage(player, 100,100,100,100,null);
		g.setFont(medieval);
//		g.drawString("Hello world", 200,200);
//		g.fillOval(500, 500, 10, 10);
//		g.drawRect(480, 480, 40, 40);
		for(Component component: components) {
			//TODO: currently, every component uses the same graphics object. Is this ok?
			//We may need to copy the graphics object using g.create() or g.copyarea()
			Graphics2D componentg = (Graphics2D) g.create();
			double x = component.getPosition().getX();
			double y = component.getPosition().getY();
			componentg.translate(x,y);
			component.draw(componentg);
		}
	}
    public static void applyHints(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }
	private void drawBg(Graphics2D g) {


	}
	private void drawStaticTiles() {

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
	public void mouseClicked(MouseEvent e) {
		//this method is annoying, it only counts as clicked if you don't
		//move your mouse as all, i'm not using it

	}
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
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
