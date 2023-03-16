package Tests;

import java.awt.Color;

import Backend.Grid;
import Backend.Player;
import resources.Titles;

public class TestPlayer extends Player {
	private int fakeScore;
	private Grid grid = new Grid();
	private boolean giveFakeScore = false;
	private boolean giveFakeGrid = false;
	public TestPlayer(Color color, String name) {
		this(color, name, new Titles().generateTitle());
	}
	public TestPlayer(Color color, String name, String title) {
		this(color, name, title, 0);
	}
	public TestPlayer(Color color, String name, String title, int fakeScore) {
		super(color, name, title);
		this.fakeScore = fakeScore;
		giveFakeScore = true;
	}
	public TestPlayer(Color color, String name, String title, Grid fakeGrid) {
		super(color, name, title);
		this.grid = fakeGrid;
		giveFakeGrid = true;
	}
	@Override
	public int getScore() {
		if(giveFakeScore) {
			return fakeScore;
		} else {
			return super.getScore();
		}
	}
	@Override
	public Grid getGrid() {
		if(giveFakeGrid) {
			System.out.println("test grid:");
			System.out.println(grid);
			return grid;
		} else {
			return super.getGrid();
		}
	}

	@Override
	public boolean isHuman() {
		// TODO Auto-generated method stub
		return true;
	}

}
