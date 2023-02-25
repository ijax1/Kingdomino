package Backend;

import java.util.*;
//aman
public class Tile{
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
		FOREST,
		LAKE,
		WHEAT,
		SWAMP,
		PASTURE,
		MINE,
		CASTLE
	}

}