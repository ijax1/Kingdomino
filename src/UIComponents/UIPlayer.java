package UIComponents;

import Backend.Kingdomino;
import Backend.Player;
import UIComponents.Render.Coordinate;

import java.awt.*;
import java.rmi.server.UID;

//TODO delete this class before turn in
public class UIPlayer extends Component{
    private Player ref;
    private UIGrid grid;
    private UIDomino domino;
    private Coordinate center;

    UIPlayer(Coordinate position, Kingdomino k, Player p) {
        super(position, k);
        this.grid = new UIGrid(position, k, p.getGrid());
        this.domino = new UIDomino(position, k, p.getCurrentDomino());
    }

    @Override
    public void setPosition(Coordinate coordinate) {
        center = coordinate;
        grid.setPosition(coordinate);
        domino.setPosition(coordinate);
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
