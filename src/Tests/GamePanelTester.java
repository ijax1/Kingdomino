package Tests;

import java.util.ArrayList;

import Backend.Domino;
import Backend.GameManager;
import Backend.GameManager.GameState;
import Backend.HumanPlayer;
import Backend.Kingdomino;
import Backend.Player;
import Backend.Tile;
import resources.OurColors;

public class GamePanelTester {
	public static void main(String[]args) {
		new GamePanelTester().runGUI();
	}
	public void runGUI() {
		//Creating 4 fake players with scores 20, 30, 25 and 25 (this should test ties well)
		ArrayList<Player>fakePlayers = new ArrayList<Player>(4);
		fakePlayers.add(new TestPlayer(OurColors.RED, "Don Quixote", "The Ingenious",20));
		fakePlayers.add(new TestPlayer(OurColors.BLUE, "King Arthur", "The Round",30));
		fakePlayers.add(new TestPlayer(OurColors.GREEN, "Sir Gawain", "The Green",25));
		fakePlayers.add(new TestPlayer(OurColors.YELLOW, "Ian Jackson", "The Glorious",25));

		
		Domino[] dList = {
				new Domino(new Tile(Tile.Land.FOREST, 0),new Tile(Tile.Land.FOREST, 0),0),
				new Domino(new Tile(Tile.Land.SWAMP, 0),new Tile(Tile.Land.WHEAT, 0),0),
				new Domino(new Tile(Tile.Land.LAKE, 0),new Tile(Tile.Land.PASTURE, 0),0),
				new Domino(new Tile(Tile.Land.MINE, 0),new Tile(Tile.Land.LAKE, 0),0)
		};
		int ind = 0;
//		for(Player p: fakePlayers){
//			//p.setCurrentDomino(dList[ind]);
//			ind++;
//		}
		Kingdomino k = new Kingdomino();
		GameManager gm = k.getManager();
		ArrayList<Player>humanPlayers = new ArrayList<>(4);
		humanPlayers.add(new HumanPlayer(OurColors.RED, "AAA", "aaa", gm));
		humanPlayers.add(new HumanPlayer(OurColors.RED, "BBB", "aaa", gm));
		humanPlayers.add(new HumanPlayer(OurColors.RED, "CCC", "aaa", gm));
		humanPlayers.add(new HumanPlayer(OurColors.RED, "DDD", "aaa", gm));
		//k.getManager().setPlayers(humanPlayers);
		k.getManager().setPlayers(fakePlayers);
		k.getManager().setGameState(GameState.PLAYER_TURN);
//		GameManager gm = k.getManager();
//		

//		GamePanel panel = new GamePanel(fakePlayers, k);
//		frame.setSize(1280,720);
//		frame.add(panel);
//		panel.add(new UIDomino());
//		panel.add(new UIGrid());
//
//		frame.pack();
//		frame.setVisible(true);
//		panel.repaint();
	}
}
