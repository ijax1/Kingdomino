package Tests;

import java.awt.Color;

import Backend.Grid;
import Backend.Player;

public class TestPlayer extends Player {

	public TestPlayer(Color color, String name, String title) {
		super(color, name, title);
	}

	@Override
	public boolean isHuman() {
		// TODO Auto-generated method stub
		return false;
	}

}
