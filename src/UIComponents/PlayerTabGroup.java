package UIComponents;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Backend.Domino;
import Backend.GameEventListener;
import Backend.GameManager;
import Backend.GameManager.GameState;
import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;

public class PlayerTabGroup extends Component {
    private final ArrayList<PlayerTabButton> group;
    private PlayerTabButton selected;
    private int selectedIndex;
    private Graphics2D graphics;
    private GamePanel gp;
    private GameManager gm;

    PlayerTabGroup(ArrayList<Player> players, Kingdomino k, GamePanel gp) {
        super(new Coordinate(0, 160, 0), k);
        double x = 0;
        double y = 70;
        this.gp = gp;
        this.gm = k.getManager();

        group = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            Coordinate coord = new Coordinate(x, y, 0);
            group.add(new PlayerTabButton(coord, k, players.get(i), this, gp));
            //current height of each domino
            y += 150;
        }
        setSelected(group.get(0));
    }

    public void updatePlayers(ArrayList<Player> players, ArrayList<Integer> playerOrder) {
        for (int i = 0; i < group.size(); i++) {
        	int order = playerOrder.get(i);
            group.get(i).setPlayer(players.get(order));
        }
    }

    public ArrayList<PlayerTabButton> getButtons() {
        return group;
    }

    public void setSelected(PlayerTabButton b) {
        selected = b;
        for (PlayerTabButton button : group) {
            if (button == b) {
                button.show();
                //gp.setViewedPlayer(button.getPlayer());
            } else {
                button.minimize();

            }
        }
    }

    void selectButton(Coordinate c) {
        PlayerTabButton selected = group.get(0);
        for (PlayerTabButton p : group) {
            p.minimize();
            if (p.onComponent(c))
                selected = p;
        }
        selected.show();
        gp.setViewedPlayer(selected.getPlayer());

    }

    void selectButton(Player selectedPlayer) {
        PlayerTabButton selectedButton = group.get(0);
        for (PlayerTabButton button : group) {
            button.minimize();
            if (button.getPlayer() == selectedPlayer) {
            	
                selectedButton = button;
            }
        }
        selectedButton.show();
        
        //Here's the problem
        
        gp.setViewedPlayer(selectedButton.getPlayer());
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
    }

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
    }
}
