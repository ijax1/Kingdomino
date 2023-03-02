// package UIComponents;
// import Backend.Kingdomino;
// import UIComponents.Render.Coordinate;

// public class PlayButton extends Button{
//    private final double width = 100;
//    private final double height = 50;

// 	PlayButton(Coordinate position, Kingdomino k) {
// 		super(position, k);
// 		// TODO Auto-generated constructor stub
// 	}

// 	public void doAction() {
//     //are we changing game by changing panel? 
// 		getGame().setPanel(); 
// 	}
  
//   public boolean onComponent(Coordinate c) {
//     return ((c.getX() > 0 && c.getX() < width) &&
//             (c.getY() > getPosition().getY() && c.getY() < getPosition().getY() + height));
//   }
    
//    public void draw(Graphics2D g) {
//         double xStart = getPosition().getX();
//         double yStart = getPosition().getY();

//         g.setColor(new Color(140, 67, 188, 100));
//         g.fillRect((int) xStart,(int) yStart, (int) width, (int) height);

//         g.setColor(new Color(241, 194, 50));
//         g.drawRect((int) xStart,(int) yStart,(int) width,(int) height);

//         g.setColor(Color.white);
//         g.drawString("PLAYETH",(int) xStart+10,(int) yStart+10);
//     }
  
// }
