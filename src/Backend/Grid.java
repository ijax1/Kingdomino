package Backend;

import UIComponents.Domino;

public class Grid {
    private Tile[][] grid;

    Grid() {
        grid = new Tile[9][9];
    }

    private int[] findCenter() {
        int sumX = 0;
        int sumY = 0;
        int numTiles = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != null) {
                    sumX += i;
                    sumY += j;
                    numTiles++;
                }
            }
        }
        int[] center = new int[2];
        if (numTiles != 0) {
            center[0] = sumX / numTiles;
            center[1] = sumY / numTiles;
        } else {
            center = new int[]{5,5};
        }
        return center;
    }

    private Tile getTile(int x, int y) {
        return grid[x][y];
    }

    private boolean[][] availableSpaces(Domino domino) {
    }

}
