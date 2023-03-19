package Backend;

import java.util.ArrayList;

import Backend.Tile.Land;
/**
 * A Kingdomino grid, with a maximum of 5x5 tiles in any direction.
 * @author Caleb
 *
 */
public class Grid {
    private Tile[][] grid;
    /**
     * Creates a new Kingdomino grid with the Castle tile in the center.
     */
    public Grid() {
        grid = new Tile[9][9];
        grid[4][4] = new Tile(Land.CASTLE, 0);
    }
    /**
     * Creates a new Kingdomino grid with tiles that are already placed using initialGrid.
     * @param initialGrid a 9x9 array of tiles that are either null (no tile) or one of the many land types.
     *  Note the tiles should not cover more than a 5x5 area; the rest should be null.
     */
    public Grid(Tile[][] initialGrid) {
        grid = initialGrid;
    }
    /**
     * Makes a full, deep copy of this Grid.
     * @return the new Grid.
     */
    public Grid copy() {
        Tile[][] newGrid = new Tile[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                newGrid[i][j] = grid[i][j];
            }
        }
        return new Grid(newGrid);
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
    /**
     * Gets the tile at the specified x and y position.
     * @param x from 0-8
     * @param y from 0-8
     * @return the Tile
     */
    public Tile getTile(int x, int y) {
        return grid[x][y];
    }
    /**
     * Gets the tile at the specified grid position.
     * @param pos The GridPosition
     * @return the Tile
     */
    public Tile getTile(GridPosition pos) {
        return grid[pos.getX()][pos.getY()];
    }
    /** 
     * Gets the available spaces based on the passed in Domino's tiles and rotation.
     * Therefore, this method will return different GridPositions with the same domino but a different rotation.
     * @param domino
     * @return
     */
    public ArrayList<GridPosition> availableSpaces(Domino domino) {
        ArrayList<GridPosition> positions = new ArrayList<>();
        int[] relPos = relPos(domino);
        int changeX = relPos[0];
        int changeY = relPos[1];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (canPlace(domino, changeX, changeY, i, j)) {
                    positions.add(new GridPosition(i,j));
                }
            }
        }
        return positions;
    }

    public boolean[][] availableSpacesGrid(Domino domino) {
        boolean[][] spaces = new boolean[9][9];
        int[] relPos = relPos(domino);
        int changeX = relPos[0];
        int changeY = relPos[1];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                spaces[i][j] = canPlace(domino, changeX, changeY, i, j);
            }
        }
        return spaces;
    }

    private boolean canPlace(Domino domino, int changeX, int changeY, int i, int j) {
        Grid temp = this.copy();
        temp.placeDomino(j, i, domino);
        return isValidPos(i, j)
                && isValidPos(i + changeY, j + changeX)
                && grid[i][j] == null
                && grid[i + changeY][j + changeX] == null
                && (validTilePlacement(domino.getTiles()[0], i, j)
                || validTilePlacement(domino.getTiles()[1], i + changeY, j + changeX))
                && !temp.isOversized();
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
        } else if (rotation == 90) {
            deltaY = 1;
        } else if (rotation == 270) {
            deltaY = -1;
        }
        return new int[]{deltaX, deltaY};
    }

    protected boolean placeDomino(int x, int y, Domino domino) {
        int[] relPos = relPos(domino);
        int deltaX = relPos[0];
        int deltaY = relPos[1];
        Tile[] tiles = domino.getTiles();
        if (isValidPos(x, y) && isValidPos(x + deltaX, y + deltaY)) {
            grid[y][x] = tiles[0];
            grid[y + deltaY][x + deltaX] = tiles[1];
            return true;
        } else return false;
    }

    private boolean isValidPos(int x, int y) {
        return 0 <= x && x < 9 && 0 <= y && y < 9;
    }

    private boolean validTilePlacement(Tile t, int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i * j == 0 && i != j) {

                    if (isValidPos(x + j, y + i))
                        if (grid[x + j][y + i] != null)
                            if (grid[x + j][y + i].getLandType() == t.getLandType() || grid[x + j][y + i].getLandType() == Land.CASTLE)
                                return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Region> getContiguous() {
        boolean[][] coveredTiles = new boolean[9][9];
        ArrayList<Region> regions = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!coveredTiles[i][j] && grid[i][j] != null) {
                    Region region = new Region(grid[i][j].getLandType());
                    findRegion(new boolean[9][9], coveredTiles, i, j, region);
                    regions.add(region);
                }
            }
        }
        return regions;
    }

    private void findRegion(boolean[][] covered, boolean[][] coveredTiles, int x, int y, Region region) {
        if (!covered[x][y] && grid[x][y] != null && grid[x][y].getLandType() == region.getLandType()) {
            region.addPosition(new GridPosition(x, y));

            covered[x][y] = true;
            coveredTiles[x][y] = true;
            if (x + 1 < 9)
                findRegion(covered, coveredTiles, x + 1, y, region);
            if (0 <= x - 1)
                findRegion(covered, coveredTiles, x - 1, y, region);
            if (y + 1 < 9)
                findRegion(covered, coveredTiles, x, y + 1, region);
            if (0 <= y - 1)
                findRegion(covered, coveredTiles, x, y - 1, region);
        } else {
            covered[x][y] = true;
        }
    }

    public int calculateScore() {
        ArrayList<Region> regions = getContiguous();
        int score = 0;
        for (Region region : regions) {
            score += regionScore(region);
        }
        return score;
    }

    public int calculateScore(Land landType) {
        ArrayList<Region> regions = getContiguous();
        int score = 0;
        for (Region r : regions) {
            if (r.getLandType() == landType) {
                score += regionScore(r);
            }
        }
        return score;
    }

    private int regionScore(Region region) {
        int numTiles = region.getPositions().size();
        int numCrowns = 0;
        for (GridPosition position : region.getPositions()) {
            Tile currTile = grid[position.getX()][position.getY()];
            numCrowns += currTile.getCrowns();
        }
        return numTiles * numCrowns;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == null) {
                    output += "--";
                } else {
                    output += grid[i][j].toString();
                }
                output += " ";
            }
            output += "\n";
        }
        return output;
    }

    public Tile[][] getTiles() {
        return grid;
    }

    public boolean isOversized() {
        int leftMost = 8;
        int rightMost = 0;
        int upMost = 8;
        int downMost = 0;
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++){
                if (getTile(i,j) != null) {
                    upMost = Math.min(upMost, i);
                    downMost = Math.max(downMost, i);
                    leftMost = Math.min(leftMost, j);
                    rightMost = Math.max(rightMost, j);
                }
            }
        }
        return rightMost - leftMost > 4 || downMost - upMost > 4;
    }
}
