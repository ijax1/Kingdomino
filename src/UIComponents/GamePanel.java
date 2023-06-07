package UIComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.Timer;

import Backend.ComputerPlayer;
import Backend.Domino;
import Backend.GameEventListener;
import Backend.GameManager;
import Backend.GameManager.GameState;
import Backend.HumanPlayer;
import Backend.Kingdomino;
import Backend.Player;
import Backend.Tile;
import UIComponents.Render.Coordinate;
import UIComponents.Render.LineSegment;
import UIComponents.Render.RectangularPrism;
import resources.Resources;

public class GamePanel extends JPanel implements GameEventListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private static final long serialVersionUID = 7381080659172927952L;

    private ArrayList<Component> components = new ArrayList<Component>();
    private Font medieval;
    private Font medievalLg;
    private PlayerTabGroup playerTabs;
    private int viewedPlayerIdx;
    private Banner banner;
    private FinishTurnButton finishTurn;
    private MessageTextBox textBox;
    private GameManager gm;
    private Kingdomino k;
    private int mousex, mousey;

    //From InteractionPanel
    private UIDomino d;
    private UIGrid uiGrid;
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

        playerTabs = new PlayerTabGroup(gm.getPlayers(), k, this);
//        banner = new Banner(new Coordinate(Kingdomino.FRAME_WIDTH - 400, 50, 0), k, 4, this, gm.getDominoesToSelect());
        finishTurn = new FinishTurnButton(new Coordinate(540, 600, 0), k);


        textBox = new MessageTextBox(new Coordinate(150, 600, 0), k);
        //TODO: sorry, i can't provide a graphics to pass in here
        //minimizeComp = new MinimizeComponentButton(new Coordinate(375, 575, 0), k, textBox);
        //textBox.minimize();

        //button = new PlayerTabButton(new Coordinate(0,160,0), k, new Player());
