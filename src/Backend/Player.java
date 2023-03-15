package Backend;

import java.awt.Color;

public abstract class Player {
    
    
    // Instance variables
    private String name;
    private String title;
    private int score;
    private Color color;
    private Grid grid;
    private Domino currentDomino;
    private Domino nextDomino;
    private boolean placed;
    private boolean selected;

    // Constructor
    public Player(Color color, String name, String title) {
        this.color = color;
        this.name = name;
        this.title = title;
        this.score = 0;
        // Create empty grid
        this.grid = new Grid();
    }

    // Abstract method to check if player is human
    public abstract boolean isHuman();

    public boolean hasSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean hasPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }
    public boolean hasLegalMoves() {
    	return !grid.availableSpaces(getCurrentDomino()).isEmpty();
    }

    public int getScore() {
        return grid.calculateScore();
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
    
    public String getTitle() {
    	return title;
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

    // Places a domino on the grid at the given coordinates
    public void placeDomino(int x, int y, Domino domino) {
        if (grid.availableSpacesGrid(domino)[x][y]) {
            grid.placeDomino(x, y, domino);
        } else {
            // Activate message box
        }
    }
    public void setCurrentDomino(Domino domino) {
        this.currentDomino = domino;
    }
    // Sets the next domino to be played
    public void setNextDomino(Domino domino) {
        this.nextDomino = domino;
    }
    @Override
    public String toString() {
    	return name + "(" + title + ") Score: " + score + " Domino: " + currentDomino + " Next: " + nextDomino;
    }

}
