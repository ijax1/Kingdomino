package UIComponents;
import Backend.Kingdomino;
import UIComponents.Render.Coordinate;

public class PlayButton extends Button{
	
	PlayButton(Coordinate position, Kingdomino k) {
		super(position, k);
		// TODO Auto-generated constructor stub
	}

	public void doAction() {
		super.game.play();
	}
	
}
