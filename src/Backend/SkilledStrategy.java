package Backend;

import java.awt.*;
import java.util.ArrayList;

public class SkilledStrategy extends ComputerPlayer {
    public SkilledStrategy(Color color, String name, String title) {
        super(color, name, title);
    }

    @Override
    public void placeDomino() {

    }

//    @Override
    private Domino calculateChoice() {

        // need access to game manager
        GameManager g = getGame();
        Domino[] dominos = g.getDeck().getDominoesToSelect();
        // what?
    }



    @Override
    public void calculatePlacement() {
        Grid grid = getGrid();
        Domino domino = getNextDomino();
        ArrayList<GridPosition> positions = grid.availableSpaces(domino);
        Grid potentialGrid;
        GridPosition bestPos = positions.get(0);
        int maxScore = 0;
        for (GridPosition pos : positions) {
            potentialGrid = grid.copy();
            potentialGrid.placeDomino(pos.getX(), pos.getY(), domino);
            int currScore = potentialGrid.calculateScore();
            if (currScore > maxScore) {
                bestPos = pos;
                maxScore = currScore;
            }
        }
        grid.placeDomino(bestPos.getX(), bestPos.getY(), domino);
    }
}
