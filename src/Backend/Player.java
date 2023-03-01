package Backend;

import UIComponents.*;

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
    private boolean placed;
    private boolean selected;
    private boolean legalMoves;

    // Constructor
    public Player(Color color, String name, String title) {
        this.color = color;
        this.name = name;
        this.score = 0;
        // Create empty grid
        this.grid = new Grid();
    }

    // Abstract method to check if player is human
    abstract boolean isHuman();

    // Getters and setters for instance variables\
    public boolean getLegalMoves() {
        return legalMoves;
    }
    public void setLegalMoves(boolean legalMove) {
        this.legalMoves = legalMove;
    }
    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean getPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

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
        if (grid.availableSpaces(domino)[x][y]) {
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
