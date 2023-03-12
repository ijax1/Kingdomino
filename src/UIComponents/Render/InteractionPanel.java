package UIComponents.Render;
import Backend.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import UIComponents.*;

public class InteractionPanel extends DynamicPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
    Domino domino = new Domino(new Tile(Tile.Land.LAKE,0),new Tile(Tile.Land.FOREST,0),13);
    UIDomino d = new UIDomino(new Coordinate(400,400,0),null,domino);
    Tile[][] init = new Tile[9][9];
    UIGrid grid = new UIGrid(new Coordinate(800,600,0),null, new Grid(init));
    RectangularPrism r = new RectangularPrism(new Coordinate(200,200,200),100,200,25);
    boolean dragging = false;
    boolean draggingCube = false;
    public InteractionPanel(JFrame frame) throws IOException {
        super(frame);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.addMouseWheelListener(this);
    }

    public void paintComponent(Graphics g){
        g.clearRect(0,0,10000,10000);
        if(!d.isRotating())
            checkDomino();
        grid.render(g, dragging);
        d.render((Graphics2D) g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(d.onComponent(new Coordinate(e.getX(),e.getY(),0))) {
            dragging = true;
        }
        if(dragging && e.getButton() == MouseEvent.BUTTON3 && !grid.isSnapped()){
            d.rotateToNextPos(1,this);
        }
        if(r.intersects(new Coordinate(e.getX(), e.getY(), 0))){
            draggingCube = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(dragging) {
            if (e.getButton() == MouseEvent.BUTTON1){
                dragging = false;
                if(grid.dominoOnGrid(d)) {
                    d.minimize();
                }
                else
                    d.show();
            }
            else{
                if(!d.onComponent(new Coordinate(e.getX(),e.getY(),0))) {
                    dragging = false;
                }
            }
        }
        draggingCube = false;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(dragging) {
            d.moveTo(new Coordinate(e.getX(), e.getY(), 0));
            d.setMouseLocation(new Coordinate(e.getX(), e.getY(), 0));
            if(!grid.dominoOnGrid(d))
                grid.setSnapped(false);
            repaint();

            grid.holdDomino(d, domino);
        }

        if(draggingCube) {
            r.moveTo(new Coordinate(e.getX(), e.getY(), 0));
            r.incrementRotation(Math.PI / 20, Math.PI / 40,Math.PI / 50);
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    double xRotation = 0, yRotation = 0, zRotation = 0;
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(dragging) {
            double direction = Math.signum(e.getWheelRotation());
            d.incrementRotation(direction * Math.PI/20,direction * Math.PI/30,direction * Math.PI/20);
            repaint();
        }
    }

    public void checkDomino(){
        if(grid.dominoOnGrid(d)) {
            d.minimize();
        }
        else
            d.show();
    }

    public static void main(String[] args) throws IOException {
        DynamicFrame d = new DynamicFrame();

        InteractionPanel ip = new InteractionPanel(d);
        d.add(ip);
        d.setVisible(true);
    }

}
