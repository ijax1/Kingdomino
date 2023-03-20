package Backend;

public class Domino implements Comparable<Domino>{
    private final Tile[] tiles = new Tile[2];
    private final int dominoValue;
    private int rotation;
    private boolean draggable;
    /**
     * Constructs a new Domino 
     * @param first
     * @param second
     * @param value
     */
    public Domino(Tile first, Tile second, int value){
        this.tiles[0] = first;
        this.tiles[1] = second;
        this.dominoValue = value;
    }
    /**
     * gets the domino value (the number on the back of the domino). A higher number means a more v
     * @return value the domino value
     */
    public int getValue(){
        return this.dominoValue;
    }
    /**
     * Gets the tiles of the Domino
     * @return a 2x1 array of Tiles. The first Tile 
     */
    public Tile[] getTiles(){
        return tiles;
    }
    /**
     * Sets rotation of domino as value.
     * @param rotation an int that is either 0, 90, 180, or 270 degrees. 
     */
    public void setRotation(int rotation){
        this.rotation = rotation;
    }
    /**
     * Increments rotation of domino clockwise 90 degrees
     */
    public void incrementRotation(){
        this.rotation += 90;
        this.rotation %= 360;
    }
    
    @Override
    public int compareTo(Domino d) {
        return this.getValue() - d.getValue();
    }
    @Override
    public String toString() {
    	return "(" + tiles[0] + "|" + tiles[1] + ")";
    }
    /**
     * Gets rotation of domino as an int, in degrees.
     * @return the domino rotation (0, 90, 180, 270)
     */
    public int getRotation(){
        return rotation;
    }
    /**
     * Makes a full (deep) copy of this domino
     * @return a completely new Domino
     */
    public Domino copy() {
        return new Domino(tiles[0], tiles[1], dominoValue);
    }
}
