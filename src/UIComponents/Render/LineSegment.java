package UIComponents.Render;

import javax.sound.sampled.Line;

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


    public boolean equals(Object o){
        LineSegment s = (LineSegment) o;
        return (s.getStart().equals(start) && s.getEnd().equals(end)) || (s.getStart().equals(end) && s.getEnd().equals(start));
}

    public boolean onLine(Coordinate check){
        if (check.getX() <= Math.max(start.getX(), end.getX()) && check.getX() >= Math.min(start.getX(), end.getX()) &&
            check.getY() <= Math.max(start.getY(), end.getY()) && check.getY() >= Math.min(start.getY(), end.getY()) &&
            check.getZ() <= Math.max(start.getZ(), end.getZ()) && check.getZ() >= Math.min(start.getZ(), end.getZ()))
            return true;
        return false;
    }

    public boolean intersect2D(LineSegment other){
        Coordinate otherStart = other.getStart();
        Coordinate otherEnd = other.getEnd();

        int turn1 = tripletOrientation2D(start, otherEnd, otherStart);
        int turn2 = tripletOrientation2D(start, end, otherEnd);
        int turn3 = tripletOrientation2D(otherStart, otherEnd, start);
        int turn4 = tripletOrientation2D(otherStart, otherEnd, end);

        if (turn1 != turn2 && turn3 != turn4)
            return true;

        if (turn1 == 0 && this.onLine(otherStart))
            return true;

        if (turn2 == 0 && this.onLine(otherEnd))
            return true;

        if (turn3 == 0 && other.onLine(start))
            return true;

        if (turn4 == 0 && other.onLine(end))
            return true;

        return false;
    }

    /*
    returns direction of triplet ABC:
        0 if collinear
        1 if clockwise
        -1 if counterclockwise
     */
    private int tripletOrientation2D(Coordinate a, Coordinate b, Coordinate c){
        double turn = (b.getY() - a.getY()) * (c.getX()-b.getX()) -
                    (b.getX() - a.getX()) * (c.getY() - b.getY());
        if (almostEqual(turn,0,1e-4))
            return 0;
        return (int) Math.signum(turn);
    }

    public boolean isEndpoint(Coordinate c){
        return c.equals(start) || c.equals(end);
    }

    public boolean meetsAtEndpoint(LineSegment other){
        return other.isEndpoint(this.start) || other.isEndpoint(this.end);
    }
    private static boolean almostEqual(double a, double b, double eps){
        return Math.abs(a-b)<eps;
    }


    public String toString(){
        return start.toString() + " -> " + end.toString();
    }

    public static void main(String[] args) {
        LineSegment ls = new LineSegment(new Coordinate(0,0,0),new Coordinate(2,2,0));
        LineSegment ls2 = new LineSegment(new Coordinate(0,1,0),new Coordinate(1,0,0));

    }
}
