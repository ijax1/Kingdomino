package UIComponents.Render;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;

public class DynamicImagePanel extends DynamicPanel{
    DynamicImageRenderer renderer;

    public DynamicImagePanel(JFrame frame, BufferedImage image, Coordinate center) {
        super(frame);
        renderer = new DynamicImageRenderer(image, center);
        renderer.rotateImage(4*Math.PI/3,4*Math.PI/3,0);
        renderer.setScale(0.10);
    }

    public void paintComponent(Graphics g){
        g.clearRect(0,0,10000,10000);
        renderer.renderImage(g);
    }

    public void animate(){
        final DynamicImagePanel dip = this;
        Timer timer = new Timer(5, new ActionListener() {
            final int left = 600;
            final int right = 1200;
            int v = -10;
            double vScale = -0.001;
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.rotateImage(Math.PI/100, Math.PI/100,Math.PI/100);
                Coordinate center = renderer.getCenter();
                if((center.getX() > right && v > 0) || (center.getX() < left && v < 0)) {
                    v = -v;
                }
                renderer.setCenter(new Coordinate(center.getX()+v,center.getY(),center.getZ()));
                double scale = renderer.getScale();
                if((scale > 0.10 && vScale > 0) || (scale < 0.01 && vScale < 0)) {
                    vScale = -vScale;
                }
                renderer.setScale(scale+ vScale);
                dip.repaint();
            }
        });
        timer.start();
    }
}
