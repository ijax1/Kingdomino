package Backend;

import java.awt.Color;
import java.util.Random;

public abstract class Player {
    // Class variables
    static final String[] MEDIEVAL_TITLES = {"The Honorable", "The Great", "The Wise", "The Bold", "The Valiant", "The Chivalrous", "The Magnificent", "The Noble", "The Righteous", "The Sagacious"};

    // Instance variables
    private String name;
    private int score;
    private Color color;
    private Grid grid;
    private Domino currentDomino;
    private Domino nextDomino;

    // Constructor
    public Player(Color color, String name, String title) {
        this.color = color;
        this.name = name;
        this.score = 0;
        // Create empty grid
        this.grid = new Grid(title);
    }

    // Abstract method to check if player is human
    abstract boolean isHuman();

    // Getters for instance variables
    public int getScore() {
        return score;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public Grid getGrid() {
        return grid;
    }

    public Domino getCurrentDomino() {
        return currentDomino;
    }

    public Domino getNextDomino() {
        return nextDomino;
    }

    // Generates a random medieval title
    public String generateTitle() {
        Random rand = new Random();
        int randIndex = rand.nextInt(MEDIEVAL_TITLES.length);
        return MEDIEVAL_TITLES[randIndex];
    }

    // Returns a temporary name for the AI
    public String generateAIName() {
        return "Computer";
    }

    // Places a domino on the grid at the given coordinates
    public void placeDomino(int x, int y, Domino domino) {
        if (grid.availableSpaces(domino)) {
            grid.placeDomino(x, y, domino);
        } else {
            // Activate message box
        }
    }
    
    // Sets the next domino to be played
    public void selectDomino(Domino domino) {
        this.nextDomino = domino;
    }

}
