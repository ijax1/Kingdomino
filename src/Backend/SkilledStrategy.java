package Backend;

import java.awt.Color;
import java.util.ArrayList;

import resources.Titles;

public class SkilledStrategy extends ComputerPlayer {

    private GridPosition bestPos;
    private int bestRot = 0;
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
        bestRot = 0;
        Domino bestDomino = dominoes[0];
        for (Domino domino : dominoes) {
        	for(int i=0; i<4; i++) {
        		domino.incrementRotation();
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
		                    bestRot = domino.getRotation();
		                    bestDomino = domino;
		                    maxScore = currScore;
		                }
		            }
	            }
        	}
        }
        setSelected(true);
        setNextDomino(bestDomino);
        
    }

    @Override
    public void placeDomino(Domino[]d, ArrayList<Player>p) {
        if(bestPos != null && getNextDomino() != null) {
        	Domino dom = getNextDomino();
        	dom.setRotation(bestRot);
            getGrid().placeDomino(bestPos.getX(), bestPos.getY(), dom);
        }
        setPlaced(true);
    }

}
