package UIComponents;

import Backend.Kingdomino;
import UIComponents.Render.Coordinate;

import java.awt.*;
import UIComponents.Render.*;
import UIComponents.Render.Polygon;

public class Domino extends Component{
    private Polygon[] p = new Polygon[2];

    double rotation = 0;


    public Domino(Coordinate position, Kingdomino k, Color color1, Color color2) {
        super(position, k);
        double x = position.getX();
        double y = position.getY();
        Coordinate[] left = {
                new Coordinate(x,y-25,0),
                new Coordinate(x-50,y-25,0),
                new Coordinate(x-50,y+25,0),
                new Coordinate(x,y+25,0)
        };
        p[0] = new Polygon(left, position);
        p[0].setColor(color1);
        Coordinate[] right = {
                new Coordinate(x,y-25,0),
                new Coordinate(x+50,y-25,0),
                new Coordinate(x+50,y+25,0),
                new Coordinate(x,y+25,0)
        };
        p[1] = new Polygon(right, position);
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
    }

    @Override
    public void whenClicked() {

    }

    public void whenDragged(){

    }

    public void rotate(double xRotation, double yRotation, double zRotation){
        p[0].setToRotation(xRotation, yRotation, zRotation);
        p[1].setToRotation(xRotation, yRotation, zRotation);
    }

}
