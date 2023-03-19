package Tests;

import Backend.Deck;
import Backend.Domino;

public class DeckTester {
	public static void main(String[] args) {
		Deck d = new Deck();
		Domino[] select = d.getDominoesToSelect();
		for(Domino dom: select) {
			System.out.println(""+dom+dom.getValue());
		}
	}
}
