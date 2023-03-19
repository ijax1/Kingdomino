package Backend;

import java.awt.*;
import java.util.ArrayList;

public class SkilledStrategy extends ComputerPlayer {

    private GridPosition bestPos;

    public SkilledStrategy(Color color, String name, String title, GameManager game) {
        super(color, name, title, game);
    }
    /** Note: don't call if there are no choices
     * 
     */
    @Override
    public void calculateChoice(Domino[]d, ArrayList<Player>p) {
        Domino[] dominoes = d;
        int maxScore = 0;
        Domino bestDomino = dominoes[0];
        for (Domino domino : dominoes) {
            ArrayList<GridPosition> positions = getGrid().availableSpaces(domino);
            Grid potentialGrid;
            bestPos = positions.get(0);
            for (GridPosition pos : positions) {
                potentialGrid = getGrid().copy();
                potentialGrid.placeDomino(pos.getX(), pos.getY(), domino);
                int currScore = potentialGrid.calculateScore();
                if (currScore > maxScore) {
                    bestPos = pos;
                    bestDomino = domino;
                    maxScore = currScore;
                }
            }
        }
        setSelected(true);
        setNextDomino(bestDomino);
        
    }

    @Override
    public void placeDomino(Domino[]d, ArrayList<Player>p) {
        getGrid().placeDomino(bestPos.getX(), bestPos.getY(), getNextDomino());
        setPlaced(true);
    }

}
