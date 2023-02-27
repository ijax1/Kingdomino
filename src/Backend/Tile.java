package Backend;

import java.awt.*;
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
		FOREST{
			public Color getColor(){
				return new Color(40,80,40);
			}
		},
		LAKE{
			public Color getColor(){
				return new Color(40,80,40);
			}
		},
		WHEAT{
			public Color getColor(){
				return new Color(215, 182, 19);
			}
		},
		SWAMP{
			public Color getColor(){
				return new Color(146, 151, 108);
			}
		},
		PASTURE{
			public Color getColor(){
				return new Color(40,180,40);
			}
		},
		MINE{
			public Color getColor(){
				return new Color(40,40,40);
			}
		},
		CASTLE{
			public Color getColor(){
				return Color.GRAY;
			}
		};
		abstract Color getColor();
	}

}