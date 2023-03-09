package UIComponents;

import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;

import java.awt.*;
import java.rmi.server.UID;

public class UIPlayer extends Component{
    private Player ref;
    private UIGrid grid;
    private UIDomino domino;

    UIPlayer(Coordinate position, Kingdomino k, Player p) {
        super(position, k);
        this.grid = new UIGrid(position, p.getGrid());
    }

    @Override
    public void setPosition(Coordinate coordinate) {

    }

    @Override
    public boolean onComponent(Coordinate c) {
        return false;
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void whenClicked() {

    }
}
