package UIComponents.Render;

public class LineSegment {

    private final Coordinate start, end;

    private final double length;

    public LineSegment(Coordinate start, Coordinate end){
        this.start = start;
        this.end = end;
        this.length = start.distanceFrom(end);
    }

    /*
        Gets the next point on the line segment at an increment of (length * percent increment)
        Assumes the point is on the line segment.
     */
    public Coordinate getNextPoint(Coordinate c, double percentIncrement){
        double currentPercentage = start.distanceFrom(c)/this.length + percentIncrement;
        return new Coordinate(
                start.getX() + getXRange() * currentPercentage,
                start.getY() + getYRange() * currentPercentage,
                start.getZ() + getZRange() * currentPercentage
        );
    }
    public Coordinate getStart(){
        return start;
    }

    public Coordinate getEnd(){
        return end;
    }

    public double getLength(){
        return length;
    }

    public double getXRange(){
        return end.getX() - start.getX();
    }

    public double getYRange(){
        return end.getY() - start.getY();
    }

    public double getZRange(){
        return end.getZ() - start.getZ();
    }

    public boolean equals(LineSegment s){
        return s.getStart().equals(start) && s.getEnd().equals(end);
    }

    public boolean intersects(LineSegment ls){
        double distance = start.distanceFrom(ls.getStart());
        double delta = 0.0;
        int count = 0;
        int limit = 100000;

        Coordinate next = this.getStart();
        Coordinate otherNext = ls.getStart();
        while(delta <= 0.0 && !almostEqual(distance, 0.0, 0.01) && count < limit){
            if(next == null || otherNext == null)
                return false;
            double otherDistance = next.distanceFrom(otherNext);
            delta = otherDistance - distance;
            distance = otherDistance;
            next = this.getNextPoint(next, 1.0/limit);
            otherNext = ls.getNextPoint(otherNext, 1.0/limit);
            count++;;
        }
        if(almostEqual(distance, 0.0, 0.01))
            return true;
        return false;
    }

    public Coordinate intersection(LineSegment ls){
        double distance = start.distanceFrom(ls.getStart());
        double delta = 0.0;
        int count = 0;
        int limit = 100000;

        Coordinate next = this.getStart();
        Coordinate otherNext = ls.getStart();
        while(delta <= 0.0 && !almostEqual(distance, 0.0, 0.01) && count < limit){
            if(next == null || otherNext == null)
                return null;
            double otherDistance = next.distanceFrom(otherNext);
            delta = otherDistance - distance;
            distance = otherDistance;
            next = this.getNextPoint(next, 1.0/limit);
            otherNext = ls.getNextPoint(otherNext, 1.0/limit);
            count++;
        }

        return null;
    }

    private static boolean almostEqual(double a, double b, double eps){
        return Math.abs(a-b)<eps;
    }

    public String toString(){
        return start.toString() + " -> " + end.toString();
    }

    public static void main(String[] args) {
        LineSegment ls = new LineSegment(new Coordinate(0,0,0),new Coordinate(100,100,100));
        Coordinate s = ls.getStart();
        for(int i = 0; i < ls.getLength(); i++){
            s = ls.getNextPoint(s,0.01);
            System.out.println(s);
        }
    }
}
