package UIComponents.Render;

import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Polygon {
    private Coordinate[] points;
    private Coordinate center;

    private LineSegment[] lineSegments;

    private Color color = new Color(0,0,0);


    public Polygon(Coordinate[] points, Coordinate center){
        this.points = points;
        this.center = center;
        this.lineSegments = calcLineSegments(points);
    }

    public Polygon(Coordinate[] points, Coordinate center, double xRotation, double yRotation, double zRotation){
        this(points, center);
        incrementRotation(xRotation, yRotation, zRotation);
    }

    private LineSegment[] calcLineSegments(Coordinate[] points){
        LineSegment[] lines = new LineSegment[points.length];
        for(int i = 0; i < points.length; i++){
            lines[i] = new LineSegment(getPoint(i), getPoint(i+1));
        }
        return lines;
    }

    public Coordinate getPoint(int num){
        return points[num%points.length];
    }

    public void incrementRotation(double xRotation, double yRotation, double zRotation){
        Coordinate[] temp = new Coordinate[points.length];
        for(int i = 0; i < points.length; i++){
            Coordinate init = points[i];
            temp[i] = Coordinate.rotateAbout(center, init, xRotation, yRotation, zRotation);
        }
        this.points = temp;
        this.lineSegments = calcLineSegments(temp);
    }

    public void setColor(Color c){
        this.color = c;
    }
    public Color getColor(){
        return this.color;
    }

    public double getWidth(){
        Coordinate left = getPoint(0);
        Coordinate right = getPoint(0);
        for(Coordinate c: points){
            if(c.getX() < left.getX())
                left = c;
            else if(c.getX() > right.getX())
                right = c;
        }
        return  Math.abs(right.getX() - left.getX());
    }

    public double getHeight(){
        Coordinate top = getPoint(0);
        Coordinate bottom = getPoint(0);
        for(Coordinate c: points){
            if(c.getY() < bottom.getY())
                bottom = c;
            else if(c.getY() > top.getY())
                top = c;
        }
        return Math.abs(bottom.getY() - top.getY());
    }

    public LineSegment[] getLineSegments(){
        return lineSegments;
    }

    /*
    Checks if a ray perpendicular to the XY plane at c intersects the polygon.
     */
    public boolean intersects(Coordinate c){
        double leftX = Double.MAX_VALUE;
        double topY = Double.MIN_VALUE;

        for(Coordinate point: points){
            if(point.getX() < leftX)
                leftX =point.getX();
            if(point.getY() > topY)
                topY = point.getY();
        }
        Coordinate end = new Coordinate(leftX-1, topY+1, 0);
        LineSegment check = new LineSegment(end, c);
        int count = 0;
        for(LineSegment ls: lineSegments){
            if(!ls.meetsAtEndpoint(check))
                if(ls.intersect2D(check))
                    count++;
        }
        return count % 2 == 1;
    }

    public void render(Graphics g){
        int numPoints = points.length+1;
        int[] xPoints = new int[numPoints];
        int[] yPoints = new int[numPoints];
        for(int i = 0; i < points.length; i++){
            xPoints[i] = (int) points[i].getX();
            yPoints[i] = (int) points[i].getY();
        }
        xPoints[points.length] = xPoints[0];
        yPoints[points.length] = yPoints[0];
        g.setColor(this.color);
        g.fillPolygon(xPoints, yPoints, numPoints);
    }
    private Coordinate[] flatten(){
        Coordinate[] flat = new Coordinate[points.length];
        for(int i = 0; i < points.length; i++){
            flat[i] = new Coordinate(
                points[i].getX(),
                points[i].getY(),
                0
            );
        }
        return flat;
    }

    public void moveTo(Coordinate c){
        Coordinate[] temp = new Coordinate[points.length];
        double deltaX = c.getX() - center.getX();
        double deltaY = c.getY() - center.getY();
        double deltaZ = c.getZ() - center.getZ();
        for(int i = 0; i < points.length; i++){
            Coordinate change = points[i];
            temp[i] = new Coordinate(change.getX() + deltaX,
                    change.getY() + deltaY,
                    change.getZ() + deltaZ);
        }
        this.center = c;
        this.points = temp;
        this.lineSegments = calcLineSegments(temp);
    }

    public Coordinate getTopLeft(){
        Coordinate left = getPoint(0);
        Coordinate top = getPoint(0);
        for(Coordinate c: points){
            if(c.getX() < left.getX())
                left = c;
            if(c.getY() < top.getY())
                top = c;
        }
        return new Coordinate(left.getX(), top.getY(), 0);
    }

    public Coordinate[] getPoints(){
        return points;
    }

    public static void main(String[] args) {
        Coordinate[] points = {
                new Coordinate(0,0,0),
                new Coordinate(0,5,0),
                new Coordinate(5,5,0),
                new Coordinate(5,0,0),
        };
        Polygon p = new Polygon(points, new Coordinate(2.5,2.5,0));
    }

    public Coordinate getCenter() {
        return center;
    }

    public double getAverageZ(){
        double avg = 0;
        for(Coordinate c: points){
            avg += c.getZ();
        }
        System.out.println(avg/points.length);
        System.out.println(avg/points.length);
        return avg/points.length;
    }

    public TexturedPolygon toTexturedPolygon(BufferedImage b){
        return new TexturedPolygon(points, center, b);
    }

    public String toString(){
        StringBuilder ret = new StringBuilder();
        System.out.println("FACE START");
        for(Coordinate c: points){
            ret.append((int) c.getX()).append(" ").append((int) c.getY()).append(" ").append((int) c.getZ());
            ret.append(" ->\n");
        }
        return ret.toString();
    }

}
