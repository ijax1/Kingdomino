package UIComponents;

import Backend.Kingdomino;
import UIComponents.Render.Coordinate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import UIComponents.Render.*;
import UIComponents.Render.Polygon;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Domino extends Component{
    private UITile[] tiles = new UITile[2];
    private RectangularPrism self;

    double rotateTo = 0;
    double rotIncrement = 90.0/4;
    public double currentRotation = 0;

    boolean rotating = false;

    double sideLen = 100;


    public Domino(Coordinate position, Kingdomino k, Color color1, Color color2) {
        super(position, k);
        double x = position.getX();
        double y = position.getY();
        tiles[0] = new UITile(Color.RED, new Coordinate(position.getX()-sideLen/2.0, position.getY(),position.getZ()+sideLen/8.0), (int) sideLen/2, position);
        tiles[1] = new UITile(Color.CYAN, new Coordinate(position.getX()+sideLen/2.0, position.getY(),position.getZ()+sideLen/8.0), (int) sideLen/2, position);

        Coordinate[] points = {
                new Coordinate(position.getX()-sideLen, position.getY()-sideLen/2.0,position.getZ()+sideLen/8.0),
                new Coordinate(position.getX()+sideLen, position.getY()-sideLen/2.0,position.getZ()+sideLen/8.0),
                new Coordinate(position.getX()+sideLen, position.getY()+sideLen/2.0,position.getZ()+sideLen/8.0),
                new Coordinate(position.getX()-sideLen, position.getY()+sideLen/2.0,position.getZ()+sideLen/8.0)
        };

        self = new RectangularPrism(new Coordinate(800,400,0),sideLen*2,25,sideLen);
        self.setFace(2,new CompoundPolygon(new Polygon[]{tiles[0].getPolygon(),tiles[1].getPolygon()},points,position));
    }

    @Override
    public void setPosition(Coordinate coordinate) {
        self.moveTo(coordinate);
    }

    @Override
    public boolean onComponent(Coordinate c) {
        return self.intersects(c);
    }

    @Override
    public void draw(Graphics2D g) {
        self.render(g);
    }

    @Override
    public void whenClicked() {

    }

    public void whenDragged(){

    }

    public void incrementRotation(double xRotation, double yRotation, double zRotation){
        self.incrementRotation(xRotation, yRotation, zRotation);
    }

    public void rotateToNextPos(final int direction, final JPanel panel) {
            rotateTo += 90 * direction;
        System.out.println(rotateTo);
        System.out.println(currentRotation);
        if (currentRotation%360 == (rotateTo-90)%360) {
            rotating = true;
            final Timer timer = new Timer(1, null);
            currentRotation = rotateTo - 90 * direction;
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentRotation += rotIncrement * direction;
                    incrementRotation(0, 0, direction * Math.toRadians(rotIncrement));
                    panel.repaint();
                    if (Math.abs(currentRotation%360 - rotateTo%360) == 0) {
                        timer.stop();
                        rotating = false;
                    }

                }
            });
            timer.start();
        }
    }

}
