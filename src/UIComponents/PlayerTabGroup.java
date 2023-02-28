package UIComponents;

import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class PlayerTabGroup extends Component {
    private final ArrayList<PlayerTabButton> group;
    private PlayerTabButton selected;

    PlayerTabGroup(ArrayList<Player> players, Kingdomino k) {
    	super(new Coordinate(0,160,0), k);
        double x = 0;
        double y = 160;

        group = new ArrayList<PlayerTabButton>();
        for (Player p: players) {
            Coordinate coord = new Coordinate(x, y,0);
            group.add(new PlayerTabButton(coord, k, p));
            //current height of each domino
            y += 100;

        }
    }

    public void setSelected(PlayerTabButton b) {
        selected = b;
        b.show();
        for (PlayerTabButton button: group) {
            if (button.getPlayer() != b.getPlayer()) {
                button.minimize();
            }
        }
    }

    public void updateOrder() {
        boolean sorted = false;
        while(!sorted) {
            for (int i=0; i<group.size()-1; i++) {
                Player p = group.get(i).getPlayer();
                Player p2 = group.get(i+1).getPlayer();
                if (p.getNextDomino().compareTo(p2.getNextDomino()) > 0) {
                    sorted = false;
                    PlayerTabButton temp = group.get(i);
                    group.set(i, group.get(i+1));
                    group.set(i+1, temp);
                } else {
                    sorted = true;
                }
            }
        }
    }

	@Override
	public void setPosition(Coordinate coordinate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onComponent(Coordinate c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
        for (PlayerTabButton button: group) {
            button.draw(g);
        }
	}

	@Override
	public void whenClicked() {
		// TODO Auto-generated method stub
		
	}

    // returns selected player - do we want to determine background based on player or button
//    public Player getSelected() {
//        return selected.getPlayer();
//    }
}
