package Backend;
import java.awt.Color;
import java.util.ArrayList;

public class RandomStrategy extends ComputerPlayer {
	
	GameManager game;
    Deck deck;
    Grid grid = getGrid();


    public RandomStrategy(Color color, String name, String title, GameManager game) {
        super(color, name, title, game);
        this.game = game;
        deck = game.getDeck();
    }

    public GameManager getGame() {
        return game;
    }

    private static int randomNum(int x) {
        int num = (int) (Math.random() * x);
        return num;
    }

    public void calculateChoice() {
        Domino[] choices = deck.getDominoesToSelect();
        int ranChoice = randomNum(4);
        Domino chosenDomino = choices[ranChoice];
        setNextDomino(chosenDomino);
    }


    
    public void placeDomino() {   	 
      	 Domino domino = getNextDomino();
      	 
      	 //randomly set rotation of domino:
      	 int ranRot = randomNum(3)*90;
      	 domino.setRotation(ranRot);
      	 
      	 //randomly select available placement:
      	 ArrayList<GridPosition> placements = grid.availableSpaces(domino);
      	 int ranPlace = randomNum(placements.size());
      	 
      	 //calls placeDomino:
      	 grid.placeDomino(placements.get(ranPlace).getX(),placements.get(ranPlace).getY(), domino);
   	}


}





