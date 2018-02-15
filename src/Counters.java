import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;

public class Counters implements Iterable<Counter>, Iterator<Counter> {

    private final HashSet<Counter> counters = new HashSet<>();
    private Iterator<Counter> iterator;

    Counters() {
        counters.add(new Counter("Scarlet", Color.RED, 204, 601));
        counters.add(new Counter("Mustard", Color.YELLOW, 44, 440));
        counters.add(new Counter("Peacock", Color.BLUE, 572, 487));
        counters.add(new Counter("Plum", new Color(95, 24, 175), 572, 188));
        counters.add(new Counter("Green", new Color(15, 188, 41), 250, 50));
        counters.add(new Counter("White", Color.WHITE, 365, 50));
    }

    public Counter get(String name) {
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
