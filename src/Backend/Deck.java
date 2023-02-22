import java.util.ArrayList;
import java.util.Collections;

class deck() {
    private ArrayList<Domino> deck;
    private Domino[] dominoesToSelect;
    private boolean[] selected;

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

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public Domino[] getDominoesToSelect() {
        //remove 4 items from main deck and place them in list
    }

    public boolean chooseDomino(Player player){

    }


}