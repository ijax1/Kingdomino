package UIComponents.Render;
public class Polygon {
    private Coordinate[] points;
    private final Coordinate center;

    private final LineSegment[] lineSegments;


    public Polygon(Coordinate[] points, Coordinate center){
        this.points = points;
        this.center = center;
        this.lineSegments = calcLineSegments(points);
    }

    public Polygon(Coordinate[] points, Coordinate center, double xRotation, double yRotation, double zRotation){
        this(points, center);
        setToRotation(xRotation, yRotation, zRotation);
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

    public void setToRotation(double xRotation, double yRotation, double zRotation){
        Coordinate[] temp = new Coordinate[points.length];
        for(int i = 0; i < points.length; i++){
            Coordinate init = points[i];
            temp[i] = Coordinate.rotateAbout(center, init, xRotation, yRotation, zRotation);
        }
        this.points = temp;
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

        return false;
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

}
