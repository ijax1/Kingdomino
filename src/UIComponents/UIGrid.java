package UIComponents;
import UIComponents.Render.*;
import UIComponents.Render.Polygon;

import javax.sound.sampled.Line;
import java.awt.*;
import java.util.ArrayList;
import Backend.*;

import Backend.Domino;
import Backend.Grid;

public class UIGrid extends Component{
    private final int width;
    private final int height;
    private Coordinate center;
    /*
    9x9 array of tiles, castle tile at the center
     */
    private UITile[][] tiles = new UITile[9][9];

    private int tileSize = UITile.TILE_SIZE;

    private Grid grid;

    private Coordinate temp = new Coordinate(0,0,0);

    private UIDomino holding;
    private UIDomino display;
    private Domino ref;

    private boolean showGridLines = false;

    private boolean snapping = false;

    private int[] dominoLocation = {-1,-1};

    public UIGrid(Coordinate center, Kingdomino k, Grid g){
        super(center, k);
        this.width = 0;
        this. height = 0;
        this.center = center;
        this.grid = g;


        //g.placeDomino(2,2, new Domino(new Tile(Tile.Land.LAKE,0), new Tile(Tile.Land.MINE,0),4));
        //g.placeDomino(2,2, new Domino(new Tile(Tile.Land.WHEAT,0), new Tile(Tile.Land.PASTURE,0),4));
        //g.placeDomino(1,1, new Domino(new Tile(Tile.Land.FOREST,0), new Tile(Tile.Land.FOREST,0),4));
        g.placeDomino(3,3, new Domino(new Tile(Tile.Land.LAKE,0), new Tile(Tile.Land.SWAMP,0),4));
        Tile[][] tileList = g.getTiles();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(tileList[i][j] != null){
                    tiles[i][j] = new UITile(tileList[i][j].getColor(), center.translatedBy((j-4)*tileSize, (i-4)*tileSize,0), tileSize/2, center);
                }
            }
        }

        recenter();
    }

    public UIGrid(Coordinate center, Player p){
        super(center, null);
        this.width = 0;
        this. height = 0;
        this.center = center;
        this.grid = p.getGrid();

        Tile[][] tileList = grid.getTiles();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(tileList[i][j] != null){
                    tiles[i][j] = new UITile(tileList[i][j].getColor(), center.translatedBy((j-4)*tileSize, (i-4)*tileSize,0), tileSize/2, center);
                }
            }
        }

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
        System.out.println("WDITH " + width + " HEIGHT " + height);
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

        if(showGridLines){
            drawGridLines(g);
        }
        drawGrid(g);
        if(dominoOnGrid(holding)){
            drawDomino(g);
        }

    }

    private void drawGridLines(Graphics g){
        UITile[][] toRender = getCenteredGrid();
        int gridHeight = toRender.length;
        int gridWidth = toRender[0].length;

        g.setColor(Color.BLACK);
        int widthAllowed = 7-Math.abs(3-gridWidth);
        int heightAllowed = 7-Math.abs(3-gridHeight);

        int startX = (int) center.getX() - tileSize*(widthAllowed)/2;
        int startY = (int) center.getY() - tileSize*(heightAllowed)/2;
        int endX = (int) center.getX() + tileSize*(widthAllowed)/2;
        int endY = (int) center.getY() + tileSize*(heightAllowed)/2;
        for(int x = startX; x <= endX; x+=tileSize) {
            g.drawLine(x, startY, x, endY);
        }

        for(int y = startY; y <= endY; y+=tileSize) {
            g.drawLine(startX, y, endX, y);
        }
    }

    private void drawGrid(Graphics g){
        UITile[][] toRender = getCenteredGrid();
        int gridHeight = toRender.length;
        int gridWidth = toRender[0].length;

        Polygon[] polygons = new Polygon[numTiles()];
        int index = 0;
        double centerIndexX = 0.5*(gridWidth);
        double centerIndexY = 0.5*(gridHeight);
//cock
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

        c.render(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        for(LineSegment ls: c.getLineSegments()){
            g2d.drawLine((int)ls.getStart().getX(),(int)ls.getStart().getY(),(int)ls.getEnd().getX(),(int)ls.getEnd().getY());
        }

    }
    private void drawDomino(Graphics g){
        UITile[][] toRender = getCenteredGrid();
        int gridHeight = toRender.length;
        int gridWidth = toRender[0].length;
        gridWidth = 7-Math.abs(3-gridWidth);
        gridHeight = 7-Math.abs(3-gridHeight);

        int leftBound = (int) (center.getX() - tileSize * gridWidth * 0.5);
        int topBound = (int) (center.getY() - tileSize * gridHeight * 0.5);

        double xMod = 0.0;
        double yMod = 0.0;
        int i = 0;
        int tileZeroX = 0;
        int tileZeroY = 0;
        for(UITile t: holding.getTiles()){
            Coordinate tileCenter = t.getCenter();
            if(gridWidth % 2 == 0)
                xMod += (int) Math.round((tileCenter.getX() - leftBound - (tileSize/2 * (gridWidth%2-1)))/tileSize);
            else
                xMod += (int) Math.round((Math.round(tileCenter.getX()) - leftBound - (tileSize/2 * (gridWidth%2-1)))/tileSize);
            if(gridHeight % 2 == 0)
                yMod += (int) Math.round((tileCenter.getY() - topBound - (tileSize/2 * (gridHeight%2-1)))/tileSize);
            else
                yMod += (int) Math.round((Math.round(tileCenter.getY()) - topBound - (tileSize/2 * (gridHeight%2-1)))/tileSize);

            if(i == 0){
                tileZeroX = (int) (leftBound + xMod * tileSize - tileSize/2 + (tileSize*(gridWidth%2)));
                tileZeroY = (int) (topBound + yMod * tileSize - tileSize/2 + (tileSize*(gridHeight%2)));
            }

            i++;

        }

        xMod /= 2.0;
        yMod /= 2.0;

        int xIndex = (int) Math.floor(xMod);
        int yIndex = (int) Math.floor(yMod);
        //g.drawString(xIndex + " " + yIndex,600 + (i * 100),200);
        Coordinate dest = new Coordinate(
                leftBound + xMod * tileSize - tileSize/2 + (tileSize*(gridWidth%2)),
                topBound + yMod * tileSize - tileSize/2 + (tileSize*(gridHeight%2)),
                0);

        if(holding.isRotating()) {
            dest = holding.getCenter();
        }

        Coordinate tileCenter = holding.getTiles()[0].getCenter();
        if(gridWidth % 2 == 0)
            xMod = (int) Math.round((tileCenter.getX() - leftBound - (tileSize/2 * (gridWidth%2-1)))/tileSize);
        else
            xMod = Math.round((Math.round(tileCenter.getX()) - leftBound - (tileSize/2 * (gridWidth%2-1)))/tileSize);
        if(gridHeight % 2 == 0)
            yMod = (int) Math.round((tileCenter.getY() - topBound - (tileSize/2 * (gridHeight%2-1)))/tileSize);
        else
            yMod = Math.round((Math.round(tileCenter.getY()) - topBound - (tileSize/2 * (gridHeight%2-1)))/tileSize);

        int checkIndexX = getStartX() + (int) Math.round(xMod) - 1;
        int checkIndexY = getStartY() + (int) Math.round(yMod) - 1;
        if(gridWidth % 2 == 0)
            checkIndexX = getStartX() + (int) Math.round(xMod);
        if(gridHeight % 2 == 0)
            checkIndexY = getStartY() + (int) Math.round(yMod);

        g.drawString((tileCenter.getX() - leftBound - (tileSize/2 * (gridWidth%2-1)))/tileSize + " " + ((tileCenter.getY() - topBound - (tileSize/2 * (gridHeight%2-1)))/tileSize), 200,160);
        g.drawString(getStartX() + " " + getStartY(), 200,180);
        g.drawString((checkIndexX - 1) + " " + (checkIndexY - 1), 200,200);
        if(grid.availableSpacesGrid(ref)[checkIndexY-1][checkIndexX-1]){
            snapping = true;
            holding.moveTo(dest);
            holding.render(g);
            dominoLocation = new int[]{checkIndexY-1,checkIndexX-1};
        }
        else{
            snapping = false;
            holding.render(g);
            dominoLocation = new int[]{-1,-1};
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

    public void holdDomino(UIDomino d, Domino ref){
        this.holding = d;
        this.ref = ref;
        this.holding.setRef(ref);
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

    private int getStartX(){
        for(int i = 0; i < 9; i++){
            boolean emptyCol = true;
            for(int j = 0; j < 9; j++){
                if(emptyCol && tiles[j][i] != null){
                    emptyCol = false;
                }
            }
            if(!emptyCol) {
                return i;
            }
        }
        return -1;
    }

    private int getStartY(){
        for(int i = 0; i < 9; i++){
            boolean emptyRow = true;
            for(int j = 0; j < 9; j++){
                if(emptyRow && tiles[i][j] != null){
                    emptyRow = false;
                }
            }
            if(!emptyRow) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void setPosition(Coordinate coordinate) {
        this.center = coordinate;
    }

    @Override
    public boolean onComponent(Coordinate c) {
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

    public void showGridLines(){
        showGridLines = true;
    }

    public void hideGridLines(){
        showGridLines = true;
    }

    @Override
    public void draw(Graphics2D g) {
        render(g, showGridLines);
    }

    @Override
    public void whenClicked() {

    }
    public void setSnapped(boolean snapped){
        snapping = snapped;
    }

    public boolean isSnapped() {
        return snapping;
    }

    public int[] getDominoLocation(){
        return dominoLocation;
    }
}
