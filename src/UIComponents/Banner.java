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
import Backend.Player;
import Backend.Tile.Land;
import UIComponents.Render.Coordinate;
import resources.OurColors;
import resources.Resources;
/** Banner
 * The flag banner that is displayed on the right side of the screen, which 
 * @author Ian
 *
 */
public class Banner extends Component {
	private boolean minimized;
	private Kingdomino k;
	private GameManager gm;
	private GamePanel gamePanel;
	private int width = 200;
	private int height = 500;
	private int bannerStartX = 155;
	private int bannerStartY = 20;
	private final ArrayList<DominoButton> buttons = new ArrayList<DominoButton>();
	private int prevPlayerIdx = -1;
	Banner(Coordinate position, Kingdomino k, int numButtons, GamePanel gp, Domino[] dominoes) {
		super(position, k);
		this.k = k;
		this.gm = k.getManager();
		this.gamePanel = gp;
		double x = position.getX();
		double y = position.getY();
		
//		Domino tempDomino = new Domino(new Tile(Land.FOREST,0), new Tile(Land.LAKE, 1), 30);
		int xOffset = 255;
		int yOffset = 90;
		for(int i=0; i<numButtons; i++) {
//			buttons.add(new DominoButton(new Coordinate(x+xOffset,y+yOffset,0), k, tempDomino));
			buttons.add(new DominoButton(new Coordinate(x+xOffset,y+yOffset,0), k, dominoes[i], (Graphics2D) k.getGamePanel().getGraphics()));
			yOffset += UITile.TILE_SIZE * 1.6;
		}
	}
	public final ArrayList<DominoButton> getButtons() {
		return buttons;
	}

	@Override
	public void setPosition(Coordinate coordinate) {
		// TODO Auto-generated method stub
		
	}

	public void setDominoes(Domino[]sorted) {
		for(int i=0; i<sorted.length; i++) {
			buttons.get(i).setDomino(sorted[i]);
		}
	}

	@Override
	public boolean onComponent(Coordinate c) {
		if(gm.getGameState() == GameState.TALLY_SCORE) {
	    	double x = getPosition().getX() + bannerStartX;
	    	double y = getPosition().getY() + bannerStartY;
	        boolean isOn = ((c.getX() > x && c.getX() < x+width) &&
	                (c.getY() > y && c.getY() < y+height));
	        return isOn;
		} else {
			return false;
		}
	}
	//shh this code is good
	private int[] scores = new int[7];
	private int[] displayedScores = new int[0];
	//Matlab reference
	private int scoreIdx=1;
	private boolean tallyAnimation = false;
	private Timer t = new Timer(600, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(scoreIdx > 7) {
				((Timer)e.getSource()).stop();
				scoreIdx = 0;
			} else {
				displayedScores = new int[scoreIdx];
				System.arraycopy(scores, 0, displayedScores, 0, scoreIdx);
				gamePanel.repaint();
				scoreIdx++;
				
			}
		}
	});
	private void showTally(Graphics2D g, int bannerStartX, int bannerStartY, int bannerEndX, int bannerEndY) {
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
		scores[scores.length-1] = total;
		xPos = bannerStartX+80;
		yPos = bannerStartY;
		
		for(int i=0; i<displayedScores.length; i++) {
			g.drawString(""+displayedScores[i], xPos, yPos);
			yPos += 50;
		}

		g.drawString("Total:", bannerStartX, bannerEndY-120);	
		g.drawString("NEXT", bannerStartX, bannerEndY-90);

		if(prevPlayerIdx == -1 || prevPlayerIdx != gamePanel.getViewedPlayerIndex()) {
			tallyAnimation = false;
			prevPlayerIdx = gamePanel.getViewedPlayerIndex();
			t.stop();
			scoreIdx = 0;
			gamePanel.repaint();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		Coordinate pos = super.getPosition();
		g.translate(pos.getX(), pos.getY());
		int widthAllowed = Kingdomino.FRAME_WIDTH - (int) pos.getX();
		int heightAllowed = Kingdomino.FRAME_HEIGHT - 50;
		int bannerWidth = width;
		int bannerEndX = bannerStartX + bannerWidth;
		int bannerEndY = bannerStartY + height;
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
		//so this is the diameter and i dont want to refactor now hf
		int radius = 40;
		g.fillOval(90,0,40,40);
		g.setColor(new Color(0xB08B13));
		//so this is the diameter and i dont want to refactor now hf
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
			
		} else if(getManager().getGameState() == GameState.TALLY_SCORE) {
			showTally((Graphics2D) g.create(), bannerStartX+60, bannerStartY+80, bannerEndX, bannerEndY);
			if(!tallyAnimation) {
				tallyAnimation = true;
				t.start();
			}
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
    public boolean isShown(){
        return minimized;
    }
	private int playerIdx = 0;
	@Override
	public void whenClicked() {

		ArrayList<Player>players = gm.getPlayers();
		playerIdx++;
		if(playerIdx >= players.size()) {
			gm.setGameState(GameState.ENDSCREEN);
		} else {
			
			gamePanel.setViewedPlayer(players.get(playerIdx));
			gm.nextPlayer();
		}
		tallyAnimation = false;
		scoreIdx = 1;
		gamePanel.repaint();
	}
}
