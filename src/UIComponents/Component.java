package UIComponents;

import java.awt.Graphics2D;

import Backend.GameManager;
import Backend.Kingdomino;
import UIComponents.Render.Coordinate;

public abstract class Component {
    private boolean minimized = false;
    private Coordinate position;
    private Kingdomino game;
    private GameManager gm;

    Component(Coordinate position, Kingdomino k){
        this.position = position;
        this.game = k;
        this.gm = k.getManager();
    }

    public void minimize(){
        minimized = true;
    }

    public void show(){
        minimized = false;
    }

    public Coordinate getPosition(){
        return position;
    };

    public boolean getMinimized() {return minimized;}

    public Kingdomino getGame(){
        return game;
    }
    public GameManager getManager(){
        return gm;
    }

    public abstract void setPosition(Coordinate coordinate);

    public abstract boolean onComponent(Coordinate c);

    public abstract void draw(Graphics2D g);

    public abstract void whenClicked();

    public boolean isMinimized(){
        return minimized;
    }

}
