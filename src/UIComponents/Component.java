package UIComponents;

import Render.*;
import Backend.*;

import java.awt.*;

public abstract class Component {
    boolean minimized;
    Coordinate position;
    Kingdomino game;

    public void minimize(){
        minimized = false;
    }

    public void show(){
        minimized = true;
    }

    public abstract Coordinate getPosition();

    public abstract void setPosition(Coordinate coordinate);

    public abstract boolean onComponent(Coordinate c);

    public abstract void draw(Graphics g);

    public abstract void whenClicked();

}
