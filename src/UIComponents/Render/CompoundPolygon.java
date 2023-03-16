package UIComponents.Render;

import javax.sound.sampled.Line;
//import javax.xml.soap.Text;
import java.awt.*;
import java.util.ArrayList;

public class CompoundPolygon extends Polygon{
    private Polygon[] polygons;



    public CompoundPolygon(Polygon[] polys, Coordinate[] points, Coordinate center) {
        super(points, center);
        //polygons = polys;
        polygons = new Polygon[polys.length];
        for(int i = 0; i< polys.length; i++){
            Polygon p = polys[i];
            p.recenter(center);
            polygons[i] = p;
        }
    }

    public void render(Graphics g){
        for(Polygon p: polygons){
            if(p instanceof TexturedPolygon)
                ((TexturedPolygon) p).render(g);
            else
                p.render(g);
        }
    }

    public void incrementRotation(double xRotation, double yRotation, double zRotation){
        for(Polygon p: polygons)
            p.incrementRotation(xRotation, yRotation, zRotation);
        super.incrementRotation(xRotation, yRotation, zRotation);
    }
    public void moveTo(Coordinate c){
        for(Polygon p: polygons)
            p.moveTo(c);
    }

    public LineSegment[] getLineSegments(){
        ArrayList<LineSegment> lines = new ArrayList<>();
        ArrayList<LineSegment> duplicates = new ArrayList<>();
        for(Polygon p: polygons){
            for(LineSegment ls: p.getLineSegments()){
                if(lines.contains(ls)){
                    duplicates.add(ls);
                    lines.remove(ls);
                }
                if(!duplicates.contains(ls)){
                    lines.add(ls);
                }
            }
        }
        LineSegment[] lineSegments = new LineSegment[lines.size()];
        for(int i = 0; i < lines.size(); i++){
            lineSegments[i] = lines.get(i);
        }
        return lineSegments;
    }

    public void addPolygon(Polygon p){
        Polygon[] temp = new Polygon[polygons.length+1];
        int index = 0;
        for(Polygon poly: polygons){
            temp[index] = poly;
            index++;
        }
        temp[index] = p;
        this.polygons = temp;
    }

}
