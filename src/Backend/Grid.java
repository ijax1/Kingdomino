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
            center = new int[]{5, 5};
        }
        return center;
    }

    private Tile getTile(int x, int y) {
        return grid[x][y];
    }

    public boolean[][] availableSpaces(Domino domino) {
        boolean[][] spaces = new boolean[9][9];
        double rotation = domino.getRotation();

        // relative position for tile 2 where tile 1 is (0,0)
        int changeX = 0;
        int changeY = 0;
        if (rotation == 0) {
            changeX = 1;
        } else if (rotation == 180) {
            changeX = -1;
        }
        if (rotation == 90) {
            changeY = 1;
        } else if (rotation == 270) {
            changeY = -1;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                spaces[i][j] = isValidPos(i + changeX, j + changeY) && grid[i][j] != null && grid[i + changeX][j + changeY] != null;
            }
        }
        return spaces;
    }

    public void placeDomino(int x, int y, Domino domino) {

    }

    private boolean isValidPos(int x, int y) {
        return 0 <= x && x < 9 && 0 <= y && y < 9;
    }
    /*
    public ArrayList<Region> getContiguous() {
        boolean[][] covered = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!covered[i][j]) {
                    Region region = new Region();
                    region.setLandType(grid[i][j].getLandType());
                    findRegion(covered, i, j, region);

                }
            }
        }
    }

    private void findRegion(boolean[][] covered, int x, int y, Region region) {
        if (grid[x][y] != null && grid[x][y].getLandType() == region.land) {
            region.addPosition(new int[]{x, y});

            covered[x][y] = true;
            findRegion(covered, x + 1, y, land, region);
            findRegion(covered, x - 1, y, land, region);
            findRegion(covered, x, y + 1, land, region);
            findRegion(covered, x, y - 1, land, region);
        } else {
            covered[x][y] = true;
        }

    }
     */
}
