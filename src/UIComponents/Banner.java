package UIComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import Backend.Domino;
import Backend.GameManager;
import Backend.GameManager.GameState;
import Backend.Grid;
import Backend.Kingdomino;
import Backend.Tile;
import Backend.Tile.Land;
import UIComponents.Render.Coordinate;
import resources.OurColors;
import resources.Resources;

public class Banner extends Component {
	private boolean minimized;
	private Kingdomino k;
	private GameManager gm;
	private GamePanel gamePanel;
	private final ArrayList<DominoButton> buttons = new ArrayList<DominoButton>();
	Banner(Coordinate position, Kingdomino k, int numButtons, GamePanel gp) {
		super(position, k);
		this.k = k;
		this.gm = k.getManager();
		this.gamePanel = gp;
		double x = position.getX();
		double y = position.getY();
		
		Domino tempDomino = new Domino(new Tile(Land.FOREST,0), new Tile(Land.LAKE, 1), 30);
		int xOffset = 255;
		int yOffset = 90;
		for(int i=0; i<numButtons; i++) {
			buttons.add(new DominoButton(new Coordinate(x+xOffset,y+yOffset,0), k, tempDomino));
			yOffset += UITile.TILE_SIZE * 1.6;
		}
	}
	public final ArrayList<DominoButton> getButtons() {
		return buttons;
	}
	public void setDominoes(ArrayList<Domino>sorted) {
		for(int i=0; i<sorted.size(); i++) {
			buttons.get(i).setDomino(sorted.get(i));
		}
	}

