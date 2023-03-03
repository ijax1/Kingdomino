package resources;

import java.util.Random;

public class Titles {
	private Titles() {
		
	}
	// Class variables
    private static final String[] MEDIEVAL_TITLES = {"The Honorable", "The Great", "The Wise", "The Bold", "The Valiant", "The Chivalrous", "The Magnificent", "The Noble", "The Righteous", "The Sagacious", "The Sublime", "The Brave"};
    private static final String[] RANDOM_TITLES = {"The Bloated", "The Distracted", "The Incompetent", "The Daft","The Blunderer", "The Bumbling", "The Corrupt"};
    private static final String[] NAMES = {"Richard",
    		"William",
    		"Björn",
    		"Archibald",
    		"Aberforth",
    		"Ella",
    		"Genevieve",
    		"Godiva",
    		"Helen",
    		"Hildegund",
    };

    // Generates a random medieval title
    public static String generateTitle() {
        Random rand = new Random();
        int randIndex = rand.nextInt(MEDIEVAL_TITLES.length);
        return MEDIEVAL_TITLES[randIndex];
    }
    public static String generateBadTitle() {
    	Random rand = new Random();
        int randIndex = rand.nextInt(RANDOM_TITLES.length);
        return RANDOM_TITLES[randIndex];
    }
    public static String generateName() {
    	Random rand = new Random();
        int randIndex = rand.nextInt(RANDOM_TITLES.length);
        return RANDOM_TITLES[randIndex];
    }
    // Returns a temporary name for the AI
    public static String generateAIName() {
        return "Computer";
    }
}
