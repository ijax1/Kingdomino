package UIComponents;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

import Backend.*;
import UIComponents.Render.Coordinate;
import UIComponents.Render.LineSegment;
import UIComponents.Render.RectangularPrism;
import resources.Resources;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
    private static final long serialVersionUID = 7381080659172927952L;

    Domino ref = new Domino(new Tile(Tile.Land.LAKE, 0), new Tile(Tile.Land.PASTURE, 0), 13);
    private ArrayList<Component> components = new ArrayList<Component>();
    private Font medieval;
    private Font medievalLg;
    private int mousedx;
    private int mousedy;
    private Domino domino;
    private int mousex, mousey;
    private PlayerTabGroup playerTabs;
    private PlayerTabButton playerTab;
    private ArrayList<UIPlayer> uiPlayers = new ArrayList<UIPlayer>(4);
    private int viewedPlayerIdx;
    private Banner banner;
    private FinishTurnButton finishTurn;
    private MessageTextBox textBox;
    private MinimizeComponentButton minimizeComp;
    private GameManager gm;
    private Kingdomino k;

    //From InteractionPanel
    private UIDomino d;
    private UIGrid grid;
    private ArrayList<UIGrid> grids;
    private RectangularPrism r = new RectangularPrism(new Coordinate(200, 200, 200), 100, 200, 25);
    boolean dragging = false;
    boolean draggingCube = false;

    boolean dominoButtonSelected = false;

    Image image = toImage(Resources.loadImage("title_scroll.png")).getScaledInstance(1100, 900, Image.SCALE_SMOOTH);

    public GamePanel(Kingdomino k) {
        setPreferredSize(new Dimension(1280, 720));
        setOpaque(true);
        setBackground(new Color(100, 100, 100));
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);
        gm = k.getManager();
        this.k = k;
        medieval = Resources.getMedievalFont(20);
        medievalLg = Resources.getMedievalFont(50);
        grids = new ArrayList<>();
        for (Player p : gm.getPlayers())
            grids.add(new UIGrid(new Coordinate(640, 320, 0), k, p.getGrid()));

        //From InteractionPanel
//        d = new UIDomino(new Coordinate(640, 50, 0), k, ref);
//        d.setMouseLocation(new Coordinate(640, 50, 0));
        setViewedPlayerIdx(0);
        updateUIPlayers();

        playerTabs = new PlayerTabGroup(gm.getPlayers(), k, this);
//        banner = new Banner(new Coordinate(Kingdomino.FRAME_WIDTH - 400, 50, 0), k, 4, this, gm.getDominoesToSelect());
        finishTurn = new FinishTurnButton(new Coordinate(540, 600, 0), k);


        textBox = new MessageTextBox(new Coordinate(100, 550, 0), k);
        //TODO: sorry, i can't provide a graphics to pass in here
        minimizeComp = new MinimizeComponentButton(new Coordinate(375, 575, 0), k, textBox);
        textBox.minimize();

        //button = new PlayerTabButton(new Coordinate(0,160,0), k, new Player());