//        setComponents();

    }

    // need to call later bc GamePanel is initialized before gm.getDominoes() works
    public void initDominoes() {
        if(banner == null) {
            banner = new Banner(new Coordinate(Kingdomino.FRAME_WIDTH - 400, 50, 0), k, 4, this, gm.getDeck().getDominoesToSelect());
            setComponents();
        } else {
            banner.setDominoes(gm.getDeck().getDominoesToSelect());
        }
    }

    private void setComponents() {
        components.add(playerTabs);
        components.add(banner);
        components.add(finishTurn);
        components.add(textBox);
        //components.add(minimizeComp);
        //components.addAll(group.getButtons());
        components.addAll(banner.getButtons());
        components.add(d);
    }


    public int getViewedPlayerIndex() {
        return viewedPlayerIdx;
    }

    /**
     * @return
     */
    public Player getViewedPlayer() {
        return gm.getPlayers().get(viewedPlayerIdx);
    }

    public void setViewedPlayer(Player p) {
        ArrayList<Player> players = gm.getPlayers();
        int index = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(p.getName())) {
                index = i;
            }
        }
        setViewedPlayerIdx(index);
    }

    /**
     * Sets the viewed player to whatever index is specified
     *
     * @param playerIdx from 0-3. The player at index 0 is going first.
     */
    public void setViewedPlayerIdx(int playerIdx) {
        viewedPlayerIdx = playerIdx;
        uiGrid = grids.get(playerIdx);
        if (playerIdx != gm.getOrigPlayerIdx() && d != null) {
            d.minimize();
        }
        if (playerTabs != null)
            playerTabs.setSelected(gm.getPlayers().get(playerIdx));
        repaint();
    }

    public void finishTurn() {
        for (DominoButton b : banner.getButtons()) {
            if (b.isSelected() && !b.isLocked())
                b.setLocked();
        }
    }

    @Override
    public void onDominoSelected(Domino dominoSelected, boolean recallNextPlayer) {
        DominoButton dominoButton = null;
        System.out.println("dominoSelected: " + dominoSelected);
        System.out.println("dominos in banner:");
        for (DominoButton b : banner.getButtons()) {
            System.out.println(b.getUiDomino().getRef());
        }
        for (DominoButton b : banner.getButtons()) {
            if (b.getUiDomino().ref.equals(dominoSelected)) {
                dominoButton = b;
                break;
            }
        }
        if (dominoButton == null) {
//            return;
            throw new NullPointerException("dominoButton selected null");
        }
        if (!dominoButton.isLocked()) {
            for (DominoButton d : banner.getButtons()) {
                if (d == dominoButton) {
                    d.doAction();
                } else if (!d.isLocked()) {
                    d.removePlayer();
                }
            }
        }
    }

    @Override
    public void onNextPlayer() {
        if (gm.getCurrentPlayer() instanceof HumanPlayer) {
            System.out.println("is human player");
            changePlayer(gm.getCurrentPlayer());
        } else {
            changePlayer(gm.getCurrentPlayer());

            final Timer timer = new Timer(1, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                    if (gm.getCurrentPlayer().hasSelected()) {
                        timer.stop();
                    }

                }
            });
            timer.start();
        }
    }

    @Override
    public void onFinishTurn() {

    }

    public void changePlayer(Player player) {
        playerTabs.updatePlayers(gm.getPlayers(), gm.getPlayerOrder());
        playerTabs.selectButton(player);
        grids.set(gm.getOrigPlayerIdx(), new UIGrid(new Coordinate(640, 320, 0), k, k.getManager().getCurrentPlayer().getGrid()));
        uiGrid = grids.get(gm.getOrigPlayerIdx());
        for (UIGrid uigrid : grids) {
            uigrid.setSnapped(false);
        }

        // first player


        if (!gm.isFirstRound() && player.getNextDomino() != null) {
            d = new UIDomino(new Coordinate(640, 600, 0), k, player.getNextDomino());
            d.setMouseLocation(new Coordinate(640, 600, 0));
        } else {
            d = null;
        }
        if (gm.isFirstPlayer()) {
            for (DominoButton b : banner.getButtons()) {
                b.removePlayer();
            }
        }
        if (gm.isFirstPlayer()) {
            Domino[] toSelect = gm.getDeck().getAllDominoes();
            Arrays.sort(toSelect);
            int index = 3;
            for (DominoButton b : banner.getButtons()) {
                b.setDomino(toSelect[index]);
                index--;
            }
        }
        repaint();
    }

    public void paintComponent(Graphics g1) {
        if (gm.getGameState() == GameState.PLAYER_TURN || gm.getGameState() == GameState.TALLY_SCORE) {
            Graphics2D g = (Graphics2D) g1;
            Player p = getViewedPlayer();
            applyHints(g);
            Dimension size = super.getSize();
            //g.scale(size.width/1280.0, size.width/720.0);
            g.setColor(p.getColor().darker());
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(p.getColor());
            g.fillOval(100, 50, getWidth() - 200, getHeight() - 100);
            g.drawImage(image, 350, 0, null);

            drawPlayerInfo(g);
            drawPlayerInstructions(g);

            uiGrid.render(g.create(), dragging);
            checkDomino();

            drawScore(g);
            drawAllComponents(g);
            renderDomino(g);
        }
        //TODO: Implement tally_score game state!
    }

    private void drawAllComponents(Graphics2D g) {
        for (Component component : components) {
            //if (!component.isShown()) {
            if (component == null)
                continue;
            Graphics2D componentg = (Graphics2D) g.create();
            double x = component.getPosition().getX();
            double y = component.getPosition().getY();
            //componentg.translate(x,y);
            component.draw(componentg);
            //}
        }
    }

    private void drawScore(Graphics2D g) {
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
    }

    private void drawPlayerInstructions(Graphics2D g) {
        g.setFont(medieval);
        g.setColor(Color.WHITE);
        if (!gm.isFirstRound() && gm.getPlayers().get(viewedPlayerIdx) == gm.getCurrentPlayer() && !gm.getCurrentPlayer().hasPlaced()) {
            g.drawString("Placeth thine tile, your grace!", 950, 605);
            //where thy kingdom meets the lawless frontier
        }
        if (gm.getPlayers().get(viewedPlayerIdx) == gm.getCurrentPlayer() && !gm.getCurrentPlayer().hasSelected()) {
            g.drawString("Selecteth thine tile, your majesty!", 950, 635);
            //to stake claim to new territory!
        }
    }

    private void drawPlayerInfo(Graphics2D g) {
        g.setFont(medievalLg);
        FontMetrics metrics = g.getFontMetrics(medievalLg);
        g.setColor(Color.BLACK);
        String playerName = gm.getPlayers().get(viewedPlayerIdx).getName();
        String playerTitle = gm.getPlayers().get(viewedPlayerIdx).getTitle();
        g.drawString(playerName, 390 + (475 - metrics.stringWidth(playerName)) / 2, 100);
        g.setFont(medieval);
        metrics = g.getFontMetrics(medieval);
        g.drawString(playerTitle, 390 + (475 - metrics.stringWidth(playerTitle)) / 2, 130);
    }

    private void renderDomino(Graphics2D g) {
        if (d != null && viewedPlayerIdx == gm.getOrigPlayerIdx()) {
            if (!gm.getCurrentPlayer().hasPlaced()) {
                d.render(g);
            }
        } else if (d != null && viewedPlayerIdx != gm.getOrigPlayerIdx()) {
            if (!gm.getCurrentPlayer().hasPlaced()) {
                new UIDomino(new Coordinate(640, 600, 0), k, getViewedPlayer().getNextDomino()).render(g);
            }
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

    private void checkDomino() {
        if (d != null) {
            if (uiGrid.dominoOnGrid(d)) {
                d.minimize();
            } else
                d.show();
        }
    }

    //Mouse, Key Event Handlers
    @Override
    public void mousePressed(MouseEvent e) {
        //From InteractionPanel
        if (gm.getCurrentPlayer() instanceof HumanPlayer) {
            if (d != null && d.onComponent(new Coordinate(e.getX(), e.getY(), 0))) {
                dragging = true;
            }
            if (dragging && e.getButton() == MouseEvent.BUTTON3 && !uiGrid.isSnapped()) {
                d.rotateToNextPos(1, this);
            }
            if (r.intersects(new Coordinate(e.getX(), e.getY(), 0))) {
                draggingCube = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //used to not activate buttons when tile released
        boolean startedDragging = dragging;
        if (gm.getCurrentPlayer() instanceof HumanPlayer) {
            if (dragging) {
                handleDragging(e);
            }
            draggingCube = false;

            if (!gm.isFirstRound() && gm.getCurrentPlayer().hasLegalMoves(false)) {
                getViewedPlayer().setPlaced(uiGrid.isSnapped());
            }
            repaint();
        }
        Coordinate mouseCoord = new Coordinate(x, y, 0);
        System.out.println("c");
        if (!startedDragging)
            handleButtonClicks(mouseCoord);
        repaint();
    }

    private void handleDragging(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            dragging = false;
            if (!uiGrid.isSnapped()) {
                resetDominoLocation();
            }
            if (uiGrid.dominoOnGrid(d)) {
                d.minimize();
            } else
                d.show();
        } else {
            if (!d.onComponent(new Coordinate(e.getX(), e.getY(), 0))) {
                dragging = false;
            }
        }
    }

    private void resetDominoLocation() {
        d.setMouseLocation(new Coordinate(640, 600, 0));
        d.moveTo(new Coordinate(640, 600, 0));
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
        if (clickedComponent instanceof Button || clickedComponent instanceof Banner) {
            if (clickedComponent instanceof DominoButton) {
                if (gm.getCurrentPlayer() instanceof HumanPlayer && !((DominoButton) clickedComponent).isLocked()) {
                    for (DominoButton d : banner.getButtons()) {
                        if (d == clickedComponent) {
                            d.doAction();
                        } else if (!d.isLocked()) {
                            d.removePlayer();
                        }
                    }
                }
            } else {
                if(clickedComponent instanceof FinishTurnButton) System.out.println("Clicked!");
                clickedComponent.whenClicked();
            }
        }
        if (clickedComponent instanceof PlayerTabGroup) {
            if (clickedComponent.onComponent(mouseCoord))
                ((PlayerTabGroup) clickedComponent).selectButton(mouseCoord);
        }
        if (clickedComponent instanceof MessageTextBox) {
            clickedComponent.whenClicked();
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        mousex = e.getX();
        mousey = e.getY();

        //From InteractionPanel
        if ((uiGrid.dominoOnGrid(d) && !finishTurn.onComponent(new Coordinate(mousex, mousey, 0))) || (!uiGrid.dominoOnGrid(d))) {
            if (gm.getCurrentPlayer() instanceof HumanPlayer) {
                if (gm.getCurrentPlayer() == getViewedPlayer())
                    if (dragging) {
                        d.moveTo(new Coordinate(e.getX(), e.getY(), 0));
                        d.setMouseLocation(new Coordinate(e.getX(), e.getY(), 0));
                        uiGrid.holdDomino(d, d.ref);
                        if (!uiGrid.dominoOnGrid(d))
                            uiGrid.setSnapped(false);
                        repaint();

                    }

                if (draggingCube) {
                    r.moveTo(new Coordinate(e.getX(), e.getY(), 0));
                    r.incrementRotation(Math.PI / 20, Math.PI / 40, Math.PI / 50);
                    repaint();
                }
            }
        }

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        //From InteractionPanel
        if (dragging) {
            double direction = Math.signum(e.getWheelRotation());
            //d.rotateToNextPos((int) direction, this);
            //d.incrementRotation(0.04,0.04,0.04);
            repaint();
        }

    }

    public void mouseEvent(boolean isClicked, boolean isDragged, boolean isScrolling) {

    }

    //Empty methods
    @Override
    public void mouseClicked(MouseEvent e) {
        //this method is annoying, it only counts as clicked if you don't
        //move your mouse as all, i'm not using it

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

    //should only be called when can be placed and player is computer
    private void animateComputerPlayer() {
        ComputerPlayer p = (ComputerPlayer) gm.getCurrentPlayer();

        UIGrid g = this.uiGrid;
        final Coordinate destination = null;
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


    public UIGrid getUIGrid() {
        return uiGrid;
    }

    @Override
    public void onStateChangedTo(GameState state) {
        // TODO Auto-generated method stub

    }


    public void lostFocus() {
        if (gm.getCurrentPlayer() instanceof HumanPlayer && dragging && !uiGrid.isSnapped()) {
            dragging = false;
            resetDominoLocation();
        }
    }
}
