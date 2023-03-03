package UIComponents;

import UIComponents.Render.Coordinate;

import java.awt.*;
import Backend.Kingdomino;

public class MessageTextBox extends Component {
    MessageTextBox(Coordinate c, Kingdomino k) {
        super(c, k);
    }

    @Override
    public void setPosition(Coordinate coordinate) {
    }

    @Override
    public boolean onComponent(Coordinate c) {
        return false;}

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(140, 67, 188, 100));
        //filler dimensions rn
        if (getMinimized()) {
            g.setColor(Color.white);
            g.drawString("MESSAGE", (int) getPosition().getX(), 1200);
        } else {
            //g.draw(getPosition().getX(), getPosition().getY(), 500, 200);
        }
    }

    @Override
    public void whenClicked() {

    }
}
