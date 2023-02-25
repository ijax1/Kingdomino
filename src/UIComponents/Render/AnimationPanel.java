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
    final double zMin = -200;
    final double zMax = 200;
    int num = 20;
    Domino[] rects = new Domino[num];
    double[] xRotations = new double[num];
    double[] yRotations = new double[num];
    double[] zRotations = new double[num];
    double[] xVelocities = new double[num];
    double[] yVelocities = new double[num];
    double[] zVelocities = new double[num];

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
            rects[i] = new Domino(center, null, null, null);
            xRotations[i] = Math.random() * Math.PI/30 * Math.signum(((2*Math.random())-1));
            yRotations[i] = Math.random() * Math.PI/30 * Math.signum(((2*Math.random())-1));
            zRotations[i] = Math.random() * Math.PI/30 * Math.signum(((2*Math.random())-1));
            xVelocities[i] = Math.random() * 10.0 * Math.signum(((2*Math.random())-1));
            yVelocities[i] = Math.random() * 10.0 * Math.signum(((2*Math.random())-1));
            zVelocities[i] = Math.random() * 10.0 * Math.signum(((2*Math.random())-1));
        }
    }

    public void animate(){
        final AnimationPanel a = this;
        Timer timer = new Timer(2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < num; i++){
                    Domino r = rects[i];
                    double xDest = r.getCenter().getX();
                    double yDest = r.getCenter().getY();
                    double xV = xVelocities[i];
                    double yV = yVelocities[i];
                    if(xDest + xV > xMax || xDest + xV < xMin) {
                        xV = -xV;
                        xVelocities[i] = xV;
                        xRotations[i] = -xRotations[i];
                        yRotations[i] = -yRotations[i];
                        zRotations[i] = -zRotations[i];
                    }
                    if(yDest + yV > yMax || yDest + yV < yMin) {
                        yV = -yV;
                        yVelocities[i] = yV;
                        xRotations[i] = -xRotations[i];
                        yRotations[i] = -yRotations[i];
                        zRotations[i] = -zRotations[i];
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
                    ArrayList<Domino> rect = new ArrayList<>();
                    Collections.addAll(rect, rects);
                    Collections.shuffle(rect);
                    //rects = rect.toArray(rects);
                }
            }
        });
        timer.start();
    }
    Color[] colors = {Color.RED, Color.MAGENTA, Color.YELLOW, Color.BLUE, Color.GREEN, Color.CYAN};
    Color prev = Color.RED;
    Color curr = Color.RED;
    Color next = Color.RED;
    double rInc = 0, gInc = 0, bInc = 0;
    public void paintComponent(Graphics g){
        g.clearRect(0,0,(int)xMax,(int)yMax);
        if(colorSimilar(curr, next)) {
            next = colors[(int) (colors.length * Math.random())];
            rInc = ((double)next.getRed() - (double)curr.getRed())/5.0;
            gInc = ((double)next.getGreen() - (double)curr.getGreen())/5.0;
            bInc = ((double)next.getBlue() -(double) curr.getBlue())/5.0;
        }
        curr = new Color(
                (int) limitNum((curr.getRed() + rInc),0,255),
                (int) limitNum((curr.getGreen() + gInc),0,255),
                (int) limitNum((curr.getBlue() + bInc),0,255)
        );
        g.setColor(curr);
        g.fillRect(0,0,(int)xMax,(int)yMax);
        for(Domino r: rects){
            r.draw((Graphics2D) g);
        }
    }

    private boolean colorSimilar(Color c1, Color c2){
        if(Math.abs(c1.getRed()-c2.getRed()) < 3 && Math.abs(c1.getGreen()-c2.getGreen()) < 3 && Math.abs(c1.getBlue()-c2.getBlue()) < 3)
            return true;
        return false;
    }

    private void reorderZ(){

    }

    private double limitNum(double num, double lower, double upper){
        if(num < lower)
            return lower;
        else if(num > upper)
            return upper;
        return num;
    }

    public static void main(String[] args) throws IOException {
        DynamicFrame d = new DynamicFrame();

        AnimationPanel ip = new AnimationPanel(d);
        d.add(ip);
        ip.animate();
        d.setVisible(true);
    }
}
