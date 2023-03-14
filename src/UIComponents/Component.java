package UIComponents;

import java.awt.Graphics2D;

import Backend.GameManager;
import Backend.Kingdomino;
import UIComponents.Render.Coordinate;

public abstract class Component {
    private boolean shown = true;
    private Coordinate position;
    private Kingdomino game;
    private GameManager gm;

    Component(Coordinate position, Kingdomino k){
        this.position = position;
        this.game = k;
        if(k!=null) {
        	this.gm = k.getManager();
        }
    }
    //TODO: these are backwards, ask jonathan how to fix
    public void minimize(){
        shown = false;
    }

    public void show(){
        shown = true;
    }

    public Coordinate getPosition(){
        return position;
    };

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

    public boolean isShown(){
        return shown;
    }

}
