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
    private Polygon[] p = new Polygon[2];
    private RectangularPrism self;

    double rotation = 0;
    int rotIncrement = 18;
    public double currentRotation = 0;

    int sideLen = 100;


    public Domino(Coordinate position, Kingdomino k, Color color1, Color color2) {
        super(position, k);
        double x = position.getX();
        double y = position.getY();
        BufferedImage b = null;
        try {
            b = ImageIO.read(new File("C:\\Users\\jonat\\Downloads\\rizz.png"));
        }catch(IOException e){;}
        Coordinate[] left = {
                new Coordinate(x,y-sideLen/2,0),
                new Coordinate(x-sideLen,y-sideLen/2,0),
                new Coordinate(x-sideLen,y+sideLen/2,0),
                new Coordinate(x,y+sideLen/2,0)
        };
        p[0] = new TexturedPolygon(left, position,b);
        p[0].setColor(color1);
        Coordinate[] right = {
                new Coordinate(x+sideLen,y-sideLen/2,0),
                new Coordinate(x,y-sideLen/2,0),
                new Coordinate(x,y+sideLen/2,0),
                new Coordinate(x+sideLen,y+sideLen/2,0)
        };
        p[1] = new TexturedPolygon(right, position,b);
        p[1].setColor(color2);
        self = new RectangularPrism(new Coordinate(800,400,0),sideLen*2,25,sideLen);
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

    public void rotateToNextPos(final int direction, final JPanel panel){
        final Timer timer = new Timer(1,null);
        currentRotation = rotation;
        rotation += 90*direction;
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentRotation += rotIncrement*direction;
                incrementRotation(0,0,direction*Math.toRadians(rotIncrement));
                panel.repaint();
                if(Math.abs(currentRotation - rotation) == 0)
                    timer.stop();

            }
        });
        timer.start();
    }

}
