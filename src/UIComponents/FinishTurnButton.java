package UIComponents;

import Backend.Kingdomino;
import UIComponents.Render.Coordinate;
import UIComponents.Render.Polygon;

import java.awt.Color;
import java.awt.Graphics2D;

public class FinishTurnButton extends Button{

    private final double width = 100;
    private final double height = 50;
    FinishTurnButton(Coordinate position, Kingdomino k) {
        super(position, k);
    }

    public void doAction() {
        // need to set current Player through the game manager
        // need access to get maanger through kingDomnio
        // need access to set currentPlayer int in gameManager

        getGame().getManager().setCurrentPlayer();
    }

    public boolean onComponent(Coordinate c) {
        // only doing it based off if you clicke on the centerpiece
        return ((c.getX() > 0 && c.getX() < width) &&
                (c.getY() > getPosition().getY() && c.getY() < getPosition().getY() + height));
    }

    public void draw(Graphics2D g) {
        double xStart = super.getPosition().getX();
        double yStart = super.getPosition().getY();

        // drawing banner shape
        // purple squares
        int squareW = 40;
        int squareX = (int) (xStart - 20);
        int squareY = (int) (yStart - 10);
        g.setColor(new Color(140, 67, 188, 100));
        g.fillRect(squareX, squareY, squareW, (int) height);
        g.fillRect((int) (squareX + width), squareY, squareW, (int) height);

        //purple triangles
        int triangleW = 20;
        int[] triangleXL = {squareX - triangleW, squareX, squareX};
        int[] triangleXR = {(int) (squareX + width + squareW), (int) (squareX + width + squareW + triangleW), (int) (squareX + width + squareW)};
        int[] triangleYT = {squareY, squareY, squareY / 2};
        int[] triangleYB = {(int) (squareY + height), (int) (squareY + height), squareY / 2};

        g.fillPolygon(new Polygon(triangleXL, triangleYT, 3));
        g.fillPolygon(new Polygon(triangleXL, triangleYB, 3));
        g.fillPolygon(new Polygon(triangleXR, triangleYT, 3));
        g.fillPolygon(new Polygon(triangleXR, triangleYB, 3));

        //gold outline

//        int lineStartL = triangleXL[0];
//        int lineStartR;
//
//        g.drawLine(triangleXL[0], squareY, squareX + squareW, squareY);
//        g.drawLine();
//        g.drawLine();
//        g.drawLine();
//        g.drawLine();

        //

        // drawing legitmate button
        g.setColor(new Color(140, 67, 188, 100));
        g.fillRect((int) xStart,(int) yStart, (int) width, (int) height);

        g.setColor(new Color(241, 194, 50));
        g.drawRect((int) xStart,(int) yStart,(int) width,(int) height);

        g.setColor(Color.white);
        g.drawString("FINISH THY TURN",(int) xStart+10,(int) yStart+10);
    }
}
