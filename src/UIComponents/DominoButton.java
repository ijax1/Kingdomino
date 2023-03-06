package UIComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;

import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;

public class DominoButton extends Button {
   private Domino domino; 
   private ArrayList<DominoButton> dominoes; 
   private Graphics graphics;
   private Player player;
   private boolean locked;
   private int width = 100, height = 50; 
  
   DominoButton(Coordinate c, Kingdomino k, Domino d, ArrayList<DominoButton> dominoes) {
     super(c, k);
      // need for information on what to draw later 
     domino = d; 
     this.dominoes = dominoes; 
     locked = false;
     player = null; 
   }
   @Override
   public void doAction() {
     if (!locked) {
      p = getGame().getCurrentPlayer(); 
      draw(graphics)
     }
      
     // removing any other button that may have the same player highlight 
     for (DominoButton db: dominoes) {
        if (!db.getLocked()) {
         db.removePlayer(); 
         db.draw(graphics);
        }
     }
   }
   
   
   public void removePlayer() {
      p = null; 
   }
      
   // after turn finishs, will call setLocked on the domino button that has the same player instance as curretn player
   public void setLocked() {
      locked = true; 
      p.setNextDomino(domino); 
   }
   
   public boolean getLocked() {
      return locked; 
   }
   
   @Override
   public boolean onComponent(Coordinate c) {
     return ((c.getX() > 0 && c.getX() < width) &&
              (c.getY() > getPosition().getY() && c.getY() < getPosition().getY() + height));
   }
   @Override
   public void draw(Graphics2D g) {
     graphics = g; 
   // drawing tiles based on images;
    
     // drawing outline when clicked; 
     if (p != null) {
       g.setColor(p.getColor());
       g.drawRect(getPosition.getX(), getPosition.getY(), width, height);
     }
   }
 }
