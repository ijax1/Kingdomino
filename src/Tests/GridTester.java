package Tests;

import Backend.Grid;
import Backend.Tile;
import Backend.Tile.Land;

public class GridTester {
    private static Land FOREST = Land.FOREST;
    private static Land LAKE = Land.LAKE;
    private static Land WHEAT = Land.WHEAT;
    private static Land SWAMP = Land.SWAMP;
    private static Land PASTURE = Land.PASTURE;
    private static Land MINE = Land.MINE;
    private static Land CASTLE = Land.CASTLE;
	public static void main(String[] args) {
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
		System.out.print(grid);
		System.out.print("Score: " + grid.calculateScore());
	}
}
