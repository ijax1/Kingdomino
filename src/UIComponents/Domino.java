package UIComponents;

import Backend.Kingdomino;
import UIComponents.Render.Coordinate;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import UIComponents.Render.*;
import UIComponents.Render.Polygon;

import javax.imageio.ImageIO;

public class Domino extends Component{
    private Polygon[] p = new Polygon[2];

    double rotation = 0;

    int sideLen = 100;


    public Domino(Coordinate position, Kingdomino k, Color color1, Color color2) throws IOException {
        super(position, k);
        double x = position.getX();
        double y = position.getY();
        BufferedImage b = ImageIO.read(new File("C:\\Users\\jonat\\Downloads\\rizz.png"));
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
    }

    @Override
    public void setPosition(Coordinate coordinate) {
        p[0].moveTo(coordinate);
        p[1].moveTo(coordinate);
    }

    @Override
    public boolean onComponent(Coordinate c) {
        return p[0].intersects(c) || p[1].intersects(c);
    }

    @Override
    public void draw(Graphics2D g) {
        p[0].render(g);
        p[1].render(g);
        Coordinate c = p[0].getCenter();
        g.drawOval((int)c.getX()-10, (int)c.getY()-10,20,20);
    }

    @Override
    public void whenClicked() {

    }

    public void whenDragged(){

    }

    public void rotate(double xRotation, double yRotation, double zRotation){
        p[0].incrementRotation(xRotation, yRotation, zRotation);
        p[1].incrementRotation(xRotation, yRotation, zRotation);
    }

}
