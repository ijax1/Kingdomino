package Backend;
import java.awt.Color;

public class RandomStrategy extends ComputerPlayer {

    GameManager game = new GameManager();
    //we need to add getDeck and getGrid to GameManager:
    Deck deck = game.getDeck();
    Grid grid = getGrid();

    
    public RandomStrategy(Color color, String name, String title) {
    	super(color, name, title);
    }    
    
    private static int randomNum(int x) {
    	int num = (int) (Math.random() * x);
    	return num;
    }
    
    Domino calculateChoice() {
    	Domino[] choices = deck.getDominoesToSelect();
    	int ranChoice = randomNum(4);
    	Domino chosenDomino = choices[ranChoice];
    	return chosenDomino;
    }

    
    int[] calculatePlacement() {    	
    	//randomly set rotation of domino
    	
    	//getRotation()
     	
    	//setRotation(int)
    	boolean[][] places = grid.availableSpaces(calculateChoice());
    	
    	
    	
    	
    	//not sure about this:
    	int ranPlace = randomNum(9);

    	for(int x=0; x<9; x++) {
    		
    	}
    	
    	
    	//getCurrentDomino()
    	
   	 //view grid
   	 //define legal moves --> availableSpaces(Domino domino)
   	 //if move legal, put possible move in arrayList
   	 //get size of arrayList
   	 //generate random # between size of arrayList
   	 //select move # of arrayList
   	 //return move

   	 return null;
    }
    
}
