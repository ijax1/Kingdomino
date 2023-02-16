package UIComponents;

import UIComponents.Render.*;
import Backend.*;

import java.awt.*;

public abstract class Button extends Component{

    boolean clicked;

    Button(Coordinate position, Kingdomino k) {
        super(position, k);
        this.clicked = false;
    }

    @Override
    public void setPosition(Coordinate coordinate) {

    }

    @Override
    public boolean onComponent(Coordinate c) {
        return false;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void whenClicked() {
        this.clicked = !this.clicked;
        doAction();
    }

    abstract public void doAction();
}
