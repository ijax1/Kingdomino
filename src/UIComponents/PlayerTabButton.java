package UIComponents;

import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;

import java.awt.Graphics2D;

public class PlayerTabButton extends Button{
    Player player;
    private double width = 50;
    private final double height = 100;
    private boolean selected = false;

    PlayerTabButton(Coordinate position, Kingdomino k, Player p) {
        super(position, k);
        player = p;
    }
    
    public boolean isSelected() {
        return selected;
    }

    public void doAction() {
        if (!selected) {
            super.show();
            selected = true;
        }
    }

    public boolean onComponent(Coordinate c) {
        return ((c.getX() > 0 && c.getX() < width) &&
                (c.getY() > getPosition().getY() && c.getY() < getPosition().getY() + height));
    }

    public Player getPlayer() {
        return player;
    }

    public void draw(Graphics2D g) {
        double startX = getPosition().getX();
        double startY = getPosition().getY();

        g.setColor(player.getColor());

        width = 50;
        if(!super.isMinimized()) {
            width += 25;
        }

        g.fillRect((int) startX, (int) startY, (int) width, (int) height);
    }
}
