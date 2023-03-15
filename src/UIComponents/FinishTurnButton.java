package UIComponents;

import java.awt.Color;
import java.awt.Graphics2D;
import Backend.GameManager;

import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;

public class FinishTurnButton extends Button{

    private final double width = 200;
    private final double height = 50;
    FinishTurnButton(Coordinate position, Kingdomino k) {
        super(position, k);
    }

    public void doAction() {
        // need to set current Player through the game manager
        // need access to get maanger through kingDomnio
        // need access to set currentPlayer int in gameManager
        Player p = getGame().getManager().getCurrentPlayer();
        p.setCurrentDomino(p.getNextDomino());
        getGame().getManager().nextPlayer();
//        getGame().getManager().finishTurn();
    }

    public boolean onComponent(Coordinate c) {
        // only doing it based off if you clicke on the centerpiece
    	//relative coordinates work here
    	double x = getPosition().getX();
    	double y = getPosition().getY();
        return ((c.getX() > x && c.getX() < x+width) &&
                (c.getY() > y && c.getY() < y+height));
    }

    public void draw(Graphics2D g) {
        //System.out.println("FINISH THY TURN length: " + g.getFontMetrics().stringWidth("FINISH THY TURN"));
        GameManager gm = super.getManager();
        if ((gm.isFirstRound() || gm.getCurrentPlayer().hasPlaced()) && gm.getCurrentPlayer().hasSelected()) {
            double xStart = super.getPosition().getX();
            double yStart = super.getPosition().getY();

            // drawing banner shape
            // purple squares
            int squareW = 100;
            int squareX = (int) (xStart - 50);
            int squareY = (int) (yStart - 10);
            g.setColor(new Color(140, 67, 188));
            g.fillRect(squareX, squareY, squareW, (int) height);
            g.fillRect((int) (squareX + width), squareY, squareW, (int) height);

            //purple triangles
            int triangleW = 20;
            int[] triangleXL = {squareX - triangleW, squareX, squareX};
            int[] triangleXR = {(int) (squareX + width + squareW), (int) (squareX + width + squareW + triangleW), (int) (squareX + width + squareW)};
            int[] triangleYT = {squareY, squareY, squareY / 2};
            int[] triangleYB = {(int) (squareY + height), (int) (squareY + height), squareY / 2};

//        g.fillPolygon(new java.awt.Polygon(triangleXL, triangleYT, 3));
//        g.fillPolygon(new java.awt.Polygon(triangleXL, triangleYB, 3));
//        g.fillPolygon(new java.awt.Polygon(triangleXR, triangleYT, 3));
//        g.fillPolygon(new java.awt.Polygon(triangleXR, triangleYB, 3));


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
            g.setColor(new Color(140, 67, 188));
            g.fillRect((int) xStart, (int) yStart, (int) width, (int) height);

            g.setColor(new Color(241, 194, 50));
            g.drawRect((int) xStart, (int) yStart, (int) width, (int) height);

//      g.setColor(Color.white);
            g.drawString("FINISH THY TURN", (int) xStart + 23, (int) yStart + 32);
            g.drawRect((int) xStart, (int) yStart, (int) width, (int) height);
        }
    }
}
