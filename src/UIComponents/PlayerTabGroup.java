package UIComponents;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;

public class PlayerTabGroup extends Component {
    private final ArrayList<PlayerTabButton> group;
    private PlayerTabButton selected;
    private int selectedIndex;
    private Graphics2D graphics;
    private GamePanel gp;

    PlayerTabGroup(ArrayList<Player> players, Kingdomino k, GamePanel gp) {
        super(new Coordinate(0, 160, 0), k);
        double x = 0;
        double y = 160;
        this.gp = gp;

        group = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            Coordinate coord = new Coordinate(x, y, 0);
            group.add(new PlayerTabButton(coord, k, players.get(i), this, gp));
            //current height of each domino
            y += 100;
        }
//        System.out.println(players);
        setSelected(group.get(0));
    }

    public void updatePlayers(ArrayList<Player> players) {
        for (int i = 0; i < group.size(); i++) {
            group.get(i).setPlayer(players.get(i));
        }
    }

    public ArrayList<PlayerTabButton> getButtons() {
        return group;
    }

    public void setSelected(PlayerTabButton b) {
        selected = b;
        for (PlayerTabButton button : group) {
            if (button == b) {
//                System.out.println("equals button");
                button.show();
                selectedIndex = group.indexOf(button);
                gp.setViewedPlayerIdx(selectedIndex);
            } else {
//                System.out.println("not equals button");
                button.minimize();

            }
        }
    }

    public void selectButton(Coordinate c) {
        PlayerTabButton selected = group.get(0);
        for (PlayerTabButton p : group) {
            p.minimize();
            if (p.onComponent(c))
                selected = p;
        }
        selected.show();
        selectedIndex = group.indexOf(selected);
        gp.setViewedPlayerIdx(selectedIndex);

    }

    public void selectButton(Player selectedPlayer) {
        PlayerTabButton selectedButton = group.get(0);
        for (PlayerTabButton button : group) {
            button.minimize();

            if (button.getPlayer() == selectedPlayer) {
                selectedButton = button;
            }
        }
        selectedButton.show();
        selectedIndex = group.indexOf(selectedButton);
        gp.setViewedPlayerIdx(selectedIndex);
    }

    public void updateOrder() {
        /*
        boolean sorted = false;
        while (!sorted) {
            for (int i = 0; i < group.size() - 1; i++) {
                Player p = group.get(i).getPlayer();
                Player p2 = group.get(i + 1).getPlayer();
                if (p.getNextDomino().compareTo(p2.getNextDomino()) > 0) {
                    sorted = false;
                    PlayerTabButton temp = group.get(i);
                    group.set(i, group.get(i + 1));
                    group.set(i + 1, temp);
                } else {
                    sorted = true;
                }
            }
        }

         */

        double x = 0;
        double y = 160;
        ArrayList<Player> players = getManager().getPlayers();
        //System.out.println(players.get(0).getCurrentDomino());
        for(int i = 0; i < 4; i++){
            Coordinate coord = new Coordinate(x, y,0);
            group.set(i, new PlayerTabButton(coord,getGame(),players.get(i),this,gp));
            y+=100;
        }


    }

    @Override
    public void setPosition(Coordinate coordinate) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onComponent(Coordinate c) {
        for (PlayerTabButton p : group) {
            if (p.onComponent(c))
                return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics2D g) {
        graphics = g;
        for (PlayerTabButton button : group) {
            if (!button.isShown()) {
                button.draw(g);
            }
        }
        for (PlayerTabButton button : group) {
            if (button.isShown()) {
                button.draw(g);
            }
        }
        /*
        if (selected.getPlayer() != getGame().getManager().getCurrentPlayer()) {
            g.setColor(new Color(241, 194, 50, 100));
            int width = 50, height = 100;
            int[] tipX = {width + 10, width + 50, width + 50};
            int[] tipY = {(int) (((selectedIndex - 0.5) * height) + getPosition().getY()),
                    (int) ((selectedIndex-1) * height + getPosition().getY()),
                    (int) ((selectedIndex) * height + getPosition().getY())};
            g.fillPolygon(new Polygon(tipX, tipY, 3));

            // height = y constant * 2
            g.fillRect(tipX[2], tipY[0] - 20, 70, 40);
        }

         */
    }

    // trying to reduce the amount of times it needs to redraw?
    // when integrating, should run playerTabButton whenClicked() first bc it is updating information used by playertabgroup
    // actually when we add viewed player this whenclicked() will look differently.
    @Override
    public void whenClicked() {
        boolean run = false;
        for (PlayerTabButton button : group) {
            if (!button.isShown()) {
                if (button != selected) {
                    setSelected(button);
                    run = true;
                }
            }
        }
        if (run) {
            draw(graphics);
        }

        // shoudl look smth like this instead in theory ????

// 	if (getGame().getManager().getViewedPlayer() != selected.player()) {
// 		setSelected(); 
// 		draw(graphics) 
// 	}
    }

    // returns selected player - do we want to determine background based on player or button
//    public Player getSelected() {
//        return selected.getPlayer();
//    }
}
