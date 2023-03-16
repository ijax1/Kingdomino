package UIComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import Backend.Tile;
import Backend.Tile.Land;
import UIComponents.Render.Coordinate;
import UIComponents.Render.Polygon;
import UIComponents.Render.TexturedPolygon;
import resources.Resources;

import javax.imageio.ImageIO;

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

    public UITile(Tile t, Coordinate tileCenter, int radius, Coordinate center){
    	
    	this.tileCenter = tileCenter;
        Coordinate[] points = new Coordinate[]{
                new Coordinate(tileCenter.getX() - radius, tileCenter.getY() - radius, tileCenter.getZ()),
                new Coordinate(tileCenter.getX() + radius, tileCenter.getY() - radius, tileCenter.getZ()),
                new Coordinate(tileCenter.getX() + radius, tileCenter.getY() + radius, tileCenter.getZ()),
                new Coordinate(tileCenter.getX() - radius, tileCenter.getY() + radius, tileCenter.getZ()),
        };
        BufferedImage b = null;
        try {
        	String tile = t.getLandType().toFileName() + t.getCrowns() + ".png";	
        	b = Resources.loadImage("../resources/tile images/"+tile);
        	//b = Resources.loadImage("player_icon.png");
            System.out.println(tile);
        	//b = ImageIO.read(new File("\\resources\\tile images\\"+tile));

        	
        	//b = ImageIO.read(new File("resources\\player_icon.png"));
        }catch(Exception e){;}

        p = new TexturedPolygon(points,center,b);
        p.incrementRotation(0,0,Math.PI);
        p.setColor(t.getColor());
        System.out.println("COLOR + " + b.getRGB(40,40));

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