//        setComponents();

    }

    // need to call later bc GamePanel is initialized before gm.getDominoes() works
    public void initDominoes() {
        banner = new Banner(new Coordinate(Kingdomino.FRAME_WIDTH - 400, 50, 0), k, 4, this, gm.getDominoesToSelect());
        setComponents();
    }

    private void setComponents() {
        components.add(playerTabs);
        components.add(banner);
        components.add(finishTurn);
        components.add(textBox);
        components.add(minimizeComp);
        //components.addAll(group.getButtons());
        components.addAll(banner.getButtons());
        components.add(d);
    }

    private void updateUIPlayers() {
        Coordinate gridCenter = new Coordinate(200, 300, 0);
        ArrayList<Player> players = gm.getPlayers();
//		for(int i=0; i<players.size(); i++) {
//			uiPlayers.add(new UIPlayer(gridCenter, k, players.get(i)));
//		}
    }

    public int getViewedPlayerIndex() {
        return viewedPlayerIdx;
    }

    public Player getViewedPlayerIdx() {
        return gm.getPlayers().get(viewedPlayerIdx);
    }

    public void setViewedPlayerIdx(int playerIdx) {
        viewedPlayerIdx = playerIdx;
        grid = grids.get(playerIdx);
        if (playerIdx != gm.getOrigPlayerIdx() && d != null) {
            d.minimize();
        }
        repaint();
    }

    public void finishTurn() {
        for (DominoButton b : banner.getButtons()) {
            if (b.isSelected() && !b.isLocked())
                b.setLocked();
        }
        //SORRY I HAD NO CHOICE
        Player p = k.getManager().getCurrentPlayer();
        Grid g = p.getGrid();

        if(p.getCurrentDomino() != null) {

            g.placeDomino(grid.getDominoLocation()[1], grid.getDominoLocation()[0], p.getCurrentDomino());
        }

    }

    public void changePlayer(Player player) {
        playerTabs.selectButton(player);
        grid = new UIGrid(new Coordinate(640, 320, 0), k, k.getManager().getCurrentPlayer().getGrid());
        grid = grids.get(gm.getOrigPlayerIdx());

        // first player
        if (gm.isFirstPlayer()) {
            banner.setDominoes(gm.getDominoesToSelect());
            for (DominoButton b : banner.getButtons()) {
                b.removePlayer();
            }
        }

        if (!gm.isFirstRound()) {
            d = new UIDomino(new Coordinate(640, 600, 0), k, player.getNextDomino());
            d.setMouseLocation(new Coordinate(640, 600, 0));
        } else {
            d = null;
        }
        repaint();
    }

    public void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        Player p = gm.getPlayers().get(viewedPlayerIdx);
        applyHints(g);
        Dimension size = super.getSize();
        //g.scale(size.width/1280.0, size.width/720.0);
        g.setColor(p.getColor().darker());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(p.getColor());
        g.fillOval(100, 50, getWidth() - 200, getHeight() - 100);
        g.drawImage(image, 350, 0, null);

        g.setFont(medievalLg);
        FontMetrics metrics = g.getFontMetrics(medievalLg);
        g.setColor(Color.BLACK);
        String playerName = gm.getPlayers().get(viewedPlayerIdx).getName();
        String playerTitle = gm.getPlayers().get(viewedPlayerIdx).getTitle();
        g.drawString(playerName, 390 + (475 - metrics.stringWidth(playerName)) / 2, 100);
        g.setFont(medieval);
        metrics = g.getFontMetrics(medieval);
        g.drawString(playerTitle, 390 + (475 - metrics.stringWidth(playerTitle)) / 2, 130);

        g.setFont(medieval);
        //From InteractionPanel
//        if (d != null) {
////            grid.holdDomino(d, ref);
//            grid.holdDomino(d, d.ref);
//        }
        grid.render(g.create(), dragging);
        checkDomino();
        //moved UIDomino draw to the component loop

        double startX = 0;
        double startY = 0;
        double width = 175;
        double height = 50;
        int filletRadius = 20;
        g.setStroke(new BasicStroke(3));
        g.setColor(new Color(241, 194, 50));
        g.drawRect((int) startX, (int) startY, (int) width - filletRadius / 2, (int) height);
        //g.drawOval((int) (startX+(width-filletRadius)), (int) startY, (int) filletRadius, (int) filletRadius);
        g.drawRect((int) (startX + (width - filletRadius)), (int) startY, (int) filletRadius, (int) height - (filletRadius / 2));
        g.drawOval((int) (startX + (width - filletRadius)), (int) (startY + (height - filletRadius)), (int) filletRadius, (int) filletRadius);

        g.setColor(new Color(140, 67, 188));
        g.fillRect((int) startX, (int) startY, (int) width - filletRadius / 2, (int) height);
        //g.fillOval((int) (startX+(width-filletRadius)), (int) startY, (int) filletRadius, (int) filletRadius);
        g.fillRect((int) (startX + (width - filletRadius)), (int) startY, (int) filletRadius, (int) height - (filletRadius / 2));
        g.fillOval((int) (startX + (width - filletRadius)), (int) (startY + (height - filletRadius)), (int) filletRadius, (int) filletRadius);

        g.setColor(new Color(241, 194, 50));
        g.drawString("SCORE: " + gm.getPlayers().get(viewedPlayerIdx).getScore(), 30, 30);

