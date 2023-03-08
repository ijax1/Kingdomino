package UIComponents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Backend.GameManager.GameState;
import Backend.Kingdomino;
import UIComponents.Render.Coordinate;
import resources.OurColors;

public class Banner extends Component {
	private boolean minimized;
	private DominoButton[] buttons = new DominoButton[4];
	Banner(Coordinate position, Kingdomino k) {
		super(position, k);
//		buttons[0] = new DominoButton(new Coordinate(200,50,0), k);
//		buttons[1] = new DominoButton(new Coordinate(200,50,0), k);
//		buttons[2] = new DominoButton(new Coordinate(200,50,0), k);
//		buttons[3] = new DominoButton(new Coordinate(200,50,0), k);
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

	@Override
	public void draw(Graphics2D g) {
		Coordinate pos = super.getPosition();
		g.translate(pos.getX(), pos.getY());
		g.setColor(OurColors.BUTTON_COLOR.brighter());
		int[] xPoints = {150,150,250,350,350};
		int[] yPoints = {20,400,350,400,20};
		g.fillPolygon(xPoints,yPoints, 5);
		int[] xPoints1 = {170,170,250,330,330};
		int[] yPoints1 = {20,380,330,380,20};
		g.setColor(Color.WHITE);
		
		g.fillPolygon(xPoints1, yPoints1, 5);
		
		g.setColor(new Color(0x432616));
		g.fillRect(0,10,350,20);
		g.setColor(OurColors.ACCENT_COLOR);
		g.fillOval(0,0,40,40);
		if(getManager().getGameState() == GameState.PLAYER_TURN) {
			
		} else if(getManager().getGameState() == GameState.TALLY_SCORE) {
			
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
