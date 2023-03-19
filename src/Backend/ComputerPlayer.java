package Backend;
import java.awt.Color;
import java.util.ArrayList;


abstract class ComputerPlayer extends Player {

	private final GameManager game;

	public ComputerPlayer(Color color, String name, String title, GameManager game) {
		super(color, name, title);
		this.game = game;
	}

//	public GameManager getGameManager() {
//		return game;
//	}
	
	public boolean isHuman() {
		return false;
	}

	abstract public void placeDomino(Domino[]choices, ArrayList<Player>currentPlayers);

	abstract public void calculateChoice(Domino[]choices, ArrayList<Player>currentPlayers);

}