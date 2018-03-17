/*  Cluedo - Sprint 3
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

<<<<<<< HEAD
public class Cards {
    int[] Given = (new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});//Keeps track if a certain card has been given out

    private ArrayList<Card> cards = new ArrayList<>();

    String[] CardList = (new String[]{"Mustard", "Scarlet", "Green", "Peacock", "White", "Plum",
            "Pistol", "Dagger", "Lead Pipe", "Candle Stick", "Rope", "Wrench",
            "Ball Room", "Library", "Hall", "Conservatory", "Billiard Room", "Study", "Lounge", "Dining Room", "Kitchen"});// Contains all the card names

=======
import java.util.ArrayList;
import java.util.Random;

public class Cards {
    private String[] cardList = (new String[]{"Mustard", "Scarlet", "Green", "Peacock", "White", "Plum",
            "Pistol", "Dagger", "Lead Pipe", "Candlestick", "Rope", "Wrench",
            "Ball Room", "Library", "Hall", "Conservatory", "Billiard Room", "Study", "Lounge", "Dining Room", "Kitchen"});
    // This array tracks if a card has been assigned to a player/envelope/SpareCards yet
    private int[] given = (new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    private ArrayList<Card> cards = new ArrayList<>();
>>>>>>> 519c35b062bd74bb0cc8488aba6b3ef142bd219c

    Cards() {
        createCard();// Creates the cards
    }

<<<<<<< HEAD

    public Card getCard(String name) {// Finds the card if it is valid
=======
    /**
     * Returns the card matching the string name
     */
    private Card getCard(String name) {
>>>>>>> 519c35b062bd74bb0cc8488aba6b3ef142bd219c
        for (Card c : cards) {
            if (c.hasCardName(name)) {
                return c;
            }
        }
        return null;
    }

