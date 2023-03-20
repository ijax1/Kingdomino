package Backend;

import Backend.GameManager.GameState;

public interface GameEventListener {
	void onStateChangedTo(GameState state);
//	void onDominoSelected(Domino d);

	void onDominoSelected(Domino d, boolean recallNextPlayer);

	void onNextPlayer();

	void onFinishTurn();

	void initDominoes();
}
