package Backend;

public class GridPosition {
    private int x;
    private int y;

    /**
     * Creates a position representing a space on the grid that dominoes will be placed on
     * @param x coordinate of position
     * @param y coordinate of position
     */
    public GridPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return x coordinate of position
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return y coordinate of position
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @return position in string format
     */
    public String toString() {
    	return x + "-" + y;
    }
}
