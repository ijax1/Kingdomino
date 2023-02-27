package UIComponents;

import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;

import java.util.ArrayList;

public class PlayerTabGroup {
    private final ArrayList<PlayerTabButton> group;
    private PlayerTabButton selected;

    PlayerTabGroup(ArrayList<Player> players, Kingdomino k) {
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

    // returns selected player - do we want to determine background based on player or button
//    public Player getSelected() {
//        return selected.getPlayer();
//    }
}
