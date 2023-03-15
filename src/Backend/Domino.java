package Backend;

public class Domino implements Comparable<Domino>{
    private final Tile[] tiles = new Tile[2];
    private final int dominoValue;
    private int rotation;
    private boolean draggable;

    public Domino(Tile first, Tile second, int value){
        this.tiles[0] = first;
        this.tiles[1] = second;
        this.dominoValue = value;
    }

    public int getValue(){
        return this.dominoValue;
    }

    public boolean getDraggable(){
        return draggable;
    }

    public Tile[] getTiles(){
        return tiles;
    }

    public void setDraggable(boolean draggable){
        this.draggable = draggable;
    }

    public void setRotation(int rotation){
        this.rotation = rotation;
    }

    public void incrementRotation(){
        this.rotation += 90;
        this.rotation %= 360;
    }

    @Override
    public int compareTo(Domino d) {
        return this.getValue() - d.getValue();
    }
    
    public String toString() {
    	return "(" + tiles[0] + "|" + tiles[1] + ")";
    }

    public int getRotation(){
        return rotation;
    }
}
