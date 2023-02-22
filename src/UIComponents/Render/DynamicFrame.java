package UIComponents.Render;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

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
        this.setUndecorated(true);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
        getRootPane().getActionMap().put("Cancel", new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
                //framename.setVisible(false);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        DynamicFrame d = new DynamicFrame();
        d.add(new DynamicImagePanel(d, ImageIO.read(Kingdomino.class.getResource("../images/Homework.jpg")), new Coordinate(d.width/2,0,d.height/2)));
        d.setVisible(true);
    }

}
