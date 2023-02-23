package UIComponents;

import Backend.Kingdomino;
import UIComponents.Render.Coordinate;

import java.awt.Color;
import java.awt.Graphics2D;

public class FinishTurnButton extends Button{

    FinishTurnButton(Coordinate position, Kingdomino k) {
        super(position, k);
    }

    public void doAction() {
        // need to set current Player through the game manager
        // need access to get maanger through kingDomnio
        // need access to set currentPlayer int in gameManager

        //super.getGame().getGameManager().setCurrentPlayer() ...

    }

    public void draw(Graphics2D g) {
        double xStart = super.getPosition().getX();
        double yStart = super.getPosition().getY();

        double width = 100;
        double height = 50;

        // drawing banner shape
        // purple squares
        g.setColor(new Color(140, 67, 188, 100));
        g.fillRect((int) xStart - 20, (int) yStart - 10, 40, (int) height);
        g.fillRect((int) (xStart + width - 20), (int) yStart - 10, 40, (int) height);

        //purple triangles

        //gold outline

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
