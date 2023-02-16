package UIComponents.Render;

import java.awt.image.BufferedImage;

public class TexturedPolygon extends Polygon{

    DynamicImageRenderer renderer;

    public TexturedPolygon(Coordinate[] points, Coordinate center, BufferedImage b) {
        super(points, center);
        renderer = new DynamicImageRenderer(b,center);
    }

    public TexturedPolygon(Coordinate[] points, Coordinate center, BufferedImage b, double xRotation, double yRotation, double zRotation){
        super(points, center);
        renderer = new DynamicImageRenderer(b,center);
        renderer.rotateImage(xRotation,yRotation,zRotation);
        setToRotation(xRotation, yRotation, zRotation);
    }

    public TexturedPolygon(Coordinate[] points, Coordinate center, BufferedImage b, double scale){
        super(points, center);
        renderer = new DynamicImageRenderer(b,center, scale);

    }


}
