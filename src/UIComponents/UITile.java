package UIComponents;

import java.awt.*;
import UIComponents.Render.*;
import UIComponents.Render.Polygon;

public class UITile {
    Polygon p;

    Coordinate tileCenter;
    public static final int TILE_SIZE = 40;

    public UITile(Color c, Coordinate tileCenter, int radius, Coordinate center){
        this.tileCenter = tileCenter;
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
        Coordinate polyCenter = p.getCenter();
        tileCenter = tileCenter.translatedBy(
                c.getX() - polyCenter.getX(),
                c.getY() - polyCenter.getY(),
                c.getZ() - polyCenter.getZ()

        );
        p.moveTo(c);

    }

    public void recenter(Coordinate c){
        p.recenter(c);
    }

    public Polygon getPolygon(){
        return p;
    }

    public Coordinate getCenter(){
        return this.tileCenter;
    }

    public void incrementRotation(double xRotation, double yRotation, double zRotation){
        p.incrementRotation(xRotation, yRotation, zRotation);
        tileCenter = Coordinate.rotateAbout(p.getCenter(), tileCenter, xRotation, yRotation, zRotation);
    }

}
