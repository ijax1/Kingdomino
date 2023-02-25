package UIComponents;
import UIComponents.Render.*;

import java.awt.*;
import java.util.ArrayList;

public class UIGrid {
    private final int width;
    private final int height;
    private final Coordinate center;
    /*
    9x9 array of tiles, castle tile at the center
     */
    private UITile[][] tiles = new UITile[9][9];

    private int tileSize = 100;

    public UIGrid(Coordinate center, Grid g){
        this.width = 0;
        this. height = 0;
        this.center = center;
        tiles[4][4] = new UITile(Color.GRAY, center, 50, center);
    }

    public void addDominoToGrid(Domino d, Coordinate placed){

    }

    public UITile[][] getCenteredGrid(){
        int startX = -1;
        int startY = -1;
        int width = 0;
        int height = 0;
        for(int i = 0; i < 9; i++){
            boolean emptyRow = true;
            boolean emptyCol = true;
            for(int j = 0; j < 9; j++){
                if(emptyRow && tiles[i][j] != null){
                    emptyRow = false;
                }
                if(emptyCol && tiles[j][i] != null){
                    emptyCol = false;
                }
            }
            if(!emptyRow) {
                if(startY == -1)
                    startY = i;
                width++;
            }
            if(!emptyCol) {
                if(startX == -1)
                    startX = i;
                height++;
            }
        }
        UITile[][] croppedKingdom = new UITile[width][height];
        for(int i = startX; i < startX + width; i++){
            for(int j = startY; j < startY + height; j++){
                croppedKingdom[i-startX][j-startY] = tiles[startX][startY];
            }
        }
        return croppedKingdom;
    }

    public void render(Graphics g){
        UITile[][] toRender = getCenteredGrid();
        for(UITile[] tList: toRender)
            for(UITile tile: tList)
                if(tile != null)
                    tile.render(g);
    }

}
