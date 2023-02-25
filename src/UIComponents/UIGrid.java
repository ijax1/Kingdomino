package UIComponents;
import UIComponents.Render.*;

import java.awt.*;
import java.util.ArrayList;

public class UIGrid {
    private final int width;
    private final int height;
    private final Coordinate center;
    private ArrayList<UITile> tiles = new ArrayList<UITile>();

    public UIGrid(Coordinate center, Grid g){
        this.width = 0;
        this. height = 0;
        this.center = center;
    }

    public void addDominoToGrid(Coordinate placed){

    }

    public void render(Graphics g){
      Graphics2D g = (Graphics2D)g1;
      for(int i=0; i<=size; i+=100) {
        g.drawLine(i, 0, i, size);
      }
      for(int i=0; i<=size; i+=100) {
        g.drawLine(0, i, size, i);
      }
    }
}
