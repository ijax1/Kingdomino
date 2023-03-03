package UIComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;

import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;

public class DominoButton extends Button {
   private Graphics graphics;
   private Player p;
   private boolean locked; 
   private int width = 100, height = 50; 
  
   DominoButton(Coordinate c, Kingdomino k, Graphics2D g) {
     super(c, k);
     locked = false;
     p = null; 
   }
  
   public void doAction() {
     if (!locked) {
//       p = getGame().getCurrentPlayer(); 
//       draw(graphics)
     }
   }

   public boolean onCOmponent(Coordinate c) {
     return ((c.getX() > 0 && c.getX() < width) &&
              (c.getY() > getPosition().getY() && c.getY() < getPosition().getY() + height));
   }
  
   public void draw(Graphics2D g) {
     graphics = g; 
   // drawing tiles based on images;
    
     // drawing outline when clicked; 
     if (p != null) {
       g.setColor(p.getColor());
//       g.drawRect(getPosition.getX(), getPosition.getY(), width, height);
      
     }
   }
 }
