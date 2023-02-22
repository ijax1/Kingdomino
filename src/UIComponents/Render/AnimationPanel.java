package UIComponents.Render;

import UIComponents.Domino;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AnimationPanel extends DynamicPanel{
    final double xMin = 0;
    final double xMax;
    final double yMin = 0;
    final double yMax;
    int num = 35;
    RectangularPrism[] rects = new RectangularPrism[num];
    double[] xRotations = new double[num];
    double[] yRotations = new double[num];
    double[] zRotations = new double[num];
    double[] xVelocities = new double[num];
    double[] yVelocities = new double[num];

    public AnimationPanel(JFrame frame) {
        super(frame);
        this.xMax = frame.getWidth();
        this.yMax = frame.getHeight();
        populate();
    }

    public void populate(){
        for(int i = 0; i < num; i++){
            Coordinate center = new Coordinate(
                    xMax * Math.random(),
                    yMax * Math.random(),
                    0
            );
            int length = (int) (200.0*Math.random())+50;
            int width = (int) (200.0*Math.random())+50;
            int height = (int) (200.0*Math.random())+50;
            rects[i] = new RectangularPrism(center, length, width, height);
            xRotations[i] = Math.random() * Math.PI/30 * Math.signum(((2*Math.random())-1));
            yRotations[i] = Math.random() * Math.PI/30 * Math.signum(((2*Math.random())-1));
            zRotations[i] = Math.random() * Math.PI/30 * Math.signum(((2*Math.random())-1));
            xVelocities[i] = Math.random() * 10.0 * Math.signum(((2*Math.random())-1));
            yVelocities[i] = Math.random() * 10.0 * Math.signum(((2*Math.random())-1));
        }
    }

    public void animate(){
        final AnimationPanel a = this;
        Timer timer = new Timer(2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < num; i++){
                    RectangularPrism r = rects[i];
                    double xDest = r.getCenter().getX();
                    double yDest = r.getCenter().getY();
                    double xV = xVelocities[i];
                    double yV = yVelocities[i];
                    if(xDest + xV > xMax || xDest + xV < xMin) {
                        xV = -xV;
                        xVelocities[i] = xV;
                    }
                    if(yDest + yV > yMax || yDest + yV < yMin) {
                        yV = -yV;
                        yVelocities[i] = yV;
                    }
                    Coordinate dest = new Coordinate(
                            xDest + xV,
                            yDest + yV,
                            0
                    );
                    r.moveTo(dest);
                    r.incrementRotation(xRotations[i],yRotations[i],zRotations[i]);
                    xRotations[i] += Math.random() * Math.PI/200 - Math.PI/400;
                    yRotations[i] += Math.random() * Math.PI/200 - Math.PI/400;
                    zRotations[i] += Math.random() * Math.PI/200 - Math.PI/400;
                    xVelocities[i] += Math.random() * 0.5 - 0.25;
                    yVelocities[i] += Math.random() * 0.5 -0.25;
                }
                a.repaint();
                if((int)(Math.random()*1)==0) {
                    ArrayList<RectangularPrism> rect = new ArrayList<>();
                    Collections.addAll(rect, rects);
                    Collections.shuffle(rect);
                    //rects = rect.toArray(rects);
                }
            }
        });
        timer.start();
    }
    Color colo = new Color((int)(2*Math.random())*255,(int)(2*Math.random())*255,(int)(2*Math.random())*255);
    public void paintComponent(Graphics g){
        g.clearRect(0,0,(int)xMax,(int)yMax);
        if((int)(Math.random()*3)==0)
            colo = new Color((int)(2*Math.random())*255,(int)(2*Math.random())*255,(int)(2*Math.random())*255);

        g.setColor(colo);
        g.fillRect(0,0,(int)xMax,(int)yMax);
        for(RectangularPrism r: rects)
            r.render(g);
    }

    public static void main(String[] args) throws IOException {
        DynamicFrame d = new DynamicFrame();

        AnimationPanel ip = new AnimationPanel(d);
        d.add(ip);
        ip.animate();
        d.setVisible(true);
    }
}
