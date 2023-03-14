package UIComponents;

import java.awt.*;

import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;

public class PlayerTabButton extends Button {
    Player player;
    private GamePanel gamePanel;
    private double width = 50;
    private final double height = 100;
    private PlayerTabGroup group;

    PlayerTabButton(Coordinate position, Kingdomino k, Player p, PlayerTabGroup group, GamePanel gp) {
        super(position, k);
        player = p;
        gamePanel = gp;
        this.group = group;
    }

    public void doAction() {
        if (!isShown()) {
            super.show();
        	group.setSelected(this);
        }
        //will need to update to match once integrated
//         if (gamePanel.getViewedPlayer() != index) {
//             super.show();
//             gamePanel.setViewedPlayer(index);
//         }
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


        width = 40;
        if(super.isShown()) {
            width += 25;
        }
        int filletRadius = 20;
        //g.fillRect((int) startX, (int) startY, (int) width, (int) height);

        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.drawRect((int) startX, (int) startY, (int) width-filletRadius/2, (int) height);
        g.drawOval((int) (startX+(width-filletRadius)), (int) startY, (int) filletRadius, (int) filletRadius);
        g.drawRect((int) (startX+(width-filletRadius)), (int) startY + filletRadius/2, (int) filletRadius, (int) height - filletRadius);
        g.drawOval((int) (startX+(width-filletRadius)), (int) (startY + (height - filletRadius)), (int) filletRadius, (int) filletRadius);

        g.setColor(player.getColor());
        g.fillRect((int) startX, (int) startY, (int) width-filletRadius/2, (int) height);
        g.fillOval((int) (startX+(width-filletRadius)), (int) startY, (int) filletRadius, (int) filletRadius);
        g.fillRect((int) (startX+(width-filletRadius)), (int) startY + filletRadius/2, (int) filletRadius, (int) height - filletRadius);
        g.fillOval((int) (startX+(width-filletRadius)), (int) (startY + (height - filletRadius)), (int) filletRadius, (int) filletRadius);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
