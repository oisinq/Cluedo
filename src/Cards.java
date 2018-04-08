/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import java.util.ArrayList;
import java.util.Random;

public class Cards {
    private String[] cardList = (new String[]{"Mustard", "Scarlet", "Green", "Peacock", "White", "Plum",
        "Pistol", "Dagger", "Lead Pipe", "Candlestick", "Rope", "Wrench",
        "Ball Room", "Library", "Hall", "Conservatory", "Billiard Room", "Study", "Lounge", "Dining Room", "Kitchen"});
    // This array tracks if a card has been assigned to a player/envelope/SpareCards yet
    private int[] given = (new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    private ArrayList<Card> cards = new ArrayList<>();

    Cards() {
        createCard();// Creates the cards
    }

    /**
     * Returns the card matching the string name
     */
    private Card getCard(String name) {
        for (Card c : cards) {
            if (c.hasCardName(name)) {
                return c;
            }
        }
        return null;
    }


    /**
     * Used to generate random numbers
     */
    private Random Random() {
        Random rand;
        rand = new Random();
        return rand;
    }

    /**
     * Inserts three cards into the Envelope class
     */
    public void Envelope() {
        Envelope file = new Envelope();

        int temp = Random().nextInt(6);
        file.setPerson(getCard(cardList[temp]));
        given[temp] = 1;

        temp = Random().nextInt(6) + 6;
        file.setWeapon(getCard(cardList[temp]));
        given[temp] = 1;

        temp = Random().nextInt(8) + 12;
        file.setRoom(getCard(cardList[temp]));
        given[temp] = 1;
    }

    /**
     * Assigns the cards randomly to the players, and assigns any leftover cars to SpareCards
     */
    public void CardHolder(String[] strArray, int amount, int number) {//Distributes the cards amongst the players
        int track = 0;// Counts through the amount of cards to be added to each player
        int x = 0;// Tracks the position in the string array of players
        int temp = Random().nextInt(21);// Creates a random number within the confines of the array of card names
        while (x < number) {// Counts to the amount of players
            track = 0;

            switch (strArray[x]) { //Switches depending on the player name found in the string array
                case "Plum":
                    while (track < amount) { // Counts to the amount of cards each player should have
                        if (given[temp] == 0) { // If the random card hasnt been given out

                            Counters.get("plum").addCard(getCard(cardList[temp])); //Adds the card to the player
                            given[temp] = 1; // Marks the card as given
                            track++; // Increments the track of the amount of cards given
                        }
                        temp = Random().nextInt(21);// Generates a new number
                    }
                    break;
                // We repeat these steps for the other counters
                case "White":
                    while (track < amount) {
                        if (given[temp] == 0) {
                            Counters.get("white").addCard(getCard(cardList[temp]));
                            given[temp] = 1;
                            track++;
                        }
                        temp = Random().nextInt(21);
                    }
                    break;
                case "Scarlet":
                    while (track < amount) {
                        if (given[temp] == 0) {
                            Counters.get("scarlet").addCard(getCard(cardList[temp]));
                            given[temp] = 1;
                            track++;
                        }
                        temp = Random().nextInt(21);
                    }
                    break;
                case "Green":
                    while (track < amount) {
                        if (given[temp] == 0) {
                            Counters.get("green").addCard(getCard(cardList[temp]));
                            given[temp] = 1;
                            track++;
                        }
                        temp = Random().nextInt(21);
                    }
                    break;
                case "Mustard":
                    while (track < amount) {
                        if (given[temp] == 0) {
                            Counters.get("mustard").addCard(getCard(cardList[temp]));
                            given[temp] = 1;
                            track++;
                        }
                        temp = Random().nextInt(21);
                    }
                    break;
                case "Peacock":
                    while (track < amount) {
                        if (given[temp] == 0) {
                            Counters.get("peacock").addCard(getCard(cardList[temp]));
                            given[temp] = 1;
                            track++;
                        }
                        temp = Random().nextInt(21);
                    }
                    break;
            }

            x++;
        }
        x = 0;
        SpareCards spares = new SpareCards();// Creates spare cards that everyone can see

        while (x < 21) {
            if (given[x] == 0) {// If any card is not given out by this point then it is put into SpareCards
                spares.addCard(getCard(cardList[x]));
            }
            x++;
        }
    }

    /**
     * Creates all of the cards
     */
    private void createCard() {
        cards.add(new Card("Mustard"));
        cards.add(new Card("Scarlet"));
        cards.add(new Card("Green"));
        cards.add(new Card("Peacock"));
        cards.add(new Card("White"));
        cards.add(new Card("Plum"));
        cards.add(new Card("Pistol"));
        cards.add(new Card("Dagger"));
        cards.add(new Card("Lead Pipe"));
        cards.add(new Card("Candlestick"));
        cards.add(new Card("Rope"));
        cards.add(new Card("Ball Room"));
        cards.add(new Card("Billiard Room"));
        cards.add(new Card("Wrench"));
        cards.add(new Card("Ball Room"));
        cards.add(new Card("Library"));
        cards.add(new Card("Hall"));
        cards.add(new Card("Conservatory"));
        cards.add(new Card("Billiard Room"));
        cards.add(new Card("Study"));
        cards.add(new Card("Lounge"));
        cards.add(new Card("Dining Room"));
        cards.add(new Card("Kitchen"));
    }
}
