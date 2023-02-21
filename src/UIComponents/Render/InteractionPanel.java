package UIComponents.Render;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import UIComponents.*;

public class InteractionPanel extends DynamicPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
    Domino d = new Domino(new Coordinate(400,400,0),null,new Color(0,255,0),new Color(255,0,255));
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
        d.draw((Graphics2D) g);
        r.render(g);
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
        if(dragging) {
            if (e.getButton() == MouseEvent.BUTTON3){
                d.rotate(0,0,Math.PI/2);
                repaint();
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(d.onComponent(new Coordinate(e.getX(),e.getY(),0))) {
            dragging = true;
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
            }
        }
        draggingCube = false;
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
            d.setPosition(new Coordinate(e.getX(), e.getY(), 0));
            repaint();
        }

        if(draggingCube) {
            r.moveTo(new Coordinate(e.getX(), e.getY(), 0));
            r.incrementRotation(Math.PI / 50, Math.PI / 40,Math.PI / 50);
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
            d.rotate(0,0, direction * Math.PI / 3);
            repaint();
        }
    }

    public static void main(String[] args) throws IOException {
        DynamicFrame d = new DynamicFrame();

        InteractionPanel ip = new InteractionPanel(d);
        d.add(ip);
        d.setVisible(true);
    }

}
