package UIComponents;
import Backend.Kingdomino;
import UIComponents.Render.Coordinate;

import java.awt.Graphics2D;
import java.awt.*;

public class CloseButton extends Button{
    CloseButton(Coordinate position, Kingdomino k) {
        super(position, k);
    }

    public void doAction() {
        System.exit(0);
    }

    public void draw(Graphics2D g) {
        double xStart = super.getPosition().getX();
        double yStart = super.getPosition().getY();

        double width = 100;
        double height = 50;

        g.setColor(new Color(140, 67, 188, 100));
        g.fillRect((int) xStart,(int) yStart, (int) width, (int) height);

        g.setColor(new Color(241, 194, 50));
        g.drawRect((int) xStart,(int) yStart,(int) width,(int) height);

        g.setColor(Color.white);
        g.drawString("EXITETH",(int) xStart+10,(int) yStart+10);
    }
}
