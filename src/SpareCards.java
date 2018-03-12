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