	@Override
	public void setPosition(Coordinate coordinate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onComponent(Coordinate c) {
		// TODO Auto-generated method stub
		return false;
	}
	private void showDominoes() {
		for(DominoButton b: buttons){
			b.show();
		}
	}
	//shh this code is good
	private int[] scores = new int[7];
	private int[] displayedScores = new int[7];
	private int scoreIdx=0;
	private void showTally(Graphics2D g, int bannerStartX, int bannerStartY) {
		g.setFont(Resources.getMedievalFont(24));
		g.setColor(Color.BLACK);
		for(DominoButton b: buttons){
			b.minimize();
		}
		
		Grid grid = gamePanel.getViewedPlayer().getGrid();
		int total = grid.calculateScore();
		Land[] landOrder = {Land.FOREST, Land.WHEAT, Land.PASTURE, Land.LAKE, Land.SWAMP, Land.MINE};
		int xPos = bannerStartX+20;
		int yPos = bannerStartY;
		for(int i=0; i<landOrder.length; i++) {
			UITile t = new UITile(landOrder[i], new Coordinate(xPos, yPos, 0));
			int score = grid.calculateScore(landOrder[i]);
			t.render(g.create());
			scores[i] = score;
			yPos += 50;
		}
		xPos = bannerStartX+20;
		yPos = bannerStartY;
		scoreIdx= 0;
		Timer t = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(scoreIdx);
				if(scoreIdx > 4) {
					((Timer)e.getSource()).stop();
				} else {
					if(scoreIdx != 0) {
						System.arraycopy(scores, 0, displayedScores, scoreIdx, scoreIdx);
					}
					scoreIdx++;
				}
			}
		});
		t.start();
		
		
		for(int i=0; i<displayedScores.length; i++) {
			g.drawString(""+displayedScores[i], xPos, yPos);
			yPos += 50;
		}

		g.drawString("Total:", xPos-30, yPos);
		g.drawString(""+total, xPos+40, yPos);

		
		
	}

	@Override
	public void draw(Graphics2D g) {
		Coordinate pos = super.getPosition();
		g.translate(pos.getX(), pos.getY());
		int widthAllowed = Kingdomino.FRAME_WIDTH - (int) pos.getX();
		int heightAllowed = Kingdomino.FRAME_HEIGHT - 50;
		int bannerWidth = 200;
		int bannerStartX = 155;
		int bannerEndX = bannerStartX + bannerWidth;
		int bannerStartY = 20;
		int bannerEndY = bannerStartY + 500;
		g.setStroke(new BasicStroke(3));

		//Draws purple background
		int[] xPoints = {bannerStartX,bannerStartX,bannerStartX+bannerWidth/2,bannerEndX,bannerEndX};
		int[] yPoints = {bannerStartY,bannerEndY,bannerEndY-50,bannerEndY,bannerStartY};
		g.setColor(Color.BLACK);
		g.drawPolygon(xPoints,yPoints, 5);
		g.setColor(OurColors.BUTTON_COLOR);
		g.fillPolygon(xPoints,yPoints, 5);

		int borderSize = 15;
		int sizeMod = (int) Math.sqrt(Math.pow(borderSize,2) + Math.pow(borderSize/2,2));
		//Draws white portion
		int[] xPoints1 = {bannerStartX+borderSize,bannerStartX+borderSize,bannerStartX+bannerWidth/2,bannerEndX-borderSize,bannerEndX-borderSize};
		int[] yPoints1 = {bannerStartY,bannerEndY-borderSize/2 - sizeMod,bannerEndY-50-sizeMod,bannerEndY-borderSize/2 - sizeMod,bannerStartY};
		g.setColor(Color.BLACK);
		//g.drawPolygon(xPoints1, yPoints1, 5);
		g.setColor(new Color(215,215,220));
		g.fillPolygon(xPoints1, yPoints1, 5);

		//draws banner pole
		g.setColor(Color.BLACK);
		g.drawRect(100,10,400,20);
		g.setColor(new Color(0x432616));
		g.fillRect(100,10,400,20);
		g.setColor(new Color(0x74462F));
		g.fillRect(100,10,400,2);
		g.setColor(new Color(0x5D3724));
		g.fillRect(100,12,400,4);
		g.setColor(new Color(0x3E2114));
		g.fillRect(100,21,400,4);
		g.setColor(new Color(0x311A0F));
		g.fillRect(100,25,400,5);

		//draws pole stopper
		int circleX = 90;
		int circleY = 0;
		g.setColor(Color.BLACK);
		g.drawOval(90,0,40,40);
		g.setColor(new Color(0xC59B1B));
		int radius = 40;
		g.fillOval(90,0,40,40);
		g.setColor(new Color(0xB08B13));
		int rad = 36;
		int mod = (int) ((Math.sqrt(2)/2)*(radius/2.0 - rad/2.0));
		g.fillOval(circleX+radius/2+mod-rad/2,circleY+radius/2-mod-rad/2,rad,rad);
		g.setColor(new Color(0xD1A723));
		rad = 34;
		mod = (int) ((Math.sqrt(2)/2)*(radius/2.0 - rad/2.0));
		g.fillOval(circleX+radius/2+mod-rad/2,circleY+radius/2-mod-rad/2,rad,rad);
		g.setColor(OurColors.ACCENT_COLOR);
		rad = 26;
		mod = (int) ((Math.sqrt(2)/2)*(radius/2.0 - rad/2.0));
		g.fillOval(circleX+radius/2+mod-rad/2,circleY+radius/2-mod-rad/2,rad,rad);
		g.setColor(new Color(0xF6D66A));
		rad = 20;
		mod = (int) ((Math.sqrt(2)/2)*(radius/2.0 - rad/2.0));
		g.fillOval(circleX+radius/2+mod-rad/2,circleY+radius/2-mod-rad/2,rad,rad);
		if(getManager().getGameState() == GameState.PLAYER_TURN) {
			showDominoes();
		} else if(getManager().getGameState() == GameState.TALLY_SCORE) {
			showTally((Graphics2D) g.create(), bannerStartX+60, bannerStartY+80);
		} else {
			//don't draw? this should never happen though
		}
	}
	
	@Override
    public void minimize(){
        minimized = false;
        ActionListener task = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
        };
        Timer timer = new Timer(10, task);
	}
	@Override
    public void show(){
        minimized = true;
    }
	@Override
    public boolean isMinimized(){
        return minimized;
    }

	@Override
	public void whenClicked() {
		// TODO Auto-generated method stub
		
	}
}
