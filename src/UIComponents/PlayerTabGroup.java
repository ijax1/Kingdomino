//package UIComponents;
//
//import Backend.Kingdomino;
//import Backend.Player;
//import UIComponents.Render.Coordinate;
//
//import java.awt.Graphics2D;
//import java.util.ArrayList;
//import java.awt.*;
//
//public class PlayerTabGroup extends Component {
//    private final ArrayList<PlayerTabButton> group;
//    private PlayerTabButton selected;
//    private int selectedIndex;
//    private Graphics2D graphics;
//
//    PlayerTabGroup(ArrayList<Player> players, Kingdomino k) {
//    	super(new Coordinate(0,160,0), k);
//        double x = 0;
//        double y = 160;
//
//        group = new ArrayList<PlayerTabButton>();
//        for (Player p: players) {
//            Coordinate coord = new Coordinate(x, y,0);
//            group.add(new PlayerTabButton(coord, k, p));
//            //current height of each domino
//            y += 100;
//        }
//    }
//
//    public void setSelected(PlayerTabButton b) {
//        selected = b;
//        b.show();
//        for (PlayerTabButton button: group) {
//            if (button.getPlayer() != b.getPlayer()) {
//                button.minimize();
//            } else {
//                selectedIndex = group.indexOf(button);
//            }
//        }
//    }
//
//    public void updateOrder() {
//        boolean sorted = false;
//        while(!sorted) {
//            for (int i=0; i<group.size()-1; i++) {
//                Player p = group.get(i).getPlayer();
//                Player p2 = group.get(i+1).getPlayer();
//                if (p.getNextDomino().compareTo(p2.getNextDomino()) > 0) {
//                    sorted = false;
//                    PlayerTabButton temp = group.get(i);
//                    group.set(i, group.get(i+1));
//                    group.set(i+1, temp);
//                } else {
//                    sorted = true;
//                }
//            }
//        }
//	setSelected(group.get(0);
//    }
//
//	@Override
//	public void setPosition(Coordinate coordinate) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public boolean onComponent(Coordinate c) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void draw(Graphics2D g) {
//	graphics = g;
//        for (PlayerTabButton button: group) {
//            button.draw(g);
//        }
//
//        if (selected.getPlayer() != getGame().getManager().getCurrentPlayer()) {
//            g.setColor(new Color(241, 194, 50, 100));
//            int width = 50, height = 100;
//            int[] tipX = {width + 10, width + 50, width + 50};
//            int[] tipY = {(int) (((selectedIndex - 0.5) * height) + getPosition().getY()),
//                    (int) ((selectedIndex-1) * height + getPosition().getY()),
//                    (int) ((selectedIndex) * height + getPosition().getY())};
//            g.fillPolygon(new Polygon(tipX, tipY, 3));
//
//            // height = y constant * 2
//            g.drawRect(tipX[2], tipY[0] - 20, 70, 40);
//        }
//	}
//
//	// trying to reduce the amount of times it needs to redraw?
//	// when integrating, should run playerTabButton whenClicked() first bc it is updating information used by playertabgroup
//	// actually when we add viewed player this whenclicked() will look differently.
//	@Override
//	public void whenClicked() {
//// 	for (PlayerTabButton buttons: group) {
//// 		if (button.isSelected == true) {
//// 			if (selected != button) {
//// 				setSelected(button);
//// 				g.draw()
//// 			}
//// 		}
//// 	}
//
//	// shoudl look smth like this instead in theory ????
//// 	if (getGame().getManager().getViewedPlayer() != selected.player()) {
//// 		draw(graphics)
//// 	}
//	}
//
//    // returns selected player - do we want to determine background based on player or button
////    public Player getSelected() {
////        return selected.getPlayer();
////    }
//}
