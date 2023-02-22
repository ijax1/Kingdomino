import java.util.*;

public class Tile{
	Land[] landTypes= {Land.FORREST,Land.LAKE,Land.WHEAT,Land.SWAMP,Land.PASTURE,Land.MINE,Land.CASTLE};
	int crowns;
	Land landType;
	Tile(Land land){
		landType=land;
		crowns=0;
	}
	Tile(Land land, int crowns){
		landType=land;
		this.crowns=crowns;
	}
	int getCrowns() {
		return crowns;
	}
	Land getLandType() {
		return landType;
	}
	enum Land{
		FORREST,
		LAKE,
		WHEAT,
		SWAMP,
		PASTURE,
		MINE,
		CASTLE
	}
	
}