package UIComponents;

import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;

import java.awt.Graphics2D;

public class PlayerTabButton extends Button{
    Player player;

    PlayerTabButton(Coordinate position, Kingdomino k, Player p) {
        super(position, k);
        player = p;
    }

    public void doAction() {
        super.show();
    }

    public Player getPlayer() {
        return player;
    }

    public void draw(Graphics2D g) {
        double startX = super.getPosition.getX();
        double startY = super.getPosition.getY();

        double width = 50;
        double height = 100;

        g.setColor(player.getColor());

        if(!super.minimized) {
            width += 25;
        }

        g.fillRect((int) startX, (int) startY, (int) width, (int) height);
    }
}
