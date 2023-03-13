package UIComponents;

import java.awt.Color;
import java.awt.Graphics;

import Backend.Tile.Land;
import UIComponents.Render.Coordinate;
import UIComponents.Render.Polygon;

public class UITile {
    Polygon p;

    Coordinate tileCenter;
    public static final int TILE_SIZE = 60;
    public UITile(Land l, Coordinate position) {
    	this(l.getColor(), position);
    }
    public UITile(Color c, Coordinate position) {
    	this(c, new Coordinate(position.getX(), position.getY(),position.getZ()), (int) TILE_SIZE/2, position);
    }
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
