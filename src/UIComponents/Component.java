package UIComponents;

import java.awt.Graphics2D;

import Backend.Kingdomino;
import UIComponents.Render.Coordinate;

public abstract class Component {
    private boolean minimized;
    private Coordinate position;
    private Kingdomino game;
    //private GameManager gm;

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

    public boolean getMinimized() {return minimized;}

    public Kingdomino getGame(){
        return game;
    }

    public abstract void setPosition(Coordinate coordinate);

    public abstract boolean onComponent(Coordinate c);

    public abstract void draw(Graphics2D g);

    public abstract void whenClicked();

}
