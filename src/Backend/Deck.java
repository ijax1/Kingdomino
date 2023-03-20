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
    private Player[] whoLocked = new Player[4];


    /** 
     * Creates deck and shuffles. Note the deck will always have 48 dominoes, even for two players.
     */
    public Deck() {

        this.dominoesToSelect = new Domino[]{null, null, null, null};

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
    /**
     * Gets the number of dominoes remaining in the deck
     * @return the number of dominoes remaining from 0-48
     */
    public int dominoesRemaining() {
        return deck.size();
    }
    /**
     * Returns if deck is empty
     * @return true if deck is empty
     */
    public boolean isEmpty() {
        return deck.isEmpty();
    }
    /**
     * Gets the dominoes that have not been selected
     * @return an array of 0-4 tiles
     */
    public Domino[] getDominoesToSelect() {
    	if(dominoesToSelect[0] == null) {
    		getNewDominoes();
    	}
    	
    	int arrLength = 0;
    	for(int i=0; i<dominoesToSelect.length; i++) {
    		if(whoLocked[i] == null) {
    			arrLength++;
    		}
    	}
    	Domino[] options = new Domino[arrLength];
    	int j=0;
    	for(int i=0; i<dominoesToSelect.length; i++) {
    		if(whoLocked[i] == null) {
    			options[j] = dominoesToSelect[i];
    			j++;
    		}
    	}
    	
        return options;
    }
    /**
     * Gets the current four dominoes displayed, draws 4 dominoes if there are none
     * @return an array of 4 tiles removed from the deck, sorted from highest to lowest value
     */
    public Domino[] getAllDominoes() {
    	if(dominoesToSelect[0] == null) {
    		getNewDominoes();
    	}
        return dominoesToSelect;
    }
    /**
     * Draws 4 dominoes from the deck, sorts them by value
     * @return an array of 4 tiles removed from the deck, sorted from highest to lowest value, null if no dominoes left
     */
    public Domino[] getNewDominoes() {
    	whoLocked = new Player[4];
        // remove 4 items from main deck, places in list and reutrns list
        if (this.dominoesRemaining() >= 4) {
            for (int i = 0; i < 4; i++) {
                dominoesToSelect[i] = deck.remove(0);
            }
        } else {
            return null;
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
    public void setLocked(Domino d, Player player) {
    	for(int i=0; i< dominoesToSelect.length; i++){
    		//no you don't get an equals method
    		if(dominoesToSelect[i]==d) {
    			//LOCK IN
    			System.out.println("Deck: locked in");
    			whoLocked[i] = player;
    		}
    	}
    }
    /**
     * Returns the array of Players that have selected and confirmed the corresponding Dominoes (from getAllDominoes)
     * @return an array of 4 Players, some of whom are null, corresponding to who locked which Domino
     */
    public Player[] getLocked() {
        return whoLocked;
    }
}