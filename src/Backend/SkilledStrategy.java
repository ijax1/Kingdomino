package Backend;

import java.awt.*;
import java.util.ArrayList;

public class SkilledStrategy extends ComputerPlayer {

    private GridPosition bestPos;

    public SkilledStrategy(Color color, String name, String title, GameManager game) {
        super(color, name, title, game);
    }

    @Override
    public void calculateChoice() {
        Domino[] dominoes = getGameManager().getCurrDominoes();
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
        setNextDomino(bestDomino);
        setSelected(true);
    }

    @Override
    public void placeDomino() {
        getGrid().placeDomino(bestPos.getX(), bestPos.getY(), getNextDomino());
        setPlaced(true);
    }

}
