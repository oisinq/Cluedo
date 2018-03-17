/*  Cluedo - Sprint 3
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import java.util.ArrayList;

/**
 * This stores the cards that aren't allocated to any counter or to the envelope
 */
public class SpareCards {

    public static ArrayList<Card> cards = new ArrayList<>();

    SpareCards() {
    }

    public void addCard(Card c) {
        cards.add(c);
    }
}