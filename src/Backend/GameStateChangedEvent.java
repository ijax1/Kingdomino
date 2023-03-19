package Backend;

import Backend.GameManager.GameState;

public interface GameStateChangedEvent extends GameEvent {
	void stateChangedTo(GameState state);
}
