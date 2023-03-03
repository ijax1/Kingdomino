package Backend;

import java.awt.Color;

public class HumanPlayer extends Player {

	private final GameManager game;

	public HumanPlayer(Color color, String name, String title, GameManager game) {
		super(color, name, title);
		this.game = game;
	}

	public GameManager getGame() {
		return game;
	}
	
	boolean isHuman() {
		return true;
	}
}
