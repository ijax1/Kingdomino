package resources;

import java.util.Random;

public class Titles {
	private Titles() {
		
	}
	// Class variables
    static final String[] MEDIEVAL_TITLES = {"The Honorable", "The Great", "The Wise", "The Bold", "The Valiant", "The Chivalrous", "The Magnificent", "The Noble", "The Righteous", "The Sagacious"};
    // Generates a random medieval title
    public static String generateTitle() {
        Random rand = new Random();
        int randIndex = rand.nextInt(MEDIEVAL_TITLES.length);
        return MEDIEVAL_TITLES[randIndex];
    }
    // Returns a temporary name for the AI
    public static String generateAIName() {
        return "Computer";
    }
}
