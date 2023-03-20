package Backend;
import java.awt.Color;
import java.util.ArrayList;

import resources.Titles;


public abstract class ComputerPlayer extends Player {

	private final GameManager game;
	private static Titles t = new Titles();
	private static String defaultName = t.generateName();
	private static String defaultTitle = t.generateTitle(); 
	
	public ComputerPlayer(Color color, GameManager game) {
		super(color, defaultName, defaultTitle);
		this.game = game;
	}

//	public GameManager getGameManager() {
//		return game;
//	}
	
	public boolean isHuman() {
		return false;
	}
	abstract public String getStrategyName();
	@Override
	abstract public String getTitle();
	@Override
	abstract public String getName();

	abstract public void placeDomino(Domino[] choices, ArrayList<Player> currentPlayers);

	abstract public void calculateChoice(Domino[] choices, ArrayList<Player> currentPlayers);

}