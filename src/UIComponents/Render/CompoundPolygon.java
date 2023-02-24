package UIComponents.Render;

import javax.sound.sampled.Line;
import java.awt.*;
import java.util.ArrayList;

public class CompoundPolygon extends Polygon{
    Polygon[] polygons;


    public CompoundPolygon(Polygon[] polys, Coordinate[] points, Coordinate center) {
        super(points, center);
        polygons = polys;
    }

    public void render(Graphics g){
        for(Polygon p: polygons){
            p.render(g);
        }
    }

    public void incrementRotation(double xRotation, double yRotation, double zRotation){
        for(Polygon p: polygons)
            p.incrementRotation(xRotation, yRotation, zRotation);
    }
    public void moveTo(Coordinate c){
        for(Polygon p: polygons)
            p.moveTo(c);
    }


}
