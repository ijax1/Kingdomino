package UIComponents;

import java.awt.Graphics2D;

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
   private int width = 100, height = 50; 
  
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
   	double x = getPosition().getX();
   	double y = getPosition().getY();
    return ((c.getX() > x && c.getX() < x+width) &&
               (c.getY() > y && c.getY() < y+height));
   }
   @Override
   public void draw(Graphics2D g) {
     graphics = g; 
   // drawing tiles based on images;
     uiDomino.draw(g);
     // drawing outline when clicked; 
     if (player != null) {
       g.setColor(player.getColor());
       g.drawRect((int) getPosition().getX(), (int) getPosition().getY(), width, height);
     }
   }
 }
