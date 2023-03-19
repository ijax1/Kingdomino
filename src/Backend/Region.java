package Backend;

import Backend.Tile.Land;

import java.util.ArrayList;

public class Region {
    private ArrayList<GridPosition> positions = new ArrayList<GridPosition>();
    private Land landType;
    
	public Region(Land land) {
		landType = land;
	}

    public ArrayList<GridPosition> getPositions() {
        return positions;
    }

    public void addPosition(GridPosition position) {
        positions.add(position);
    }

    public Land getLandType() {
        return landType;
    }

}
