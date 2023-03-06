package Backend;
<<<<<<< HEAD
import java.awt.Color;
import Backend.Player;
public class HumanPlayer extends Player{
	public HumanPlayer(Color color, String name, String title) {
		super(color, name, title);
		// TODO Auto-generated constructor stub
	}
	boolean isHuman() {
		return true;
	}
=======

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

	public boolean isHuman() {
		return true;
	}

>>>>>>> branch 'master' of https://github.com/ijax1/Kingdomino
}
