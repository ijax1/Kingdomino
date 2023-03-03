package UIComponents;
import UIComponents.Render.*;
import UIComponents.Render.Polygon;

import javax.sound.sampled.Line;
import java.awt.*;
import java.util.ArrayList;

import Backend.Domino;

public class UIGrid {
    private final int width;
    private final int height;
    private Coordinate center;
    /*
    9x9 array of tiles, castle tile at the center
     */
    private UITile[][] tiles = new UITile[9][9];

    private int tileSize = 100;

    private UIDomino holding;

    public UIGrid(Coordinate center, Grid g){
        this.width = 0;
        this. height = 0;
        this.center = center;
        tiles[4][4] = new UITile(Color.GRAY, center, 50, center);
        tiles[3][4] = new UITile(Color.BLUE, center.translatedBy(0,-100,0), 50, center);
        tiles[3][5] = new UITile(Color.RED, center.translatedBy(100,-100,0), 50, center);
        tiles[4][5] = new UITile(Color.MAGENTA, center.translatedBy(100,0,0), 50, center);
        tiles[2][3] = new UITile(Color.GREEN, center.translatedBy(-100,-200,0), 50, center);
        tiles[2][4] = new UITile(Color.GREEN, center.translatedBy(0,-200,0), 50, center);
        tiles[1][2] = new UITile(Color.YELLOW, center.translatedBy(-200,-300,0), 50, center);
        //tiles[2][6] = new UITile(Color.YELLOW, center.translatedBy(200,-300,0), 50, center);
        recenter();
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
                height++;
            }
            if(!emptyCol) {
                if(startX == -1)
                    startX = i;
                width++;
            }
        }

        UITile[][] croppedKingdom = new UITile[height][width];
        for(int i = startY; i < startY + height; i++){
            for(int j = startX; j < startX + width; j++){
                croppedKingdom[i-startY][j-startX] = tiles[i][j];
            }
        }
        return croppedKingdom;
    }

    private int numTiles(){
        int count = 0;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(tiles[i][j] != null)
                    count++;
            }
        }
        return count;
    }

    public void render(Graphics g, boolean showGridLines){
        UITile[][] toRender = getCenteredGrid();
        int gridHeight = toRender.length;
        int gridWidth = toRender[0].length;

        Polygon[] polygons = new Polygon[numTiles()];
        int index = 0;
        double centerIndexX = 0.5*(gridWidth);
        double centerIndexY = 0.5*(gridHeight);

        for(int i = 0; i < gridHeight; i++){
            for(int j = 0; j < gridWidth; j++){
                if(toRender[i][j] != null){
                    Polygon temp = toRender[i][j].getPolygon();
                    Polygon p = temp.duplicatePolygon(temp.getCenter());
                    polygons[index] = p;
                    index++;
                }
            }
        }
        Coordinate[] points = {
            center.translatedBy(-tileSize/2.0*gridWidth,-tileSize/2.0*gridHeight,0),
            center.translatedBy(tileSize/2.0*gridWidth,-tileSize/2.0*gridHeight,0),
            center.translatedBy(tileSize/2.0*gridWidth,tileSize/2.0*gridHeight,0),
            center.translatedBy(-tileSize/2.0*gridWidth,tileSize/2.0*gridHeight,0)
        };
        CompoundPolygon c = new CompoundPolygon(polygons,points,center);

        if(showGridLines){
            int widthAllowed = 7-Math.abs(3-gridWidth);
            int heightAllowed = 7-Math.abs(3-gridHeight);

            int startX = (int) center.getX() - tileSize*(widthAllowed)/2;
            int startY = (int) center.getY() - tileSize*(heightAllowed)/2;
            int endX = (int) center.getX() + tileSize*(widthAllowed)/2;
            int endY = (int) center.getY() + tileSize*(heightAllowed)/2;
            for(int x = startX; x <= endX; x+=100) {
                g.drawLine(x, startY, x, endY);
            }

            for(int y = startY; y <= endY; y+=100) {
                g.drawLine(startX, y, endX, y);
            }
        }
        c.render(g);
        if(dominoOnGrid(holding)){

            Coordinate dominoCenter = new Coordinate(0,0,0);
            double rotation = holding.getRotation();
            double leftBound = center.getX() - tileSize * gridWidth * 0.5;
            double topBound = center.getY() - tileSize * gridHeight * 0.5;
            double xMod = 0.0;
            double yMod = 0.0;
            int i = 0;
            for(UITile t: holding.getTiles()){
                Coordinate tileCenter = t.getCenter();
                xMod += (int) Math.round((tileCenter.getX() - leftBound - (50 * (gridWidth%2-1)))/tileSize);
                yMod += (int) Math.round((tileCenter.getY() - topBound - (50 * (gridHeight%2-1)))/tileSize);
                g.drawString((int) Math.round((tileCenter.getX() - leftBound - (50 * (gridWidth%2-1)))/tileSize) + " " + (int) Math.round((tileCenter.getY() - topBound - (50 * (gridHeight%2-1)))/tileSize), 600 + (i * 100),200);
                i++;

            }

            xMod /= 2.0;
            yMod /= 2.0;

            Coordinate dest = new Coordinate(leftBound + xMod * tileSize - 50,topBound + yMod * tileSize - 50,0);
            holding.moveTo(dest);
            UIDomino uid = new UIDomino(dest,null,null,null);
            uid.incrementRotation(0,0,holding.getRotation());
            uid.draw((Graphics2D) g);
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        for(LineSegment ls: c.getLineSegments()){
            g2d.drawLine((int)ls.getStart().getX(),(int)ls.getStart().getY(),(int)ls.getEnd().getX(),(int)ls.getEnd().getY());
        }
    }

    private void recenter(){
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
                height++;
            }
            if(!emptyCol) {
                if(startX == -1)
                    startX = i;
                width++;
            }
        }
        int displacementX = ( (startX+width -4) - (4-startX) ) -1;
        int displacementY = ( (startY+height -4) - (4-startY) ) -1;
        double x = center.getX();
        double y = center.getY();
        Coordinate gridCenter = new Coordinate(x-(displacementX)*tileSize/2.0,y-(displacementY)*tileSize/2.0,0);

        for(UITile[] tList: tiles){
            for(UITile tile: tList)
                if(tile!=null)
                    tile.moveTo(gridCenter);
        }



    }

    public boolean onGrid(Coordinate c){
        int x = (int) c.getX();
        int y = (int) c.getY();
        int widthAllowed = 7-Math.abs(3-getWidth());
        int heightAllowed = 7-Math.abs(3-getHeight());

        int left = (int) center.getX() - tileSize*(widthAllowed)/2;
        int bottom = (int) center.getY() - tileSize*(heightAllowed)/2;
        int right = (int) center.getX() + tileSize*(widthAllowed)/2;
        int top = (int) center.getY() + tileSize*(heightAllowed)/2;
        if(left < x && x < right && bottom < y && y < top)
            return true;
        return false;
    }

    public int getWidth(){
        int width = 0;
        for(int i = 0; i < 9; i++){
            boolean emptyCol = true;
            for(int j = 0; j < 9 && emptyCol; j++){
                if (emptyCol && tiles[j][i] != null){
                    emptyCol = false;
                }
            }
            if(!emptyCol) {
                width++;
            }
        }
        return width;
    }

    public int getHeight(){
        int height = 0;
        for(int i = 0; i < 9; i++) {
            boolean emptyRow = true;
            for (int j = 0; j < 9; j++) {
                if (emptyRow && tiles[i][j] != null) {
                    emptyRow = false;
                }
            }
            if (!emptyRow) {
                height++;
            }
        }
        return height;
    }

    public void holdDomino(UIDomino d){
        this.holding = d;
    }

    public boolean dominoOnGrid(UIDomino d){
        if(d != null) {
            UITile[] tiles = d.getTiles();
            boolean onGrid = true;
            for (UITile t : tiles) {
                if (onGrid && !onGrid(t.getCenter())) {
                    onGrid = false;
                }
            }
            return onGrid;
        } return false;
    }

}
