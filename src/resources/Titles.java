package resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Titles {
    private static final String[] MEDIEVAL_TITLES = {"The Honorable", "The Great", "The Wise", "The Bold", "The Valiant", "The Chivalrous", "The Magnificent", "The Noble", "The Righteous", "The Sagacious", "The Sublime", "The Brave", "The Virtuous"};
    private static final String[] RANDOM_TITLES = {"The Bloated", "The Distracted", "The Incompetent", "The Daft","The Blunderer", "The Bumbling", "The Corrupt", "The Unintelligible", "The Clumsy", "The Misclicker"};
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
            "Aaron",
            "Solomon",
            "Justinian",
            "Tybalt",
            "Josiah",
            "Constantine",
            "Josephine",
            "Joseph",
            "Jeremiah",
            "Augustine",
            "Robert",
            "Anthony",
            "Caesar",
            "Attila",
            "Genghis",
            "Vlad",
            "Anastasia",
            "Qianlong",
            "Akbar",
            "Babur",
            "Tamerlane",
            "Mary",
            "Victoria",
            "Charles",
            "Heinrich",
            "Simon",
            "Hongwu",
            "Meiji",
            "Mansa Musa",
            "Jacob",
            "Paul"
    };
    private static ArrayList<String> titles = new ArrayList<String>();
    private static ArrayList<String> badTitles = new ArrayList<String>();
    private static ArrayList<String> names = new ArrayList<String>();
	static {
		reset();
	}
	public static void reset() {
		titles.clear();
		badTitles.clear();
		names.clear();
		Collections.addAll(titles, MEDIEVAL_TITLES);
		Collections.addAll(badTitles, RANDOM_TITLES);
		Collections.addAll(names, NAMES);
	}
    // Generates a random medieval title
    public static String generateTitle() {
    	if(titles.isEmpty()) {
    		Collections.addAll(titles, MEDIEVAL_TITLES);
    	}
    	Random rand = new Random();
        int randIndex = rand.nextInt(titles.size());
        return titles.remove(randIndex);
    }
    public static String generateBadTitle() {
    	if(badTitles.isEmpty()) {
    		Collections.addAll(badTitles, RANDOM_TITLES);
    	}
    	Random rand = new Random();
        int randIndex = rand.nextInt(badTitles.size());
        return badTitles.remove(randIndex);
    }
    public static String generateName() {
    	if(names.isEmpty()) {
    		Collections.addAll(names, NAMES);
    	}
    	Random rand = new Random();
        int randIndex = rand.nextInt(names.size());
        return names.remove(randIndex);
    }
}
