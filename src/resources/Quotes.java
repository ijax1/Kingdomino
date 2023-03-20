package resources;

public class Quotes {
    private static final String[][] QUOTES = {
            {"It is better to be feared than loved, if you cannot be both.", "Niccolo Machiavelli"},
            {"To be or not to be, that is the question.", "Shakespeare's Hamlet"},
            {"Who overcomes by force, hath overcome but half his foe.", "Milton's Paradise Lost"},
            {"The mind is its own place, and in itself can make a heaven of hell, a hell of heaven.", "Milton's Paradise Lost"},
            {"All the world's a stage, and all the men and women are merely players.", "Shakespeare's As You Like It"},
            {"If it were done when 'tis done, then 'twere well it were done quickly.", "Shakespeare's Macbeth"},
            {"Look like th' innocent flower, but be the serpent under 't.", "Shakespeare's Macbeth"},
            {"Fair is foul, and foul is fair.", "Shakespeare's Hamlet"},
            {"Something is rotten in the state of Denmark.", "Shakespeare's Hamlet"},
            {"Most men seem to live according to sense rather than reason.", "St. Thomas Aquinas"},
            {"If the highest aim of a captain were to preserve his ship, he would keep it in port forever.", "St. Thomas Aquinas"},
            {"A man's greatest joy is crushing his enemies.", "Genghis Khan"},
            {"My life was too short to achieve conquest of the world. That task is left for you.", "Genghis Khan"},
            {"An action committed in anger is an action doomed to fail.", "Genghis Khan"},
            {"The wise man does at once what the fool does finally.", "Niccolo Machiavelli"},
            {"Never was anything great achieved without danger.", "Niccolo Machiavelli"},
            {"There is nothing on this earth more to be prized than true friendship.", "St. Thomas Aquinas"},
            {"If the populace knew with what idiocy they were ruled, they would revolt.", "Charlemagne"},
            {"Do not sleep. Be awake on your throne.", "Suleyman the Magnificent"},
            {"In this world a spell of health is the best state.", "Suleyman the Magnificent"},
            {"Courage! Do not fall back.", "Joan of Arc"},
            {"I am not afraid; I was born to do this.", "Joan of Arc"},
            {"Keep cool and you will command everyone.", "Justinian I"},
            {"Justice is the constant and eternal purpose that renders to each his due.", "Justinian I"},
            {"Give me twenty-six soldiers of lead and I will conquer the world.", "Johannes Gutenberg"},
            {"I suddenly realized I was a writer of wide reputation and most of it bad.", "Theodora"},
            {"Royalty is a fine burial shroud.", "Theodora"},
            {"The pain that we cause is the cause of our pain.", "Leif Erikson"},
            {"For it is in giving that we receive.", "St. Francis of Assisi"},
            {"Peace if possible, truth at all costs.", "Martin Luther"}
    };

    public static String[] getQuote() {
        return QUOTES[(int) (Math.random() * QUOTES.length)];
    }
}
