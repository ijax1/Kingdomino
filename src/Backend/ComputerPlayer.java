package Backend;
import java.awt.Color;


abstract class ComputerPlayer extends Player {

	private final GameManager game;

	public ComputerPlayer(Color color, String name, String title, GameManager game) {
		super(color, title, title);
		this.game = game;
	}

	public GameManager getGame() {
		return game;
	}
	
	public boolean isHuman() {
		return false;
	}

	abstract public void placeDomino();
	abstract public void calculateChoice();

}