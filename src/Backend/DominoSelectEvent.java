package Backend;

public interface DominoSelectEvent extends GameEvent {
	void onDominoSelected(Domino d);
}
