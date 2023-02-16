package UIComponents;

import UIComponents.Render.*;
import Backend.*;

import java.awt.*;

public abstract class Component {
    boolean minimized;
    Coordinate position;
    Kingdomino game;

    Component(Coordinate position, Kingdomino k){
        this.position = position;
        this.game = k;
    }

    public void minimize(){
        minimized = false;
    }

    public void show(){
        minimized = true;
    }

    public Coordinate getPosition(){
        return position;
    };

    public Kingdomino getGame(){
        return game;
    }

    public abstract void setPosition(Coordinate coordinate);

    public abstract boolean onComponent(Coordinate c);

    public abstract void draw(Graphics g);

    public abstract void whenClicked();

}
