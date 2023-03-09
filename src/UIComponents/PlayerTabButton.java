package UIComponents;

import java.awt.Graphics2D;

import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;

public class PlayerTabButton extends Button {
    Player player;
    private GamePanel gamePanel;
    private double width = 50;
    private final double height = 100;

    PlayerTabButton(Coordinate position, Kingdomino k, Player p, GamePanel gp) {
        super(position, k);
        player = p;
        gamePanel = gp;
    }

    public void doAction() {
    	System.out.println("player tab button clicked");
        if (getMinimized()) {
            super.show();
        }
        //will need to update to match once integrated
         if (gamePanel.getViewedPlayer() != player) {
             super.show();
             //gamePanel.setViewedPlayer(player);
         }
    }

    public boolean onComponent(Coordinate c) {
    	double x = getPosition().getX();
    	double y = getPosition().getY();
        return ((c.getX() > x && c.getX() < x+width) &&
                (c.getY() > y && c.getY() < y+height));
    }

    public Player getPlayer() {
        return player;
    }

    public void draw(Graphics2D g) {
        double startX = getPosition().getX();
        double startY = getPosition().getY();

        g.setColor(player.getColor());

        width = 50;
        if(!super.getMinimized()) {
            width += 25;
        }

        g.fillRect((int) startX, (int) startY, (int) width, (int) height);
    }
}
