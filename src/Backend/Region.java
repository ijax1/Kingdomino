package Backend;

import Backend.Tile.Land;

import java.util.ArrayList;

public class Region {
    private ArrayList<GridPosition> positions;
    private Land landType;

    public ArrayList<GridPosition> getPositions() {
        return positions;
    }

    public void addPosition(GridPosition position) {
        positions.add(position);
    }

    public void setLandType(Land land) {
        landType = land;
    }

    public Land getLandType() {
        return landType;
    }

}