<<<<<<< HEAD

    public Random Random() {// Generates a random number
=======
    /**
     * Used to generate random numbers
     */
    private Random Random() {
>>>>>>> 519c35b062bd74bb0cc8488aba6b3ef142bd219c
        Random rand;
        rand = new Random();
        return rand;
    }

    /**
     * Inserts three cards into the Envelope class
     */
    public void Envelope() {
<<<<<<< HEAD
        String[] MurderFile = new String[3];
        int temp = Random().nextInt(6);// Generates a random number which applies to a person card
        MurderFile[0] = CardList[temp];// Stores the card
        Given[temp] = 1;// Declares the card as given out

        temp = Random().nextInt(6) + 6;//Generates a random number which applies to a weapon card
        MurderFile[1] = CardList[temp];
        Given[temp] = 1;
        temp = Random().nextInt(8) + 12;
        MurderFile[2] = CardList[temp];
        Given[temp] = 1;
        Envelope file = new Envelope(MurderFile[0], MurderFile[1], MurderFile[2]);
        file.addCard(getCard(MurderFile[0]));
        file.addCard(getCard(MurderFile[1]));
        file.addCard(getCard(MurderFile[2]));
=======
        Envelope file = new Envelope();

        int temp = Random().nextInt(6);
        file.setPerson(getCard(cardList[temp]));
        given[temp] = 1;

        temp = Random().nextInt(6) + 6;
        file.setWeapon(getCard(cardList[temp]));
        given[temp] = 1;
>>>>>>> 519c35b062bd74bb0cc8488aba6b3ef142bd219c

        temp = Random().nextInt(8) + 12;
        file.setRoom(getCard(cardList[temp]));
        given[temp] = 1;
    }

<<<<<<< HEAD
    public void CardHolder(String[] strArray, int amount, int number) {//Distributes the cards amoungst the players
=======
    /**
     * Assigns the cards randomly to the players, and assigns any leftover cars to SpareCards
     */
    public void CardHolder(String[] strArray, int amount, int number) {//Distributes the cards amongst the players
>>>>>>> 519c35b062bd74bb0cc8488aba6b3ef142bd219c

        int track = 0;// Counts through the amount of cards to be added to each player
        int x = 0;// Tracks the position in the string array of players
        int temp = Random().nextInt(21);// Creates a random number within the confines of the array of card names
        while (x < number) {// Counts to the amount of players
            track = 0;

<<<<<<< HEAD
            switch (strArray[x]) {//Switches depending on the player name found in the string array 
                case "Plum":
                    while (track < amount) {// Counts to the amount of cards each player should have 
                        if (Given[temp] == 0) {// If the random card hasnt been given out

                            Counters.get("plum").addCard(getCard(CardList[temp]));//Adds the card to the player
                            Given[temp] = 1;// Marks the card as given 
                            track++;// Increments the track of the amount of cards given
=======
            switch (strArray[x]) { //Switches depending on the player name found in the string array
                case "Plum":
                    while (track < amount) { // Counts to the amount of cards each player should have
                        if (given[temp] == 0) { // If the random card hasnt been given out

                            Counters.get("plum").addCard(getCard(cardList[temp])); //Adds the card to the player
                            given[temp] = 1; // Marks the card as given
                            track++; // Increments the track of the amount of cards given
>>>>>>> 519c35b062bd74bb0cc8488aba6b3ef142bd219c
                        }
                        temp = Random().nextInt(21);// Generates a new number
                    }
                    break;
                    // We repeat these steps for the other counters
                case "White":
                    while (track < amount) {
<<<<<<< HEAD

                        if (Given[temp] == 0) {
                            Counters.get("white").addCard(getCard(CardList[temp]));
                            Given[temp] = 1;
=======
                        if (given[temp] == 0) {
                            Counters.get("white").addCard(getCard(cardList[temp]));
                            given[temp] = 1;
>>>>>>> 519c35b062bd74bb0cc8488aba6b3ef142bd219c
                            track++;
                        }
                        temp = Random().nextInt(21);
                    }
                    break;
                case "Scarlet":
                    while (track < amount) {
<<<<<<< HEAD
                        if (Given[temp] == 0) {
                            Counters.get("scarlet").addCard(getCard(CardList[temp]));
                            Given[temp] = 1;
=======
                        if (given[temp] == 0) {
                            Counters.get("scarlet").addCard(getCard(cardList[temp]));
                            given[temp] = 1;
>>>>>>> 519c35b062bd74bb0cc8488aba6b3ef142bd219c
                            track++;
                        }
                        temp = Random().nextInt(21);
                    }
                    break;
                case "Green":
                    while (track < amount) {
<<<<<<< HEAD
                        if (Given[temp] == 0) {
                            Counters.get("green").addCard(getCard(CardList[temp]));
                            Given[temp] = 1;
=======
                        if (given[temp] == 0) {
                            Counters.get("green").addCard(getCard(cardList[temp]));
                            given[temp] = 1;
>>>>>>> 519c35b062bd74bb0cc8488aba6b3ef142bd219c
                            track++;
                        }
                        temp = Random().nextInt(21);
                    }
                    break;
                case "Mustard":
                    while (track < amount) {
<<<<<<< HEAD
                        if (Given[temp] == 0) {
                            Counters.get("mustard").addCard(getCard(CardList[temp]));
                            Given[temp] = 1;
=======
                        if (given[temp] == 0) {
                            Counters.get("mustard").addCard(getCard(cardList[temp]));
                            given[temp] = 1;
>>>>>>> 519c35b062bd74bb0cc8488aba6b3ef142bd219c
                            track++;
                        }
                        temp = Random().nextInt(21);
                    }
                    break;
                case "Peacock":
                    while (track < amount) {
<<<<<<< HEAD
                        if (Given[temp] == 0) {
                            Counters.get("peacock").addCard(getCard(CardList[temp]));
                            Given[temp] = 1;
=======
                        if (given[temp] == 0) {
                            Counters.get("peacock").addCard(getCard(cardList[temp]));
                            given[temp] = 1;
>>>>>>> 519c35b062bd74bb0cc8488aba6b3ef142bd219c
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
<<<<<<< HEAD
            if (Given[x] == 0) {// If any card is not given out by this point then it is put into SpareCards
                spares.addCard(getCard(CardList[x]));
=======
            if (given[x] == 0) {// If any card is not given out by this point then it is put into SpareCards
                spares.addCard(getCard(cardList[x]));
>>>>>>> 519c35b062bd74bb0cc8488aba6b3ef142bd219c
            }
            x++;
        }
    }

    /**
     * Creates all of the cards
     */
    private void createCard() {
        cards.add(new Card("Mustard", "Person", false));
        cards.add(new Card("Scarlet", "Person", false));
        cards.add(new Card("Green", "Person", false));
        cards.add(new Card("Peacock", "Person", false));
        cards.add(new Card("White", "Person", false));
        cards.add(new Card("Plum", "Person", false));
        cards.add(new Card("Pistol", "Weapon", false));
        cards.add(new Card("Dagger", "Weapon", false));
        cards.add(new Card("Lead Pipe", "Weapon", false));
        cards.add(new Card("Candlestick", "Weapon", false));
        cards.add(new Card("Rope", "Weapon", false));
        cards.add(new Card("Ballroom", "Room", false));
        cards.add(new Card("Billiard Room", "Room", false));
        cards.add(new Card("Wrench", "Weapon", false));
        cards.add(new Card("Ball Room", "Room", false));
        cards.add(new Card("Library", "Room", false));
        cards.add(new Card("Hall", "Room", false));
        cards.add(new Card("Conservatory", "Room", false));
        cards.add(new Card("Billiard Room", "Room", false));
        cards.add(new Card("Study", "Room", false));
        cards.add(new Card("Lounge", "Room", false));
        cards.add(new Card("Dining Room", "Room", false));
        cards.add(new Card("Kitchen", "Room", false));
    }
}
