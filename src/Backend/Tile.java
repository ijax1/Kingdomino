package Backend;

import java.awt.*;
//aman
public class Tile{
	private int crowns;
	private Land landType;
	//private Image image;

	public Tile(Land land){
		landType=land;
		crowns=0;
	}
	public Tile(Land land, int crowns){
		landType=land;
		this.crowns=crowns;
		//image =
	}
	public int getCrowns() {
		return crowns;
	}
	public Land getLandType() {
		return landType;
	}

	public Color getColor(){
		return landType.getColor();
	}

	//public Image getImage() { return image; }
	public enum Land{
		FOREST{
			public Color getColor(){
				return new Color(40,80,40);
			}
			public String toString() {
				return "F";
			}
		},
		LAKE{
			public Color getColor(){
				return new Color(20,80,180);
			}
			public String toString() {
				return "L";
			}
		},
		WHEAT{
			public Color getColor(){
				return new Color(215, 182, 19);
			}
			public String toString() {
				return "W";
			}
		},
		SWAMP{
			public Color getColor(){
				return new Color(146, 151, 108);
			}
			public String toString() {
				return "S";
			}
		},
		PASTURE{
			public Color getColor(){
				return new Color(40,180,40);
			}
			public String toString() {
				return "P";
			}
		},
		MINE{
			public Color getColor(){
				return new Color(40,40,40);
			}
			public String toString() {
				return "M";
			}
		},
		CASTLE{
			public Color getColor(){
				return Color.GRAY;
			}
			public String toString() {
				return "C";
			}
		};
		public abstract Color getColor();
	}
	@Override
	public String toString() {
		if(crowns==0) {
			return landType.toString() + "-";
		} else {
			return landType.toString() + crowns;
		}
	}

}