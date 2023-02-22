package UIComponents.Render;
public class Polygon {
    private Coordinate[] points;
    private final Coordinate center;


    public Polygon(Coordinate[] points, Coordinate center){
        this.points = points;
        this.center = center;
    }

    public Polygon(Coordinate[] points, Coordinate center, double xRotation, double yRotation, double zRotation){
        this(points, center);
        setToRotation(xRotation, yRotation, zRotation);
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

    /*
    Checks if a ray perpendicular to the XY plane at c intersects the polygon.
     */
    public boolean intersects(Coordinate c){

        return false;
    }

}
