package UIComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import Backend.Domino;
import Backend.Kingdomino;
import Backend.Tile;
import UIComponents.Render.CompoundPolygon;
import UIComponents.Render.Coordinate;
import UIComponents.Render.LineSegment;
import UIComponents.Render.Polygon;
import UIComponents.Render.RectangularPrism;
import UIComponents.Render.TexturedPolygon;

public class UIDomino extends Component{
    private final UITile[] tiles = new UITile[2];
    private RectangularPrism self;

    double rotateTo = 0;
    double rotIncrement = Math.PI/8;
    public double currentRotation = 0;

    boolean rotating = false;

    double sideLen = UITile.TILE_SIZE;

    Coordinate mouseLocation = new Coordinate(0,0,0);

    Domino ref;

    Kingdomino k;
    double xRotation = 0, yRotation = 0, zRotation = 0;

    public UIDomino(Coordinate position, Kingdomino k, Color color1, Color color2) {
        super(position, k);
        this.k = k;
        tiles[0] = new UITile(Color.RED, new Coordinate(position.getX()-sideLen/2.0, position.getY(),position.getZ()-sideLen/20), (int) sideLen/2, position);
        tiles[1] = new UITile(Color.CYAN, new Coordinate(position.getX()+sideLen/2.0, position.getY(),position.getZ()-sideLen/20), (int) sideLen/2, position);

        createPrism(0,0,0);
        super.show();
    }

    public UIDomino(Coordinate position, Kingdomino k, Domino d) {
        super(position, k);
        this.k = k;
        Tile[] dominoTiles = d.getTiles();
        tiles[0] = new UITile(dominoTiles[0], new Coordinate(position.getX()-sideLen/2.0, position.getY(),position.getZ()-sideLen/20), (int) sideLen/2, position);
        tiles[1] = new UITile(dominoTiles[1], new Coordinate(position.getX()+sideLen/2.0, position.getY(),position.getZ()-sideLen/20), (int) sideLen/2, position);

        createPrism(0,0,0);
        super.show();
        this.ref = d;
    }

    private void createPrism(double xRotation, double yRotation, double zRotation){
        Coordinate position = super.getPosition();

        Coordinate[] points = {
                new Coordinate(position.getX()-sideLen, position.getY()-sideLen/2.0,position.getZ()-sideLen/20),
                new Coordinate(position.getX()+sideLen, position.getY()-sideLen/2.0,position.getZ()-sideLen/20),
                new Coordinate(position.getX()+sideLen, position.getY()+sideLen/2.0,position.getZ()-sideLen/20),
                new Coordinate(position.getX()-sideLen, position.getY()+sideLen/2.0,position.getZ()-sideLen/20)
        };
        self = new RectangularPrism(position,sideLen*2,sideLen/10,sideLen);
        Color sideColor = new Color(123, 63, 0);
        Color backColor = new Color(123, 63, 0);
        self.getFace(0).setColor(sideColor);
        self.getFace(1).setColor(sideColor);
        self.getFace(3).setColor(sideColor);
        self.getFace(4).setColor(sideColor);
        self.getFace(5).setColor(backColor);
        CompoundPolygon c = new CompoundPolygon(new Polygon[]{tiles[0].getPolygon().duplicatePolygon(getCenter()),tiles[1].getPolygon().duplicatePolygon(getCenter())},points,position);
        self.setFace(2,new CompoundPolygon(new Polygon[]{tiles[0].getPolygon().duplicatePolygon(getCenter()),tiles[1].getPolygon().duplicatePolygon(getCenter())},points,position));
        try {
            self.setFace(5,new TexturedPolygon(self.getFace(5).getPoints(), self.getFace(5).getCenter(), ImageIO.read(new File("C:\\Users\\jonat\\Downloads\\18.jpg"))));
        } catch(Exception e){;}
        super.show();

    }
    @Override
    public void setPosition(Coordinate coordinate) {
        for(UITile t: tiles)
            t.moveTo(coordinate);
        self.moveTo(coordinate);
    }

    @Override
    public boolean onComponent(Coordinate c) {
        return self.intersects(c);
    }

    @Override
    public void draw(Graphics2D g) {
        if(super.isShown())
            self.render(g);
        if(almostEqual(Math.abs(currentRotation%(2*Math.PI) - rotateTo%(2*Math.PI)), 0, 1e-4) ||
                almostEqual(Math.abs(currentRotation%(2*Math.PI) - rotateTo%(2*Math.PI)), 2*Math.PI, 1e-4))
            rotating = false;
    }

    public void render(Graphics g){
        UIDomino uid = new UIDomino(this.getCenter(), k, ref);
        uid.incrementRotation(xRotation, yRotation, zRotation);
        uid.draw((Graphics2D) g);
    }
    @Override
    public void whenClicked() {

    }

    public void incrementRotation(double xRotation, double yRotation, double zRotation){
        self.incrementRotation(xRotation, yRotation, zRotation);
        for(UITile t: tiles){
            t.incrementRotation(xRotation, yRotation, zRotation);
        }
        this.xRotation += xRotation;
        this.yRotation += yRotation;
        this.zRotation += zRotation;

    }

    public void rotateToNextPos(final int direction, final JPanel panel) {
        rotateTo += (0.5*Math.PI) * direction;
        if (almostEqual(currentRotation%(2*Math.PI), (rotateTo-(0.5*Math.PI))%(2*Math.PI),1e-4)) {
            rotating = true;
            final UIDomino domino = this;
            final Timer timer = new Timer(1, null);
            currentRotation = rotateTo - (0.5*Math.PI) * direction;
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentRotation += rotIncrement * direction;
                    incrementRotation(0, 0, direction * rotIncrement);
                    panel.repaint();
                    if (almostEqual(Math.abs(currentRotation%(2*Math.PI) - rotateTo%(2*Math.PI)), 0, 1e-4) ||
                            almostEqual(Math.abs(currentRotation%(2*Math.PI) - rotateTo%(2*Math.PI)), 2*Math.PI, 1e-4)) {
                        rotateTo %= (2*Math.PI);
                        currentRotation = rotateTo;
                        panel.repaint();
                        timer.stop();
                        domino.moveTo(domino.getMouseLocation());
                        ref.incrementRotation();
                        rotating = false;
                        zRotation = currentRotation;
                    }

                }
            });
            timer.start();
        }
    }
    public void animateDomino(Coordinate destination){
        final LineSegment ls = new LineSegment(getCenter(),destination);
        final Timer timer = new Timer(1, null);
        timer.addActionListener(new ActionListener() {
            Coordinate temp = ls.getStart();
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = ls.getNextPoint(temp, 0.01);
                moveTo(temp);
                if (!ls.onLine(temp)) {
                    timer.stop();
                }

            }
        });
        timer.start();
    }
    public Coordinate getCenter() {
        return self.getCenter();
    }

    public void moveTo(Coordinate c) {
        for(UITile t: tiles)
            t.moveTo(c);
        self.moveTo(c);
    }
    public boolean isRotating(){
        return rotating;
    }

    public UITile[] getTiles(){
        return tiles;
    }

    public double getRotation(){
        return currentRotation;
    }

    private static boolean almostEqual(double a, double b, double eps){
        return Math.abs(a-b)<eps;
    }

    public void setMouseLocation(Coordinate c){
        this.mouseLocation = c;
    }

    public Coordinate getMouseLocation(){
        return mouseLocation;
    }

    public void setRef(Domino ref){
        this.ref = ref;
    }
    public Domino getRef() {
    	return ref;
    }
}
