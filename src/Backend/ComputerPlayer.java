package Backend;
import java.awt.Color;

import UIComponents.Domino;

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