//		g.drawString("Hello world", 200,200);
//		g.fillOval(500, 500, 10, 10);
//		g.drawRect(480, 480, 40, 40);
        for (Component component : components) {
            //if (!component.isShown()) {
            //TODO: currently, every component uses the same graphics object. Is this ok?
            //We may need to copy the graphics object using g.create() or g.copyarea()
            if (component == null)
                continue;
            Graphics2D componentg = (Graphics2D) g.create();
            double x = component.getPosition().getX();
            double y = component.getPosition().getY();
            //componentg.translate(x,y);
            //System.out.println(component);
                component.draw(componentg);
            //}
        }
        if (d != null && viewedPlayerIdx == gm.getOrigPlayerIdx())
            d.render(g);
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

    public void checkDomino() {
        if (d != null) {
            if (grid.dominoOnGrid(d)) {
                d.minimize();
            } else
                d.show();
        }
    }

    //Mouse, Key Event Handlers
    @Override
    public void mousePressed(MouseEvent e) {
        //From InteractionPanel
        if (d != null && d.onComponent(new Coordinate(e.getX(), e.getY(), 0))) {
            dragging = true;
        }
        if (dragging && e.getButton() == MouseEvent.BUTTON3 && !grid.isSnapped()) {
            d.rotateToNextPos(1, this);
        }
        if (r.intersects(new Coordinate(e.getX(), e.getY(), 0))) {
            draggingCube = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
//		System.out.println("Clicked at " + "X: " + x + ", Y:" + y);

        //From InteractionPanel
        if (dragging) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                dragging = false;
                if (!grid.isSnapped()) {
                    d.setMouseLocation(new Coordinate(640, 600, 0));
                    d.moveTo(new Coordinate(640, 600, 0));
                }
                if (grid.dominoOnGrid(d)) {
                    d.minimize();
                } else
                    d.show();
            } else {
                if (!d.onComponent(new Coordinate(e.getX(), e.getY(), 0))) {
                    dragging = false;
                }
            }
        }
        draggingCube = false;

        //TODO: this won't work, just a placeholder.
        Coordinate mouseCoord = new Coordinate(x, y, 0);
        handleButtonClicks(mouseCoord);
        if (grid.isSnapped()) {
//			gm.getCurrentPlayer().placeDomino(0,0, d.getRef());
            int[] dominoLocation = grid.getDominoLocation();
            gm.getCurrentPlayer().placeDomino(dominoLocation[0], dominoLocation[1], d.getRef());
            gm.getCurrentPlayer().setPlaced(true);
        }

        repaint();

    }

    private void handleButtonClicks(Coordinate mouseCoord) {
        //if (!k.getManager().isStrategyMode()) {
        Component clickedComponent = null;
        for (Component component : components) {
            if (component != null && component.onComponent(mouseCoord)) {
                clickedComponent = component;
                break;
            }
        }
        if (clickedComponent instanceof Button) {
            if (clickedComponent instanceof DominoButton) {
                if (!((DominoButton) clickedComponent).isLocked()) {
                    for (DominoButton d : banner.getButtons()) {
                        if (d == clickedComponent) {
                            d.doAction();
                        } else if (!d.isLocked()) {
                            d.removePlayer();
                        }
                    }
                }
            } else {
                clickedComponent.whenClicked();
            }
        }
        if (clickedComponent instanceof PlayerTabGroup) {
            if (clickedComponent.onComponent(mouseCoord))
                ((PlayerTabGroup) clickedComponent).selectButton(mouseCoord);
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        mousex = e.getX();
        mousey = e.getY();
        //System.out.println("mousex: "+mousex + " mousey: "+mousey);

        //From InteractionPanel
        if(gm.getCurrentPlayer() == getViewedPlayerIdx())
            if (dragging) {
                d.moveTo(new Coordinate(e.getX(), e.getY(), 0));
                d.setMouseLocation(new Coordinate(e.getX(), e.getY(), 0));
                grid.holdDomino(d, d.ref);
                if (!grid.dominoOnGrid(d))
                    grid.setSnapped(false);
                repaint();

            }

        if (draggingCube) {
            r.moveTo(new Coordinate(e.getX(), e.getY(), 0));
            r.incrementRotation(Math.PI / 20, Math.PI / 40, Math.PI / 50);
            repaint();
        }

    }

    double xRotation = 0, yRotation = 0, zRotation = 0;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        //From InteractionPanel
        if (dragging) {
            System.out.println("mouse wheel");
            double direction = Math.signum(e.getWheelRotation());
            //d.rotateToNextPos((int) direction, this);
            //d.incrementRotation(0.04,0.04,0.04);
            repaint();
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
    }


    public void mouseEvent(boolean isClicked, boolean isDragged, boolean isScrolling) {

    }

    public void keyEvent(int keyCode) {

    }

    private void drawBg(Graphics2D g) {


    }

    private void drawStaticTiles() {

    }

    public void drawError(String header, String message) {

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
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public PlayerTabGroup getPlayerTabs() {
        return playerTabs;
    }

    private Image toImage(BufferedImage img) {
        Image image = img.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
        return image;
    }

    private void animateDomino(UIDomino d, Coordinate destination) {
        final LineSegment ls = new LineSegment(d.getCenter(), destination);
        final UIDomino domino = d;
        final Timer timer = new Timer(1, null);
        timer.addActionListener(new ActionListener() {
            Coordinate temp = ls.getStart();

            @Override
            public void actionPerformed(ActionEvent e) {
                temp = ls.getNextPoint(temp, 0.01);
                repaint();
                if (!ls.onLine(temp)) {
                    timer.stop();
                }

            }
        });
        timer.start();
    }

//    public void resetDominoButtons() {
//        for (DominoButton b : banner.getButtons()) {
//            b.
//        }
//    }
}
