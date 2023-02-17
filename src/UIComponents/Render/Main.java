package UIComponents.Render;

import java.io.IOException;

import res.Resources;

public class Main {

    public static void main(String[] args) throws IOException {

        DynamicFrame d = new DynamicFrame();
        DynamicImagePanel dip = new DynamicImagePanel(d, Resources.loadImage("homework.jpg"), new Coordinate(d.width/2,d.height/2,0));
        d.add(dip);
        dip.animate();
        d.setVisible(true);
    }
}