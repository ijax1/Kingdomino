package Backend;

import Backend.Tile.Land;

import java.util.ArrayList;

public class Region {
    private ArrayList<Integer[]> positions;
    private Land landType;

    public ArrayList<Integer[]> getPositions() {
        return positions;
    }

    public void addPosition(Integer[] position) {
        positions.add(position);
    }

    public void setLandType(Land land) {
        landType = land;
    }

    public Land getLandType() {
        return landType;
    }

}
