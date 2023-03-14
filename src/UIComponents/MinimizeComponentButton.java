package UIComponents;

import Backend.Kingdomino;
import UIComponents.Render.Coordinate;

import java.awt.Graphics2D;
import java.awt.*;
import java.util.ArrayList;

public class MinimizeComponentButton extends Button{

    private final Coordinate minimizedPosition = new Coordinate(200, 1200, 0);
    Graphics2D graphics;
    private final MessageTextBox textBox;

    MinimizeComponentButton(Coordinate position, Kingdomino k, MessageTextBox textBox) {
        super(position, k);
        this.textBox = textBox;
    }

    // action = if already minimized - set to not minimized, vice versa
    // draws separate components based on if minimized or not
    // sets position of tip to determine where to draw / where to determine the mouse
    public void doAction() {
        if (isShown()) {
            show();
            textBox.show();
        } else {
            minimize();
            textBox.minimize();
        }
        // will probably draw through whatever draws this button, just gonna set the textbook as show or minimize
        //textBox.draw(graphics);

        //need to minimize message box, but need to get the instance of the message box somehow
    }

    public boolean onComponent(Coordinate c) {
        double pointX = c.getX();
        double pointY = c.getY();

        double[] xPoints = getPoints()[0];
        double[] yPoints = getPoints()[1];

        double area = getArea(xPoints[0], xPoints[1], xPoints[2], yPoints[0], yPoints[1], yPoints[2]);
        double area1 = getArea(pointX, xPoints[1], xPoints[2], pointY, yPoints[1], yPoints[2]);
        double area2 = getArea(xPoints[0], pointX, xPoints[2], yPoints[0], pointY, yPoints[2]);
        double area3 = getArea(xPoints[0], xPoints[1], pointX, yPoints[0], yPoints[1], pointY);

        return (area1 + area2 + area3 == area);
    }

    private double[][] getPoints() {
        double tipX = getPosition().getX();
        double tipY = getPosition().getY();

        double yConstant = -10;
        if (isShown()) {
            yConstant = 10;
            tipX = minimizedPosition.getX();
            tipY = minimizedPosition.getY();
        }

        double[] xPoints = {tipX, tipX-10, tipX+10};
        double[] yPoints = {tipY, tipY+yConstant, tipY+yConstant};

        return new double[][]{xPoints, yPoints};
    }

    private double getArea(double x1, double x2, double x3, double y1, double y2, double y3) {
        return Math.abs((x1*(y2-y3) + x2*(y3-y1)+x3*(y1-y2))/2);
    }

    // draws based on the coordinate of the tip of the arrow
    // (if minimized or not)

    public void draw(Graphics2D g) {
        //for loop moving points into new arrays with integer type
        double[][] points = getPoints();
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];

        for (int i=0; i<3; i++) {
            xPoints[i] = (int) points[0][i];
            yPoints[i] = (int) points[1][i];
        }

        g.setColor(new Color(241, 194, 50));
        g.fillPolygon(new Polygon(xPoints, yPoints, 3));
    }

}
