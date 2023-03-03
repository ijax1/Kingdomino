package resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Titles {
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
    private ArrayList<String> titles = new ArrayList<String>();
    private ArrayList<String> badTitles = new ArrayList<String>();
    private ArrayList<String> names = new ArrayList<String>();
	public Titles() {
		Collections.addAll(titles, MEDIEVAL_TITLES);
		Collections.addAll(badTitles, RANDOM_TITLES);
		Collections.addAll(names, NAMES);
	}
    // Generates a random medieval title
    public String generateTitle() {
    	Random rand = new Random();
        int randIndex = rand.nextInt(titles.size());
        return titles.remove(randIndex);
    }
    public String generateBadTitle() {
    	Random rand = new Random();
        int randIndex = rand.nextInt(badTitles.size());
        return badTitles.remove(randIndex);
    }
    public String generateName() {
    	Random rand = new Random();
        int randIndex = rand.nextInt(names.size());
        return names.remove(randIndex);
    }
}
