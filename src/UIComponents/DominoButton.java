package UIComponents;

import java.awt.*;

import Backend.Domino;
import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;

public class DominoButton extends Button {
   private UIDomino uiDomino; 
   //private ArrayList<DominoButton> dominoes; 
   private Graphics2D graphics;
   private Player player;
   private boolean locked;
   private int width = UITile.TILE_SIZE * 2 + 20, height = UITile.TILE_SIZE + 20;
  
   DominoButton(Coordinate c, Kingdomino k, Domino d) {
     super(c, k);
      // need for information on what to draw later 
     uiDomino = new UIDomino(c, k, d);
     locked = false;
     player = null; 
   }
   @Override
   public void doAction() {
	 System.out.println("dominobutton clicked");
     if (!locked) {
      player = getManager().getCurrentPlayer();
      setLocked();
      draw(graphics);
     }
      
     // removing any other button that may have the same player highlight 
//     for (DominoButton db: dominoes) {
//        if (!db.getLocked()) {
//         db.removePlayer(); 
//         db.draw(graphics);
//        }
//     }
   }
   
   
   public void removePlayer() {
      player = null;
      locked = false;
   }
      
   // after turn finishs, will call setLocked on the domino button that has the same player instance as curretn player
   private void setLocked() {
      locked = true; 
      player.setNextDomino(uiDomino.getRef());
   }
   protected void setDomino(Domino d) {
	   uiDomino.setRef(d);
   }
   
   public boolean getLocked() {
      return locked; 
   }
   
   @Override
   public boolean onComponent(Coordinate c) {
   	double x = getPosition().getX() - width/2;
   	double y = getPosition().getY() - height/2;

    return ((c.getX() > x && c.getX() < x+width) &&
               (c.getY() > y && c.getY() < y+height));
   }
   @Override
   public void draw(Graphics2D g) {
     graphics = g;
    // drawing tiles based on images;
     // drawing outline when clicked;
     if (player != null) {
       g.setColor(player.getColor());
       g.fillRect((int) getPosition().getX() - width/2, (int) getPosition().getY()-height/2, width, height);
     }
     uiDomino.draw(g);
     if(player != null){
         g.setColor(new Color(50,50,50,150));
         g.fillRect((int) getPosition().getX() - UITile.TILE_SIZE, (int) getPosition().getY()-UITile.TILE_SIZE/2, UITile.TILE_SIZE*2, UITile.TILE_SIZE);
     }
   }
 }
