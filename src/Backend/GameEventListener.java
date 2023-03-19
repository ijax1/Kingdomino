package Backend;

import Backend.GameManager.GameState;

public interface GameEventListener {
	void onStateChangedTo(GameState state);
	void onDominoSelected(Domino d);
	void onNextPlayer();
}
