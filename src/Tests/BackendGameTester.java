package Tests;

import java.util.ArrayList;

import Backend.Domino;
import Backend.GameEventListener;
import Backend.GameManager;
import Backend.GameManager.GameState;
import Backend.Player;
import Backend.SkilledStrategy;
import resources.OurColors;

public class BackendGameTester implements GameEventListener {
	public static void main(String[]args) {
		new BackendGameTester().test();
	}
		

//			printarr(g.getDominoesToSelect());
//			g.getCurrentPlayer().setNextDomino(g.getDominoesToSelect()[0]);
	public void test() {
		GameManager g = new GameManager();
		g.addListener(this);
		g.setGameState(GameState.INITIAL);
		ArrayList<Player>fakePlayers = new ArrayList<Player>(4);
		fakePlayers.add(new SkilledStrategy(OurColors.RED, g));
		fakePlayers.add(new SkilledStrategy(OurColors.BLUE, g));
		fakePlayers.add(new SkilledStrategy(OurColors.GREEN, g));
		fakePlayers.add(new SkilledStrategy(OurColors.YELLOW, g));
		g.setPlayers(fakePlayers);
		g.setMode(true);
		g.setNumGames(10);
		g.setGameState(GameState.PLAYER_TURN);
	}
	private static void printarr(Object[] arr) {
		for(int i=0; i<arr.length; i++){
			System.out.print(arr[i]);
		}
		System.out.println();
	}
	@Override
	public void onStateChangedTo(GameState state) {
		System.out.println("backend tester: state changed to "+ state);
		// TODO Auto-generated method stub
		if(state == GameState.END_ROUND) {
			System.out.println("done");
		}
	}
	@Override
	public void onDominoSelected(Domino d, boolean recallNextPlayer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onNextPlayer() {
		
	}

	@Override
	public void onFinishTurn() {

	}

	@Override
	public void initDominoes() {

	}
}
