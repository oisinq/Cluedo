import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Notes {

    public Map<String, String> values;
    private String players[] = {"Green","Mustard","Peacock","Plum","Scarlet","White"};
    private String weapons[] = {"Wrench","Candlestick","Dagger","Pistol","Lead Pipe","Rope"};
    private String rooms[] = {"Kitchen","Ball Room","Conservatory","Dining Room","Billiard Room","Library","Lounge","Hall","Study"};

    Notes() {
        values = new HashMap<>();

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

    public void refresh(Counter counter, ArrayList<Card> sharedCards) {

        for(Card c : sharedCards) {
            values.put(c.getName(), "A");
        }

        for(Card c : counter.getCards()) {
            values.put(c.getName(), "X");
        }

    }
}
