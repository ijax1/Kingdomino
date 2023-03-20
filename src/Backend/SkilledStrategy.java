package Backend;

import java.awt.*;
import java.util.ArrayList;

import resources.Titles;

public class SkilledStrategy extends ComputerPlayer {

    private GridPosition bestPos;
    private String title = Titles.generateTitle();
    private String name = Titles.generateName();

    public SkilledStrategy(Color color, GameManager game) {
        super(color, game);
    }
    public String getStrategyName() {
    	return "Skilled Strategy";
    }
    @Override
    public String getTitle() {
    	return title;
    }
    @Override
    public String getName() {
    	return name;
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
            if(!positions.isEmpty()) {
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
