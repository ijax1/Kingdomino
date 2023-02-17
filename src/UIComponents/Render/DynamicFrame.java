package UIComponents.Render;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Backend.Kingdomino;

public class DynamicFrame extends JFrame {
    public final int width;
    public final int height;
    DynamicFrame(){
        DisplayMode d = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = d.getWidth();
        this.height = d.getHeight();
        this.setSize(d.getWidth(), d.getHeight());
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setUndecorated(true);
        this.setDefaultCloseOperation(super.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IOException {
        DynamicFrame d = new DynamicFrame();
        d.add(new DynamicImagePanel(d, ImageIO.read(Kingdomino.class.getResource("../images/Homework.jpg")), new Coordinate(d.width/2,0,d.height/2)));
        d.setVisible(true);
    }

}
