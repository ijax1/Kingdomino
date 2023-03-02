package Backend;

import java.util.ArrayList;
import java.util.Collections;
import Backend.Tile.Land;

import Backend.Player;
import UIComponents.*;
class Deck {
    private Land FOREST = Land.FOREST;
    private Land LAKE = Land.LAKE;
    private Land WHEAT = Land.WHEAT;
    private Land SWAMP = Land.SWAMP;
    private Land PASTURE = Land.PASTURE;
    private Land MINE = Land.MINE;
    private Land CASTLE = Land.CASTLE;

    private final Domino[] STARTING_DECK = {
            new Domino(new Tile(WHEAT, 0), new Tile(WHEAT, 0), 1),
            new Domino(new Tile(WHEAT, 0), new Tile(WHEAT, 0), 2),
            new Domino(new Tile(FOREST, 0), new Tile(FOREST, 0), 3),
            new Domino(new Tile(FOREST, 0), new Tile(FOREST, 0), 4),
            new Domino(new Tile(FOREST, 0), new Tile(FOREST, 0), 5),
            new Domino(new Tile(FOREST, 0), new Tile(FOREST, 0), 6),
            new Domino(new Tile(LAKE, 0), new Tile(LAKE, 0), 7),
            new Domino(new Tile(LAKE, 0), new Tile(LAKE, 0), 8),
            new Domino(new Tile(LAKE, 0), new Tile(LAKE, 0), 9),
            new Domino(new Tile(PASTURE, 0), new Tile(PASTURE, 0), 10),
            new Domino(new Tile(PASTURE, 0), new Tile(PASTURE, 0), 11),
            new Domino(new Tile(SWAMP, 0), new Tile(SWAMP, 0), 12),
            new Domino(new Tile(WHEAT, 0), new Tile(FOREST, 0), 13),
            new Domino(new Tile(WHEAT, 0), new Tile(LAKE, 0), 14),
            new Domino(new Tile(WHEAT, 0), new Tile(PASTURE, 0), 15),
            new Domino(new Tile(WHEAT, 0), new Tile(SWAMP, 0), 16),
            new Domino(new Tile(FOREST, 0), new Tile(LAKE, 0), 17),
            new Domino(new Tile(FOREST, 0), new Tile(PASTURE, 0), 18),
            new Domino(new Tile(WHEAT, 1), new Tile(FOREST, 0), 19),
            new Domino(new Tile(WHEAT, 1), new Tile(LAKE, 0), 20),
            new Domino(new Tile(WHEAT, 1), new Tile(PASTURE, 0), 21),
            new Domino(new Tile(WHEAT, 1), new Tile(SWAMP, 0), 22),
            new Domino(new Tile(WHEAT, 1), new Tile(PASTURE, 0), 10),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino(),
            new Domino()
    };

    //Instance Variables
    private ArrayList<Domino> deck = new ArrayList<>();
    private Domino[] dominoesToSelect;
    private boolean[] selected;


    //Create deck and shuffles
    public Deck() {

        this.dominoesToSelect = new Domino[] {null, null, null, null};
        this.selected = new boolean[] {false, false, false, false};

        // create a deck of 48 random dominos
        for (int i = 0; i < 48; i++) {
            Domino d = new Domino();
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