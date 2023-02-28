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

    
    public void calculatePlacement() {   	 
      	 Domino domino = calculateChoice();
      	 
      	 //randomly set rotation of domino:
      	 int ranRot = randomNum(3)*90;
      	 domino.setRotation(ranRot);
      	 
      	 boolean[][] places = grid.availableSpaces(domino);
      			 
      	 //not sure about this:
      	 //int ranPlacey = randomNum(places.length);
      	 //int ranPlacex = randomNum(places[0].length);
      	 
      	 if(places[ranPlacex][ranPlacey]) {
           	grid.placeDomino(ranPlacey, ranPlacex, domino);
      	 }
      	 
      	 
      	 //getCurrentDomino()
      	 
     	  //view grid
     	  //define legal moves --> availableSpaces(Domino domino)
     	  //if move legal, put possible move in arrayList
     	  //get size of arrayList
     	  //generate random # between size of arrayList
     	  //select move # of arrayList
     	  //return move
   	}

    
    
}
