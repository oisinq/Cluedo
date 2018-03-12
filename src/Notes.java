import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This class stores the notes - a list of all the cards and the info the user knows about them
 */
public class Notes {

    public LinkedHashMap<String, String> values;
    // I don't need to separate these strings, but I think it's easier to understand what's happening.
    // I may also need these separated later on
    private String players[] = {"Green","Mustard","Peacock","Plum","Scarlet","White"};
    private String weapons[] = {"Wrench","Candlestick","Dagger","Pistol","Lead Pipe","Rope"};
    private String rooms[] = {"Kitchen","Ball Room","Conservatory","Dining Room","Billiard Room","Library","Lounge","Hall","Study"};

    Notes() {
        // I'm using a LinkedHashMap instead of a Map because I want to print off the values in the order they were added later
        // This makes it much easier to read when printing
        values = new LinkedHashMap<>();

        for(String p : players) {
            values.put(p, " ");
        }

        for(String w : weapons) {
            values.put(w, " ");
        }

        for(String r : rooms) {

            values.put(r, " ");
        }
    }

    /**
     * This is called before the notes are printed - it makes sure the LinkedHashMap is up-to-date
     */
    public void refresh(Counter counter, ArrayList<Card> sharedCards) {

        for(Card c : sharedCards) {
            values.put(c.getName(), "A");
        }

        for(Card c : counter.getCards()) {
            values.put(c.getName(), "X");
        }
    }
}
