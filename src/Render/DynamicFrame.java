package Render;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        d.add(new DynamicImagePanel(d, ImageIO.read(new File("C:\\Users\\jonat\\IdeaProjects\\Testing\\Images\\Logo.png")), new Coordinate(d.width/2,0,d.height/2)));
        d.setVisible(true);
    }

}
