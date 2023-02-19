package Backend;

import java.awt.Color;
import java.util.Random;

public abstract class Player {

    //class vars
    static final String MEDIEVAL_TITLES;

    //instance
    String name;
    int score;
    Color color;
    Grid grid;
    Domino currentDomino;
    Domino nextDomino;

    //methods
    public Player(Color color, String name, String title) {
        this.color = color;
        this.name = name;
        this.score = 0;

        //create empty grid
        grid = new Grid(title);
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String generateTitles() {
        Random rand = new Random();
    }



}
