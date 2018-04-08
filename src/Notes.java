/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This class stores the notes - a list of all the cards and the info the user knows about them
 */
public class Notes {

    public LinkedHashMap<String, String> values;

    Notes() {
        // I'm using a LinkedHashMap instead of a Map because I want to print off the values in the order they were added later
        // This makes it much easier to read when printing
        // I'm using a map because I think it's the best data structure to store each card and its corresponding status
        values = new LinkedHashMap<>();

        // I don't need to separate these strings, but I think it's easier to understand what's happening.
        // I may also need these separated later on
        String players[] = {"Green", "Mustard", "Peacock", "Plum", "Scarlet", "White"};
        String weapons[] = {"Wrench", "Candlestick", "Dagger", "Pistol", "Lead Pipe", "Rope"};
        String rooms[] = {"Kitchen", "Ball Room", "Conservatory", "Dining Room", "Billiard Room", "Library", "Lounge", "Hall", "Study"};

        // We place " " as the value for each card in the map
        for (String p : players) {
            values.put(p, " ");
        }

        for (String w : weapons) {
            values.put(w, " ");
        }

        for (String r : rooms) {

            values.put(r, " ");
        }
    }

    /**
     * This is called before the notes are printed - it makes sure the LinkedHashMap is up-to-date
     */
    public void refresh(Counter counter, ArrayList<Card> sharedCards) {
        for (Card c : sharedCards) {
            values.put(c.getName(), "A");
        }

        for (Card c : counter.getCards()) {
            values.put(c.getName(), "X");
        }
    }

    /**
     * If we find a card in questioning, it's added here
     */
    public void addSeenCard(String cardName) {
        values.put(cardName, "V");
    }
}
