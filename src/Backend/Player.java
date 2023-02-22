package Backend;

import java.awt.Color;
import java.util.Random;

public abstract class Player {

    //class vars
    static final String[] MEDIEVAL_TITLES = {"The Honorable", "The Great", "The Wise", "The Bold", "The Valiant", "The Chivalrous", "The Magnificent", "The Noble", "The Righteous", "The Sagacious"};

    //instance
    private String name;
    private int score;
    private Color color;
    private Grid grid;
    private Domino currentDomino;
    private Domino nextDomino;

    //methods

    abstract boolean isHuman();

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
        // Random rand = new Random();
        // int randIndex = rand.nextInt(MEDIEVAL_TITLES.length);
        // name = MEDIEVAL_TITLES[randIndex];
    }

    public String generateAIName() {

    }

    public Grid getGrid() {
        return grid;
    }

    public void placeDomino(int x, int y, Domino domino) {
        if (grid.avilableSpaces(domino)) {
            grid.placeDomino(x, y, domino);
        } else {
            //prompt user?
        }
    }

    public void selectedDomino(Domino domino) {

    }

    public String generateTitle() {

    }
}
