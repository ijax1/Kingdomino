package Backend;

import java.awt.Color;

public class HumanPlayer extends Player {

	private final GameManager game;

	private Grid grid;

	public HumanPlayer(Color color, String name, String title, GameManager game) {
		super(color, name, title);
		// grid = new Grid();
		this.game = game;
	}

//	public GameManager getGame() {
//		return game;
//	}

	public boolean isHuman() {
		return true;
	}

}
