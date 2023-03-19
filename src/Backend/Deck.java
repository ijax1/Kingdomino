package Backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import Backend.Tile.Land;

import Backend.Player;
import UIComponents.*;
/**
 * A class representing a Kingdomino deck. Dominoes are created according to the distribution in-game.
 * @author Jiming
 *
 */
public class Deck {
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
            new Domino(new Tile(WHEAT, 1), new Tile(MINE, 0), 23),
            new Domino(new Tile(FOREST, 1), new Tile(WHEAT, 0), 24),
            new Domino(new Tile(FOREST, 1), new Tile(WHEAT, 0), 25),
            new Domino(new Tile(FOREST, 1), new Tile(WHEAT, 0), 26),
            new Domino(new Tile(FOREST, 1), new Tile(WHEAT, 0), 27),
            new Domino(new Tile(FOREST, 1), new Tile(LAKE, 0), 28),
            new Domino(new Tile(FOREST, 1), new Tile(PASTURE, 0), 29),
            new Domino(new Tile(LAKE, 1), new Tile(WHEAT, 0), 30),
            new Domino(new Tile(LAKE, 1), new Tile(WHEAT, 0), 31),
            new Domino(new Tile(LAKE, 1), new Tile(FOREST, 0), 32),
            new Domino(new Tile(LAKE, 1), new Tile(FOREST, 0), 33),
            new Domino(new Tile(LAKE, 1), new Tile(FOREST, 0), 34),
            new Domino(new Tile(LAKE, 1), new Tile(FOREST, 0), 35),
            new Domino(new Tile(WHEAT, 0), new Tile(PASTURE, 1), 36),
            new Domino(new Tile(PASTURE, 0), new Tile(SWAMP, 1), 39),
            new Domino(new Tile(MINE, 1), new Tile(WHEAT, 0), 40),
            new Domino(new Tile(WHEAT, 1), new Tile(SWAMP, 2), 43),
            new Domino(new Tile(LAKE, 0), new Tile(PASTURE, 1), 37),
            new Domino(new Tile(WHEAT, 0), new Tile(SWAMP, 1), 38),
            new Domino(new Tile(WHEAT, 0), new Tile(PASTURE, 2), 41),
            new Domino(new Tile(LAKE, 0), new Tile(PASTURE, 2), 42),
            new Domino(new Tile(PASTURE, 0), new Tile(SWAMP, 2), 44),
            new Domino(new Tile(MINE, 2), new Tile(WHEAT, 0), 45),
            new Domino(new Tile(SWAMP, 0), new Tile(MINE, 2), 46),
            new Domino(new Tile(SWAMP, 0), new Tile(MINE, 2), 47),
            new Domino(new Tile(WHEAT, 0), new Tile(MINE, 3), 48)
    };

    //Instance Variables
    private ArrayList<Domino> deck = new ArrayList<>();
    private Domino[] dominoesToSelect;
    private boolean[] selected;


    /** 
     * Creates deck and shuffles. Note the deck will always have 48 dominoes, even for two players.
     */
    public Deck() {

        this.dominoesToSelect = new Domino[]{null, null, null, null};
        this.selected = new boolean[]{false, false, false, false};

        // create a deck of 48 random dominos
        for (int i = 0; i < 48; i++) {
            deck.add(STARTING_DECK[i]);
        }

        //if (isTwoPlayer) {
        // take first half of card
        //this.deck = deck.subList(0, deck.size() / 2);
        //}

        // shuffle the deck
        this.shuffleDeck();
    }
    /** 
     *  Shuffles deck.
     */
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public int dominoesRemaining() {
        return deck.size();
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }
    /**
     * Draws 4 dominoes from the deck, sorts them by value
     * @return an array of 4 tiles removed from the deck, sorted from highest to lowest value
     */
    public Domino[] getDominoesToSelect() {
        // remove 4 items from main deck, places in list and reutrns list
        if (this.dominoesRemaining() >= 4) {
            for (int i = 0; i < 4; i++) {
                dominoesToSelect[i] = deck.remove(0);
            }
        } else {
            // not enough cards
        }
        sortDominoes(dominoesToSelect);
        return dominoesToSelect;
    }

    private void sortDominoes(Domino[] dominoes) {
        Arrays.sort(dominoes, new Comparator<Domino>() {
            @Override
            public int compare(Domino o1, Domino o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });
    }

    // --------------
    @Deprecated
    public void chooseDomino(Player player, int index) {
        //leaving alone for now

    }
    // --------------
    /**
     * Sets that a domino is selected (unused)
     * @param index from 0-3
     */
    @Deprecated
    public void setSelected(int index) {
        selected[index] = true;
    }
    @Deprecated
    public boolean[] getSelected() {
        return selected;
    }
}