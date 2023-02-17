package UIComponents.Render;

import java.io.IOException;

import javax.imageio.ImageIO;

import Backend.Kingdomino;

public class Main {

    public static void main(String[] args) throws IOException {

        DynamicFrame d = new DynamicFrame();
        DynamicImagePanel dip = new DynamicImagePanel(d, ImageIO.read(Kingdomino.class.getResource("../images/Homework.jpg")), new Coordinate(d.width/2,d.height/2,0));
        d.add(dip);
        dip.animate();
        d.setVisible(true);
    }
}