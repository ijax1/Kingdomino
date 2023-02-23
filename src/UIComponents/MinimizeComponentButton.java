package UIComponents;

import Backend.Kingdomino;
import UIComponents.Render.Coordinate;

import java.awt.Graphics2D;
import java.awt.*;

public class MinimizeComponentButton extends Button{
    MinimizeComponentButton(Coordinate position, Kingdomino k) {
        super(position, k);
    }

    // action = if already minimized - set to not minimized, vice versa
    // draws separate components based on if minimized or not
    // sets position of tip to determine where to draw / where to determine the mouse
    public void doAction() {
        if (super.minimized) {
            super.show();
            // need to set coordinate based on location of button
            //super.setPosition(Coordinate())
        } else {
            super.minimize();
            //super.setPosition(Coordinate())
        }

        //draw(k.getGraphics);
        //need to minimize message box, but need to get the instance of the message box somehow
    }

    // draws based on the coordinate of the tip of the arrow
    // (if minimized or not)

    public void draw(Graphics2D g) {
        double tipX = super.getPosition().getX();
        double tipY = super.getPosition().getY();
        int[] xPoints = {(int)tipX, (int)tipX+10, (int)tipX-10};
        int yConstant = -10;
        if (super.minimized) {
            yConstant = 10;
        }
        int[] yPoints = {(int) tipY, (int)tipY+yConstant, (int)tipY+yConstant};

        g.setColor(new Color(241, 194, 50, 100));
        g.fillPolygon(new Polygon(xPoints, yPoints, 3));
    }

}
