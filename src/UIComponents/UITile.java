package UIComponents;

import java.awt.*;
import UIComponents.Render.*;
import UIComponents.Render.Polygon;

public class UITile {
    Polygon p;

    public UITile(Color c, Coordinate tileCenter, int radius, Coordinate center){
        //TODO
        p = new Polygon(new Coordinate[]{
                new Coordinate(tileCenter.getX() - radius, tileCenter.getY() - radius, tileCenter.getZ()),
                new Coordinate(tileCenter.getX() + radius, tileCenter.getY() - radius, tileCenter.getZ()),
                new Coordinate(tileCenter.getX() + radius, tileCenter.getY() + radius, tileCenter.getZ()),
                new Coordinate(tileCenter.getX() - radius, tileCenter.getY() + radius, tileCenter.getZ()),
        }, center);
        p.setColor(c);
    }

    public void render(Graphics g){
        p.render(g);
    }

    public boolean onComponent(Coordinate c){
        return p.intersects(c);
    }

    public void moveTo(Coordinate c){
        p.moveTo(c);
    }

    public void recenter(Coordinate c){
        p.recenter(c);
    }

    public Polygon getPolygon(){
        return p;
    }
}
