package UIComponents.Render;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class TexturedPolygon extends Polygon implements ImageObserver {
    private final BufferedImage image;
    private final int height, width;
    private double scale;
    private final int[][] RGBArray;
    private double xRotation = 0, yRotation = 0, zRotation = 0;
    public TexturedPolygon(Coordinate[] points, Coordinate center, BufferedImage b) {
        super(points, center);
        this.image = b;
        height = image.getHeight();
        width = image.getWidth();
        this.RGBArray = get2DArrayFromImage(image);
        scale=super.getWidth()/width;
    }

    public TexturedPolygon(Coordinate[] points, Coordinate center, BufferedImage b, double scale){
        super(points, center);
        this.image = b;
        height = image.getHeight();
        width = image.getWidth();
        this.scale = scale;
        this.RGBArray = get2DArrayFromImage(image);
    }

    public void incrementRotation(double xRotation, double yRotation, double zRotation){
        super.incrementRotation(xRotation, yRotation, zRotation);
        this.xRotation = xRotation;
        this.yRotation = yRotation;
        this.zRotation = zRotation;
    }

    public void render(Graphics g){
        double width = this.width * scale;
        double height = this.height * scale;


        Coordinate center = super.getCenter();
        g.setColor(Color.BLACK);
        int[] xPoints = {(int)super.getPoint(0).getX(),
                (int)super.getPoint(1).getX(),
                (int)super.getPoint(2).getX(),
                (int)super.getPoint(3).getX(),
                (int)super.getPoint(0).getX()};
        int[] yPoints = {(int)super.getPoint(0).getY(),
                (int)super.getPoint(1).getY(),
                (int)super.getPoint(2).getY(),
                (int)super.getPoint(3).getY(),
                (int)super.getPoint(0).getY()};
        g.drawPolygon(xPoints,yPoints,5);
        Polygon p = this;


        double x = p.getWidth();
        double y = p.getHeight();

        LineSegment topSegment = new LineSegment(p.getPoint(0),p.getPoint(1));
        LineSegment bottomSegment = new LineSegment(p.getPoint(3),p.getPoint(2));
        Coordinate start = topSegment.getStart();
        Coordinate end = bottomSegment.getStart();
        LineSegment sweep = new LineSegment(end, start);
        Coordinate access = sweep.getStart();

        Coordinate topLeftCorner = super.getTopLeft();

        if((int) x == 0 || (int) y == 0)
            return;
        BufferedImage b = new BufferedImage((int) x,(int) y, BufferedImage.TYPE_INT_ARGB);

        double widthIncrement = 0.6;
        double heightIncrement = 0.6;

        for(double i = 0; i < width; i+=widthIncrement){
            for(double j = 0; j < height; j+=heightIncrement){
                int rotX = (int) (limitNum((int) access.getX()-(int)topLeftCorner.getX(),0,(int)x-1));
                int rotY = (int) (limitNum((int)access.getY()-(int) topLeftCorner.getY(),0,(int)y-1));
                int color = RGBArray
                        [limitNum((int)(j/scale),0,RGBArray.length-1)]
                        [limitNum((int)(i/scale),0,RGBArray[0].length-1)];
                if(b.getRGB(rotX,rotY)==0) {
                    b.setRGB(rotX, rotY, color);
                }
                    //if(p.intersects(new Coordinate(access.getX(),access.getY(),0)))

                access = sweep.getNextPoint(access, heightIncrement/height);
            }
            start = topSegment.getNextPoint(start,widthIncrement/width);
            end = bottomSegment.getNextPoint(end,widthIncrement/width);
            sweep = new LineSegment(end, start);
            access = end;
        }

        //antialias(b);
        g.drawImage(b, (int)topLeftCorner.getX(), (int)topLeftCorner.getY(), this);
    };

    private int[][] get2DArrayFromImage(BufferedImage b){
        int height = b.getHeight();
        int width = b.getWidth();
        int[][] pixels = new int[height][width];
        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                //b.getRGB stores values on a rotated plane: x goes up and down and y goes left to right
                pixels[row][col] = b.getRGB(col,row);
            }
        }
        return pixels;
    }

    private int limitNum(int num, int lower, int upper){
        if(num < lower)
            return lower;
        else if(num > upper)
            return upper;
        return num;
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }

    public Polygon duplicatePolygon(Coordinate c){
        Coordinate[] temp = new Coordinate[super.getPoints().length];
        System.arraycopy(super.getPoints(), 0, temp, 0, super.getPoints().length);
        TexturedPolygon p = new TexturedPolygon(temp, super.getCenter(), image);
        p.moveTo(c);
        p.setColor(super.getColor());
        return p;
    };
    public String toString(){
        return image.toString();
    }
}
