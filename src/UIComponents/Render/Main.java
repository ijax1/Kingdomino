package UIComponents.Render;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        DynamicFrame d = new DynamicFrame();
        DynamicImagePanel dip = new DynamicImagePanel(d, ImageIO.read(new File("C:\\Users\\jonat\\IdeaProjects\\Testing\\Images\\colored_squares.png")), new Coordinate(d.width/2,0,d.height/2));
        d.add(dip);
        //dip.animate();
        d.setVisible(true);

        //Application.launch(ImageRenderer3D.class, args);
    }
}