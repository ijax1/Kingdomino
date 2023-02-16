package UIComponents.Render;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class DynamicImageRenderer implements ImageObserver{
    private final BufferedImage image;
    private final int height, width;
    private double scale;
    private final int[][] RGBArray;
    private double xRotation = 0, yRotation = 0, zRotation = 0;
    private Coordinate center;

    public DynamicImageRenderer(BufferedImage image, Coordinate center){
        this.image = image;
        height = image.getHeight();
        width = image.getWidth();
        scale=1;
        this.center = center;
        this.RGBArray = get2DArrayFromImage(image);
    }

    public DynamicImageRenderer(BufferedImage image, Coordinate center, double scale){
        this(image, center);
        this.scale = scale;
    }

    public void renderImage(Graphics g){
        double width = this.width * scale;
        double height = this.height * scale;

        Coordinate[] points = {
                new Coordinate(center.getX()-width/2.0, center.getY()-height/2.0,center.getZ()),
                new Coordinate(center.getX()+width/2.0, center.getY()-height/2.0,center.getZ()),
                new Coordinate(center.getX()+width/2.0, center.getY()+height/2.0,center.getZ()),
                new Coordinate(center.getX()-width/2.0, center.getY()+height/2.0,center.getZ())
        };
        Polygon p = new Polygon(points,center,xRotation,yRotation,zRotation);

        double x = p.getWidth();
        double y = p.getHeight();

        LineSegment topSegment = new LineSegment(p.getPoint(0),p.getPoint(1));
        LineSegment bottomSegment = new LineSegment(p.getPoint(3),p.getPoint(2));
        Coordinate start = topSegment.getStart();
        Coordinate end = bottomSegment.getStart();
        LineSegment sweep = new LineSegment(end, start);
        Coordinate access = sweep.getStart();

        Coordinate topLeftCorner = new Coordinate(
                center.getX() - x/2,
                center.getY() + y/2,
                0
        );

        if((int) x == 0 || (int) y == 0)
            return;
        BufferedImage b = new BufferedImage((int) x,(int) y, BufferedImage.TYPE_INT_ARGB);

        double widthIncrement = 0.75;
        double heightIncrement = 0.7;

        for(double i = 0; i < width; i+=widthIncrement){
            for(double j = 0; j < height; j+=heightIncrement){
                int rotX = (int) (limitNum((int) access.getX()-(int)topLeftCorner.getX(),0,(int)x-1));
                int rotY = (int) (limitNum((int) topLeftCorner.getY()-(int)access.getY(),0,(int)y-1));
                int color = RGBArray[limitNum((int)(j/scale),0,RGBArray.length-1)][limitNum((int)(i/scale),0,RGBArray[0].length-1)];
                if(b.getRGB(rotX,rotY)==0)
                    b.setRGB(rotX, rotY, color);
                access = sweep.getNextPoint(access, heightIncrement/height);
            }
            start = topSegment.getNextPoint(start,widthIncrement/width);
            end = bottomSegment.getNextPoint(end,widthIncrement/width);
            sweep = new LineSegment(end, start);
            access = end;
        }

        //antialias(b);
        g.drawImage(b, (int)(center.getX()-x/2.0), (int)(center.getY()-y/2.0), this);
    }


    /*
    Axes of rotation:
        x-axis runs along the width of the screen
            0, 2pi @ positive y-axis
            around ccw
        y-axis runs up and down the screen
            0, 2pi @ positive x-axis
            around ccw
        z-axis runs into and out of the screen
            0, 2pi @ positive x-axis
            around ccw
     */
    public void rotateImage(double xIncrement, double yIncrement, double zIncrement){
        this.xRotation += xIncrement;
        this.yRotation += yIncrement;
        this.zRotation += zIncrement;
        if(xRotation >= 2*Math.PI)
            xRotation -= 2*Math.PI;
        if(yRotation >= 2*Math.PI)
            yRotation -= 2*Math.PI;
        if(zRotation >= 2*Math.PI)
            zRotation -= 2*Math.PI;
    }

    public double getScale(){
        return scale;
    }

    public void setScale(double scale){
        this.scale = scale;
    }

    public Coordinate getCenter(){
        return center;
    }

    public void setCenter(Coordinate destination){
        this.center = destination;
    }

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

    public static void main(String[] args) throws IOException {
        DynamicFrame d = new DynamicFrame();
        BufferedImage b = ImageIO.read(new File("C:\\Users\\jonat\\IdeaProjects\\Testing\\Images\\IMG_5516.jpg"));
        DynamicImagePanel dip = new DynamicImagePanel(d, b, new Coordinate(d.width/2.0,d.height/2.0,0));
        d.add(dip);
        dip.animate();
        d.setVisible(true);
    }

}
