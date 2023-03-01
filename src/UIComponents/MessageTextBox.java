package UIComponents;

import UIComponents.Render.Coordinate;

import java.awt.*;
import Backend.Kingdomino;

public class MessageTextBox extends Component {
    MessageTextBox(Coordinate c, Kingdomino k) {
        super(c, k);
    }


    @Override
    public void setPosition(Coordinate coordinate) {}

    @Override
    public boolean onComponent(Coordinate c) {
        return false;}

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void whenClicked() {}
}
