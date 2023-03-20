package UIComponents;

import java.awt.*;

import Backend.GameManager;
import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;
import resources.OurColors;

public class PlayerTabButton extends Button {
    Player player;
    private GamePanel gamePanel;
    private double width = 70;
    private final double height = 150;
    private PlayerTabGroup group;
    private GameManager gm;

    PlayerTabButton(Coordinate position, Kingdomino k, Player p, PlayerTabGroup group, GamePanel gp) {
        super(position, k);
        player = p;
        gamePanel = gp;
        this.group = group;
        this.gm = k.getManager();
    }

    public void doAction() {
        if (!isShown()) {
            super.show();
        	group.setSelected(this);
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


        width = 90;
        if(super.isShown()) {
            width += 30;
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

        if (player.getNextDomino() != null) {
            Coordinate dominoPosition = new Coordinate(super.getPosition().getX() + 40, super.getPosition().getY() + 75, super.getPosition().getZ());
            UIDomino nextDomino = new UIDomino(dominoPosition, super.getGame(), player.getNextDomino());
            nextDomino.incrementRotation(0, 0, Math.PI / 2);
            nextDomino.draw(g);
        }
        if(gm.getCurrentPlayer() == getPlayer()) {
        	int widthi = (int) (startX + width + 10);
        	int heighti = (int) (startY + height);
        	int heightHalf = (int) (startY + height/2);
        	int[] xp = {widthi, widthi+30, widthi+30};
        	int[] yp = {heightHalf, (int) startY+30, heighti-30};
        	g.setColor(OurColors.ACCENT_COLOR);
        	g.fillPolygon(xp, yp, 3);
        	g.setStroke(new BasicStroke(2));
        	g.setColor(Color.BLACK);
        	g.drawPolygon(xp, yp, 3);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
