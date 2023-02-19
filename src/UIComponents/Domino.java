package UIComponents;

import Backend.Kingdomino;
import UIComponents.Render.Coordinate;

import java.awt.*;

public class Domino extends Component{
    Domino(Coordinate position, Kingdomino k) {
        super(position, k);
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
