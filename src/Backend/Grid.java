package Backend;

import java.util.ArrayList;

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

    public Tile getTile(int x, int y) {
        return grid[x][y];
    }

    public boolean[][] availableSpaces(Domino domino) {
        boolean[][] spaces = new boolean[9][9];
        double rotation = domino.getRotation();
        int[] relPos = relPos(domino);
        int changeX = relPos[0];
        int changeY = relPos[1];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                spaces[i][j] = isValidPos(i + changeX, j + changeY)
                        && grid[i][j] == null
                        && grid[i + changeX][j + changeY] == null;
            }
        }
        return spaces;
    }

    // relative position for tile 2 where tile 1 is (0,0)
    private int[] relPos(Domino domino) {
        int rotation = domino.getRotation();
        int deltaX = 0;
        int deltaY = 0;
        if (rotation == 0) {
            deltaX = 1;
        } else if (rotation == 180) {
            deltaX = -1;
        }
        if (rotation == 90) {
            deltaY = 1;
        } else if (rotation == 270) {
            deltaY = -1;
        }
        return new int[]{deltaX, deltaY};
    }

    public void placeDomino(int x, int y, Domino domino) {
        int[] relPos = relPos(domino);
        int deltaX = relPos[0];
        int deltaY = relPos[1];
        Tile[] tiles = domino.getTiles();
        if (isValidPos(x, y) && isValidPos(x + deltaX, y + deltaY)) {
            grid[x][y] = tiles[0];
            grid[x + deltaX][y + deltaY] = tiles[1];
        }
    }

    private boolean isValidPos(int x, int y) {
        return 0 <= x && x < 9 && 0 <= y && y < 9;
    }
//
//    public ArrayList<Region> getContiguous() {
//        boolean[][] covered = new boolean[9][9];
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                if (!covered[i][j]) {
//                    Region region = new Region();
//                    region.setLandType(grid[i][j].getLandType());
//                    findRegion(covered, i, j, region);
//
//                }
//            }
//        }
//    }
//
//    private void findRegion(boolean[][] covered, int x, int y, Region region) {
//        if (grid[x][y] != null && grid[x][y].getLandType() == region.land) {
//            region.addPosition(new Integer[]{x, y});
//
//            covered[x][y] = true;
//            findRegion(covered, x + 1, y, land, region);
//            findRegion(covered, x - 1, y, land, region);
//            findRegion(covered, x, y + 1, land, region);
//            findRegion(covered, x, y - 1, land, region);
//        } else {
//            covered[x][y] = true;
//        }
//
//    }
}
