package Backend;
import java.awt.Color;

public class RandomStrategy extends ComputerPlayer {

    GameManager game = new GameManager();
    Deck deck = game.getDeck();
    Grid grid = game.getGrid();

    
    public RandomStrategy(Color color, String name, String title) {
   	 super(color, name, title);
    }    
    
    private static int randomNum() {
   	 int num = (int) (Math.random() * 4);
   	 return num;
    }
    
    Domino calculateChoice() {
   	 Domino chosenDomino;
   	 Domino[] choices = deck.getDominoesToSelect();
   	 int ranChoice = randomNum();
   	 chosenDomino = choices[ranChoice];
   	 return chosenDomino;
    }

    
    int[] calculatePlacement() {
   	 grid.availableSpaces(getCurrentDomino());
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
