import java.util.ArrayList;
import java.util.Collections;

class deck() {
    private ArrayList<Domino> deck;
    private Domino[] dominoesToSelect;
    private boolean[] selected = {false, false, false, false};

    public deck(boolean isTwoPlayer) {
        //create a deck of random dominos
        if (isTwoPlayer) {
            //take first half
            this.deck = deck.subList(0, deck.size / 2);
        }
    }

    public shuffleDeck() {
        collections.shuffle(deck);
    }

    public int dominoesRemaining() {
        return deck.size();
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public Domino[] getDominoesToSelect() {
        //what about 3 players?
        //remove 4 items from main deck and place them in list
        if (this.deminoesRemaining()) {
            dominoesToSelect = [deck.remove(0), deck.remove(0), deck.remove(0), deck.remove(0)];
        } else {
            //not enough cards
        }
        
    }

    public void setSelected(int index) {
        selected[index] = true;
    }

    public boolean[] getSelected() {
        return selected;
    }
}