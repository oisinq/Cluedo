import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;

public class Counters implements Iterable<Counter>, Iterator<Counter> {

    private final static HashSet<Counter> counters = new HashSet<>();
    private Iterator<Counter> iterator;

    Counters() {
        counters.add(new Counter("Scarlet", Color.RED, 7, 24));
        counters.add(new Counter("Mustard", Color.YELLOW, 0, 17));
        counters.add(new Counter("Peacock", Color.BLUE, 23, 19));
        counters.add(new Counter("Plum", new Color(95, 24, 175), 23, 6));
        counters.add(new Counter("Green", new Color(15, 188, 41), 9, 0));
        counters.add(new Counter("White", Color.WHITE, 14, 0));
    }

    public static Counter get(String name) {
        for (Counter c : counters) {
            if (c.hasName(name)) {
                return c;
            }
        }
        return null;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public Counter next() {
        return iterator.next();
    }

    public Iterator<Counter> iterator() {
        iterator = counters.iterator();
        return iterator;
    }


}
