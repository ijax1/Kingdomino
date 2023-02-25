package UIComponents.Render;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RectangularPrism extends Polyhedron{
    private Polygon[] faces;
    private Coordinate center;
    private double length, width, height;

    private TransformationManager tm = new TransformationManager(new double[3], new double[3]);

    private Color[] color = {
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.YELLOW,
            Color.MAGENTA,
            Color.CYAN
    };

    public RectangularPrism(Coordinate center, double length, double width, double height){
        this.center = center;
        this.length = length;
        this.width = width;
        this.height = height;
        faces = this.calcFaces();
    }

    @Override
    Polygon[] calcFaces() {
        Polygon[] faces = new Polygon[6];

        int[] signs = {-1,1};
        int[][] orientations = {{1,1,-1,-1},{1,1,1,1},{1,-1,-1,1}};
        for(int i = 0; i < 2; i++){
            int sign = signs[i];
            for(int j = 0; j < 3; j++){
                Coordinate[] points = {
                        new Coordinate(center.getX()+(sign * orientations[j][0] * length/2), center.getY()+(sign * orientations[(j+1)%3][0] * height/2), center.getZ()+(sign * orientations[(j+2)%3][0] * width/2)),
                        new Coordinate(center.getX()+(sign * orientations[j][1] * length/2), center.getY()+(sign * orientations[(j+1)%3][1] * height/2), center.getZ()+(sign * orientations[(j+2)%3][1] * width/2)),
                        new Coordinate(center.getX()+(sign * orientations[j][2] * length/2), center.getY()+(sign * orientations[(j+1)%3][2] * height/2), center.getZ()+(sign * orientations[(j+2)%3][2] * width/2)),
                        new Coordinate(center.getX()+(sign * orientations[j][3] * length/2), center.getY()+(sign * orientations[(j+1)%3][3] * height/2), center.getZ()+(sign * orientations[(j+2)%3][3] * width/2))
                };
                int index = i*3+j;
                Polygon p = new Polygon(points, center);
                p.setColor(color[index]);
                faces[index] = p;
                if(index == 0)
                    try {
                        //faces[index] = new TexturedPolygon(points, center, ImageIO.read(new File("C:\\Users\\jonat\\Downloads\\strong.jpg")));
                    } catch(Exception e){;}
                if(index == 3)
                    try {
                        //faces[index] = new TexturedPolygon(points, center, ImageIO.read(new File("C:\\Users\\jonat\\Downloads\\obamba.jpg")));
                    } catch(Exception e){;}
            }
        }
        return faces;
    }

    public void moveTo(Coordinate c){
        this.center = c;
        for(Polygon p: faces){
            p.moveTo(c);
        }
    }

    @Override
    Polygon[] getFaces() {
        return faces;
    }

    //TODO
    //Fix z-buffering for non-cubic rectangular prisms

    @Override
    Polygon[] getVisible() {
        double near = 300;
        double far = -300;
        double[] zPrimes = new double[6];
        for(int i = 0; i < 6; i++){
            zPrimes[i] = 2.0 * (faces[i].getAverageZ() - near)/(far - near) - 1;
        }
        ArrayList<Polygon> visible = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            visible.add(faces[i]);
        }
        int n = zPrimes.length;
        for (int i = 1; i < n; ++i) {
            double key = zPrimes[i];
            int j = i - 1;

            while (j >= 0 && zPrimes[j] > key) {
                zPrimes[j + 1] = zPrimes[j];
                j = j - 1;
            }
            zPrimes[j + 1] = key;
            visible.add(j+1, visible.remove(i));
        }
        Polygon[] vis = new Polygon[6];
        for(int i = 0; i < 6; i++){
            vis[i] = visible.get(i);
        }
        return new Polygon[]{
                vis[3],vis[4],vis[5]
        };
    }

    public Coordinate getCenter(){
        return this.center;
    }

    public void setFace(int index, Polygon p){
        faces[index] = p;
    }

    public Polygon getFace(int index){
        return faces[index];
    }
}
