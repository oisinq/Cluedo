/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class Counters implements Iterable<Counter>, Iterator<Counter> {


    final static ArrayList<Counter> players = new ArrayList<>();
    // This arraylist stores all of the different counters - this makes it easier to access individual counters
    private final static ArrayList<Counter> counters = new ArrayList<>();
    private Iterator<Counter> iterator;

    Counters() {
    }

    /**
     * Returns the counter with the entered string as its name
     */
    public static Counter get(String name) {
        for (Counter c : counters) {
            if (c.hasCharacterName(name)) {
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

    /**
     * Creates the corresponding counter and adds it to the arraylist
     */
    public void createCounter(String userName, String userChoice, int rollFirst) {
        switch (userChoice) {
            case "Plum":
                counters.add(new Counter(userName, "Plum", new Color(95, 24, 175), 23, 6, rollFirst));
                players.add(new Counter(userName, "Plum", new Color(95, 24, 175), 23, 6, rollFirst));
                break;
            case "White":
                counters.add(new Counter(userName, "White", Color.WHITE, 14, 0, rollFirst));
                players.add(new Counter(userName, "White", Color.WHITE, 14, 0, rollFirst));
                break;
            case "Scarlet":
                counters.add(new Counter(userName, "Scarlet", Color.RED, 7, 24, rollFirst));
                players.add(new Counter(userName, "Scarlet", Color.RED, 7, 24, rollFirst));
                break;
            case "Green":
                counters.add(new Counter(userName, "Green", new Color(15, 188, 41), 9, 0, rollFirst));
                players.add(new Counter(userName, "Green", new Color(15, 188, 41), 9, 0, rollFirst));
                break;
            case "Mustard":
                counters.add(new Counter(userName, "Mustard", Color.YELLOW, 0, 17, rollFirst));
                players.add(new Counter(userName, "Mustard", Color.YELLOW, 0, 17, rollFirst));
                break;
            case "Peacock":
                counters.add(new Counter(userName, "Peacock", Color.BLUE, 23, 19, rollFirst));
                players.add(new Counter(userName, "Peacock", Color.BLUE, 23, 19, rollFirst));
                break;
        }
    }

    public void createNPC(String userName, String userChoice, int rollFirst) {
        switch (userChoice) {
            case "Plum":
                counters.add(new Counter(userName, "Plum", new Color(95, 24, 175), 23, 6, rollFirst));
                break;
            case "White":
                counters.add(new Counter(userName, "White", Color.WHITE, 14, 0, rollFirst));
                break;
            case "Scarlet":
                counters.add(new Counter(userName, "Scarlet", Color.RED, 7, 24, rollFirst));
                break;
            case "Green":
                counters.add(new Counter(userName, "Green", new Color(15, 188, 41), 9, 0, rollFirst));
                break;
            case "Mustard":
                counters.add(new Counter(userName, "Mustard", Color.YELLOW, 0, 17, rollFirst));
                break;
            case "Peacock":
                counters.add(new Counter(userName, "Peacock", Color.BLUE, 23, 19, rollFirst));
                break;
        }

    }


}
