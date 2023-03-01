package UIComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import Backend.Kingdomino;
import UIComponents.Render.Coordinate;
import resources.OurColors;

public class Banner extends Component {
	Banner(Coordinate position, Kingdomino k) {
		super(position, k);
		// TODO Auto-generated constructor stub
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
		
	}

	@Override
	public void whenClicked() {
		// TODO Auto-generated method stub
		
	}
}
