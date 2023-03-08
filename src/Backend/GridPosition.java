package Backend;

public class GridPosition {
    private int x;
    private int y;
    public GridPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public String toString() {
    	return x + "-" + y;
    }
}
