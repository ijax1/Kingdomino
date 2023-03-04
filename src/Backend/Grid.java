package Backend;

import java.util.ArrayList;

import Backend.Tile.Land;

public class Grid {
    private Tile[][] grid;

    public Grid() {
        grid = new Tile[9][9];
    }

    public Grid(Tile[][] initialGrid) {
        grid = initialGrid;
    }

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

    public Tile getTile(int x, int y) {
        return grid[x][y];
    }

    public Tile getTile(GridPosition pos) {
        return grid[pos.getX()][pos.getY()];
    }

    public ArrayList<GridPosition> availableSpaces(Domino domino) {
        ArrayList<GridPosition> positions = new ArrayList<>();
        int[] relPos = relPos(domino);
        int changeX = relPos[0];
        int changeY = relPos[1];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (isValidPos(i + changeX, j + changeY)
                        && grid[i][j] == null
                        && grid[i + changeX][j + changeY] == null) {
                    positions.add(new GridPosition(i, j));
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

    public boolean placeDomino(int x, int y, Domino domino) {
        int[] relPos = relPos(domino);
        int deltaX = relPos[0];
        int deltaY = relPos[1];
        Tile[] tiles = domino.getTiles();
        if (isValidPos(x, y) && isValidPos(x + deltaX, y + deltaY)) {
            grid[x][y] = tiles[0];
            grid[x + deltaX][y + deltaY] = tiles[1];
            return true;
        } else return false;
    }

    private boolean isValidPos(int x, int y) {
        return 0 <= x && x < 9 && 0 <= y && y < 9;
    }

    public ArrayList<Region> getContiguous() {
        boolean[][] coveredTiles = new boolean[9][9];
        ArrayList<Region> regions = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!coveredTiles[i][j]) {
                    Region region = new Region();
                    region.setLandType(grid[i][j].getLandType());
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
            findRegion(covered, coveredTiles, x + 1, y, region);
            findRegion(covered, coveredTiles, x - 1, y, region);
            findRegion(covered, coveredTiles, x, y + 1, region);
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
        for (Region r : regions) {
            if (r.getLandType() == landType) {
                return regionScore(r);
            }
        }
        return 0;
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
    	for(int i=0; i<grid.length; i++) {
    		for(int j=0; j<grid.length; j++) {
    			if(grid[i][j]==null) {
    				output+="--";
    			} else {
    				output+=grid[i][j].toString();
    			}
    			output+=" ";
    		}
    		output+="\n";
    	}
    	return output;
    }
}
