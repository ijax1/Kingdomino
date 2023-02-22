package UIComponents.Render;

import java.awt.*;
import java.io.IOException;

public abstract class Polyhedron {

    Polyhedron(){
    }
    public void incrementRotation(double xRotation, double yRotation, double zRotation){
        for(Polygon face: getFaces())
            face.incrementRotation(xRotation,yRotation,zRotation);
    }

    public void render(Graphics g){
        Polygon[] polys = getVisible();
        for(Polygon p: polys) {
            p.render(g);
        }
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.BLACK);
        g2D.setStroke(new BasicStroke(3.0f));
        for(Polygon p: polys){
            for(LineSegment ls: p.getLineSegments()){
                g2D.drawLine((int)ls.getStart().getX(),(int)ls.getStart().getY(),(int)ls.getEnd().getX(),(int)ls.getEnd().getY());
            }
        }
    }

    abstract Polygon[] calcFaces() throws IOException;

    abstract Polygon[] getFaces();

    abstract Polygon[] getVisible();

    public boolean intersects(Coordinate c){
        for(Polygon p: getFaces())
            if(p.intersects(c))
                return true;
        return false;
    }




}
