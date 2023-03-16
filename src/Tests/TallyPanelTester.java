package Tests;

import java.util.ArrayList;

import Backend.GameManager.GameState;
import Backend.Grid;
import Backend.Kingdomino;
import Backend.Player;
import Backend.Tile;
import Backend.Tile.Land;
import resources.OurColors;

public class TallyPanelTester {
    private static Land FOREST = Land.FOREST;
    private static Land LAKE = Land.LAKE;
    private static Land WHEAT = Land.WHEAT;
    private static Land SWAMP = Land.SWAMP;
    private static Land PASTURE = Land.PASTURE;
    private static Land MINE = Land.MINE;
    private static Land CASTLE = Land.CASTLE;
	public static void main(String[]args) {
		new TallyPanelTester().runGUI();
	}
	public void runGUI() {
		Tile[][] testGrid = new Tile[9][9];
		testGrid[2][2] = new Tile(SWAMP,1);
		testGrid[2][3] = new Tile(WHEAT,0);
		testGrid[2][4] = new Tile(WHEAT,0);
		testGrid[2][5] = new Tile(WHEAT,0);
		testGrid[2][6] = new Tile(WHEAT,0);
		
		testGrid[3][2] = new Tile(FOREST,1);
		testGrid[3][3] = new Tile(WHEAT,0);
		testGrid[3][4] = new Tile(WHEAT,0);
		testGrid[3][5] = new Tile(SWAMP,0);
		testGrid[3][6] = new Tile(MINE,3);

		testGrid[4][2] = new Tile(FOREST,0);
		testGrid[4][3] = new Tile(FOREST,0);
		testGrid[4][4] = new Tile(CASTLE,0);
		testGrid[4][5] = new Tile(SWAMP,0);
		testGrid[4][6] = new Tile(MINE,2);
		
		testGrid[5][2] = new Tile(LAKE,1);
		testGrid[5][3] = new Tile(LAKE,0);
		testGrid[5][4] = new Tile(LAKE,0);
		testGrid[5][5] = new Tile(PASTURE,2);
		testGrid[5][6] = new Tile(PASTURE,0);
		
		testGrid[6][2] = new Tile(LAKE,0);
		testGrid[6][3] = new Tile(LAKE,0);
		testGrid[6][4] = new Tile(LAKE,1);
		testGrid[6][5] = new Tile(FOREST,0);
		testGrid[6][6] = new Tile(FOREST,1);
				
		Grid grid = new Grid(testGrid);
		//Creating 4 fake players with scores 20, 30, 25 and 25 (this should test ties well)
		ArrayList<Player>fakePlayers = new ArrayList<Player>(4);
		fakePlayers.add(new TestPlayer(OurColors.RED, "Don Quixote", "The Ingenious",grid));
		fakePlayers.add(new TestPlayer(OurColors.BLUE, "King Arthur", "The Round",2));
		fakePlayers.add(new TestPlayer(OurColors.GREEN, "Sir Gawain", "The Green",grid));
		fakePlayers.add(new TestPlayer(OurColors.YELLOW, "Ian Jackson", "The Glorious",2));
		
		Kingdomino k = new Kingdomino();
		k.getManager().setPlayers(fakePlayers);
		k.getManager().setGameState(GameState.TALLY_SCORE);
//		GameManager gm = k.getManager();
//		

//		GamePanel panel = new GamePanel(fakePlayers, k);
//		frame.setSize(1280,720);
//		frame.add(panel);
//		panel.add(new UIDomino());
//		panel.add(new UIGrid());
//
//		frame.pack();
//		frame.setVisible(true);
//		panel.repaint();
	}
}
