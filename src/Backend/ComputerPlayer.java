package Backend;
import java.awt.Color;

abstract class ComputerPlayer extends Player {

	public ComputerPlayer(Color color, String name, String title) {
		super(color, title, title);
	}
	
	public boolean isHuman() {
		return false;
	}
	
	abstract Domino calculateChoice();
	
	abstract int[] calculatePlacement();
	
}