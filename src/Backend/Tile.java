package Backend;

import java.awt.*;
//aman
public class Tile{
	private int crowns;
	private Land landType;
	//private Image image;

	/**
	 * creates a tile without crowns
	 * @param land
	 */
	public Tile(Land land){
		landType=land;
		crowns=0;
	}

	/**
	 * creates a tile with crowns
	 * @param land the land type of the tile
	 * @param crowns the number of crowns the tile is worth
	 */
	public Tile(Land land, int crowns){
		landType=land;
		this.crowns=crowns;
		//image =
	}

	/**
	 *
	 * @return the number of crowns the tile is worth
	 */
	public int getCrowns() {
		return crowns;
	}

	/**
	 *
	 * @return the land type of the tile
	 */
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
			public String toFileName() {
				return "forest";
			}
		},
		LAKE{
			public Color getColor(){
				return new Color(20,80,180);
			}
			public String toString() {
				return "L";
			}
			public String toFileName() {
				return "lake";
			}
		},
		WHEAT{
			public Color getColor(){
				return new Color(215, 182, 19);
			}
			public String toString() {
				return "W";
			}
			public String toFileName() {
				return "wheat";
			}
		},
		SWAMP{
			public Color getColor(){
				return new Color(146, 151, 108);
			}
			public String toString() {
				return "S";
			}
			public String toFileName() {
				return "swamp";
			}
		},
		PASTURE{
			public Color getColor(){
				return new Color(40,180,40);
			}
			public String toString() {
				return "P";
			}
			public String toFileName() {
				return "pasture";
			}
		},
		MINE{
			public Color getColor(){
				return new Color(40,40,40);
			}
			public String toString() {
				return "M";
			}
			public String toFileName() {
				return "mine";
			}
		},
		CASTLE{
			public Color getColor(){
				return Color.GRAY;
			}
			public String toString() {
				return "C";
			}
			public String toFileName() {
				return "castle";
			}
		};
		public abstract Color getColor();
		public abstract String toFileName();

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