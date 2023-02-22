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
        for(Polygon p: getVisible())
            p.render(g);
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
