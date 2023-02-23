import java.util.ArrayList;
import java.util.Collections;
import UIComponents.Domino;


class deck() {

    private ArrayList<Domino> deck = new ArrayList<>();
    private Domino[] dominoesToSelect = {null, null, null, null};
    private boolean[] selected = {false, false, false, false};

    public deck(boolean isTwoPlayer) {
        //create a deck of random dominos
        for (int i = 0; i < 4; i++) {
            Domino d = new Domino();
            deck.add(d);
        }
 
        if (isTwoPlayer) {
            //take first half
            this.deck = deck.subList(0, deck.size() / 2);
        }

        //shuffle the deck
        this.shuffleDeck();
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
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
        if (this.dominoesRemaining() >= 4) {
            for (int i = 0; i < 4; i++) {
                dominoesToSelect[i] = deck.remove(0);
            }
        } else {
            //not enough cards
        }
        
    }

    public boolean chooseDomino(Player player) {
        //necessary?
    }

    public void setSelected(int index) {
        selected[index] = true;
    }

    public boolean[] getSelected() {
        return selected;
    }
    
}