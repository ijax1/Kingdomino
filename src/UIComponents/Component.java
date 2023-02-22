package UIComponents;

import java.awt.Graphics2D;

import Backend.Kingdomino;
import UIComponents.Render.Coordinate;

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

    public abstract void draw(Graphics2D g);

    public abstract void whenClicked();

}
