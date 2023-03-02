package Backend;

import java.util.ArrayList;
import java.util.Collections;

import Backend.Player;
import UIComponents.*;
class Deck {
    //Instance Variables
    private ArrayList<Domino> deck = new ArrayList<>();
    private Domino[] dominoesToSelect;
    private boolean[] selected;


    //Create deck and shuffles
    public Deck() {

        this.dominoesToSelect = new Domino[]{null, null, null, null};
        this.selected = new boolean[]{false, false, false, false};

        // create a deck of 48 random dominos
        for (int i = 0; i < 48; i++) {
            //Domino d = new Domino();
            //deck.add(d);
        }

        //if (isTwoPlayer) {
            // take first half of card
            //this.deck = deck.subList(0, deck.size() / 2);
        //}

        // shuffle the deck
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

    //Returns a list of 4 tiles removed from the deck
    public Domino[] getDominoesToSelect() {
        // remove 4 items from main deck, places in list and reutrns list
        if (this.dominoesRemaining() >= 4) {
            for (int i = 0; i < 4; i++) {
                dominoesToSelect[i] = deck.remove(0);
            }
        } else {
            // not enough cards
        }
        return dominoesToSelect;
    }

    // --------------
    public void chooseDomino(Player player, int index) {
        //leaving alone for now

    }
    // --------------

    public void setSelected(int index) {
        selected[index] = true;
    }
    public boolean[] getSelected() {
        return selected;
    }
}