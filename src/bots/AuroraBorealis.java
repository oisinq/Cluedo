package bots;

import gameengine.*;
import gameengine.Map;

import java.util.*;

public class AuroraBorealis implements BotAPI {

    // The public API of Bot must not change
    // This is ONLY class that you can edit in the program
    // Rename Bot to the name of your team. Use camel case.
    // Bot may not alter the state of the board or the player objects
    // It may only inspect the state of the board and the player objects

    private Player player;
    private PlayersInfo playersInfo;
    private Map map;
    private Dice dice;
    private Log log;
    private Deck deck;

    private boolean usedPassage = false;
    private boolean rolled = false;
    private int i = 0;
    private int l = 0;
    private boolean firstTurn = true;
    private String path;
    private HashMap<String, HashMap <String, String>> pathways = new HashMap<>();
    private Notes notes;
    private boolean startOfTurn = true;
    private boolean askedQuestion = false;


    public AuroraBorealis (Player player, PlayersInfo playersInfo, Map map, Dice dice, Log log, Deck deck) {
        this.player = player;
        this.playersInfo = playersInfo;
        this.map = map;
        this.dice = dice;
        this.log = log;
        this.deck = deck;

        // This is used to store the pathway to get from one room to another
        initialisePathways();
        notes = new Notes();
    }

    public String getName() {

        return "AuroraBorealis"; // must match the class name
    }

    public String getCommand() {
        if (startOfTurn) {
            startOfTurn = false;
        }

        if(player.getToken().isInRoom()) {
            if (notes.hasCardsRemaining(3)) {
                path = pathways.get(player.getToken().getRoom().toString()).get("Cellar");
                if (i % 3 == 0) {
                    i++;
                    return "done";
                } else if (i % 3 == 1) {
                    i++;
                    return "roll";
                } else {
                    i++;
                    return "accuse";
                }
            } else {
                pickNextRoom();
            }
        }

        // If you've rolled already, then the move must be completed, so type done
        if (rolled) {
            if (player.getToken().isInRoom()) {
                if (!askedQuestion) {
                    askedQuestion = true;
                    return "question";
                } else {
                    askedQuestion = false;
                }
            }
            rolled = false;
            startOfTurn = true;
            return "done";
        }
        // If you used a passageway, then we need to leave the new room
        if (usedPassage) {
            usedPassage = false;
            // We can't leave the room straight away though, so I used this if statement to end your turn once,
            // then return "roll" next time around
            if (l % 2 == 0) {
                l++;
                startOfTurn = true;
                rolled = false;
                return "done";
            }
            rolled = true;
            return "roll";
        }

        // If you're in a room, we need to find the next pathway
        if (player.getToken().isInRoom()) {
            // If you're in the cellar, we'll return done for now
            if (player.getToken().getRoom().toString().equals("Cellar")) {
                startOfTurn = true;
                rolled = false;
                return "done";
            }
            i++;

            // If the first character in the path is 'p', we want to use the passageway
            if(player.getToken().isInRoom() && path.length() != 0 && path.charAt(0) == 'p') {
                path = path.substring(1, path.length());
                usedPassage = true;
                return "passage";
            }
            // Otherwise, we want to start moving
            rolled = true;
            return "roll";
        }
        // If we haven't rolled yet, we want to. Otherwise, we're done.
        if (!rolled) {
            rolled = true;
            return "roll";
        } else {
            startOfTurn = true;
            rolled = false;
            return "done";
        }
    }

    public String getMove() {
        try {
            Thread.sleep(50);
        } catch(Exception e) {
            throw new RuntimeException("sleep machine broke");
        }

        // If it's your first turn, we find the path to the closest room
        if (firstTurn) {
            findFirstPath();
            // I'm doing this here because I know the deck is sorted at this stage
            notes.addOwnedCards();
            notes.addSharedCards();
            firstTurn = false;
        }

        // We take the first character from the path string as the move, and remove it from the start of path
        String move = path.substring(0, 1);
        path = path.substring(1, path.length());

        return move;
    }

    public String getSuspect() {
        // Add your code here
        if (notes.ownsCard(player.getToken().getRoom().toString())) {
            if (notes.hasEverySuspect()) {
                return notes.getOwnedPlayer();
            }
            return notes.getUnseenPlayer();
        }
        if (notes.hasEverySuspect()) {
            return notes.getOwnedPlayer();
        }
        return notes.getUnseenPlayer();
    }

    public String getWeapon() {
        if (notes.hasEverySuspect()) {
            return notes.getOwnedWeapon();
        }
        // Add your code here
        return notes.getUnseenWeapon();
    }

    public String getRoom() {
        // Add your code here
        return notes.getOwnedRoom();
    }

    public String getDoor() {
        // When this is triggered, the door will be the first character in path
        String move = path.substring(0, 1);
        path = path.substring(1, path.length());

        // Add your code here
        return move;
    }

    public String getCard(Cards matchingCards) {
        // Add your code here
        return matchingCards.get().toString();
    }

    /**
     * Tells you the response to a question you've asked
     */
    public void notifyResponse(Log response) {
        // Add your code here
       for (String s : response) {
           if (s.contains("showed one card:")) {
               System.out.println("aaah!");
               String[] split = s.split(" ");
               String card = split[split.length -1];
               card = card.substring(0, card.length()-1);
               System.out.println(card);
               notes.addSeenCard(card);
           }
       }
        System.out.println(notes.getNotesString());
    }

    /**
     * Finds the first pathway for the counter
     */
    private void findFirstPath() {
        Coordinates currentPosition = player.getToken().getPosition();
        int currRow = currentPosition.getRow();
        int currCol = currentPosition.getCol();

        Coordinates white = new Coordinates(9, 0);
        Coordinates green = new Coordinates(14, 0);
        Coordinates peacock = new Coordinates(23, 6);
        Coordinates scarlet = new Coordinates(7, 24);
        Coordinates plum = new Coordinates(23, 19);
        Coordinates mustard = new Coordinates(0, 17);

        if (currRow == scarlet.getRow() && currCol == scarlet.getCol()) {
            path = "uuuuuuld";
        } else if (currRow == white.getRow() && currCol == white.getCol()) {
            path = "dllddddr";
        } else if (currRow == green.getRow() && currCol == green.getCol()) {
            path = "drrddddl";
        } else if (currRow == peacock.getRow() && currCol == peacock.getCol()) {
            path = "llllluu";
        } else if (currRow == plum.getRow() && currCol == plum.getCol()) {
            path = "lllllldd";
        } else if (currRow == mustard.getRow() && currCol == mustard.getCol()) {
            path = "rrrrrruu";
        }
    }

    /**
     * Adds all the possible pathways to the hashmap
     */
    private void initialisePathways() {
        HashMap<String, String> kitchen = new HashMap<>();
        kitchen.put("Dining Room", "1ddrdrrrdddl");
        kitchen.put("Ballroom", "1drrruur");
        kitchen.put("Study", "p");
        kitchen.put("Cellar", "puuullulllu");

        HashMap<String, String> ballRoom = new HashMap<>();
        ballRoom.put("Kitchen", "1lddlllu");
        ballRoom.put("Conservatory", "4rrru");
        ballRoom.put("Cellar", "2ddddddddddrrru");

        HashMap<String, String> conservatory = new HashMap<>();
        conservatory.put("Ballroom", "1dlll");
        conservatory.put("Billiard Room", "1dddlddr");
        conservatory.put("Lounge", "p");
        conservatory.put("Cellar", "puurrrrrru");

        HashMap<String, String> billiardRoom = new HashMap<>();
        billiardRoom.put("Ballroom", "1luuuull");
        billiardRoom.put("Conservatory", "1luuuuru");
        billiardRoom.put("Library", "2dlld");
        billiardRoom.put("Cellar", "1ldddddlldddlllu");

        HashMap<String, String> library = new HashMap<>();
        library.put("Billiard Room", "2urru");
        library.put("Hall", "1lldllld");
        library.put("Study", "1lddddrd");
        library.put("Cellar", "1lldlllu");

        HashMap<String, String> study = new HashMap<>();
        study.put("Kitchen", "p");
        study.put("Library", "1uuuluur");
        study.put("Hall", "1ulll");
        study.put("Cellar", "1uuulullllu");

        HashMap<String, String> hall = new HashMap<>();
        hall.put("Lounge", "1ullldlld");
        hall.put("Dining Room", "1ullllluu");
        hall.put("Study", "3rrrd");
        hall.put("Library", "3ruuurur");
        hall.put("Cellar", "2uu");

        HashMap<String, String> lounge = new HashMap<>();
        lounge.put("Conservatory", "p");
        lounge.put("Dining Room", "1uuuu");
        lounge.put("Hall", "1uurrrrrd");
        lounge.put("Cellar", "1uurrrrrru");

        HashMap<String, String> diningRoom = new HashMap<>();
        diningRoom.put("Lounge", "1dddd");
        diningRoom.put("Ballroom", "2rruuuuu");
        diningRoom.put("Hall", "1ddrrrrrd");
        diningRoom.put("Kitchen", "2ruuullluluu");
        diningRoom.put("Cellar", "1ddrrrrrru");

        pathways.put("Kitchen", kitchen);
        pathways.put("Ballroom", ballRoom);
        pathways.put("Conservatory", conservatory);
        pathways.put("Billiard Room", billiardRoom);
        pathways.put("Library", library);
        pathways.put("Study", study);
        pathways.put("Hall", hall);
        pathways.put("Lounge", lounge);
        pathways.put("Dining Room", diningRoom);
    }

    /**
     * This is the notes class from our own version of Cluedo - we think it'll make tracking cards easier
     */
    private class Notes {

        private LinkedHashMap<String, String> values;

        Notes() {
            // I'm using a LinkedHashMap instead of a Map because I want to print off the values in the order they were added later
            // This makes it much easier to read when printing
            // I'm using a map because I think it's the best data structure to store each card and its corresponding status
            values = new LinkedHashMap<>();

            // I don't need to separate these strings, but I think it's easier to understand what's happening.
            // I may also need these separated later on
            String players[] = {"Green", "Mustard", "Peacock", "Plum", "Scarlett", "White"};
            String weapons[] = {"Wrench", "Candlestick", "Dagger", "Pistol", "Lead Pipe", "Rope"};
            String rooms[] = {"Kitchen", "Ballroom", "Conservatory", "Dining Room", "Billiard Room", "Library", "Lounge", "Hall", "Study"};

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
        public void addSharedCards() {
            for (Card c : deck.getSharedCards()) {
                values.put(c.toString(), "A");
            }
        }

        public void addOwnedCards() {
            for (Card c : player.getCards()) {
                values.put(c.toString(), "X");
            }
        }

        /**
         * If we find a card in questioning, it's added here
         */
        public void addSeenCard(String cardName) {
            values.put(cardName, "V");
        }

        public LinkedHashMap<String, String> getValues() {
            return values;
        }

        public String getNotesString() {
            StringBuilder s = new StringBuilder();
            int i = 0;

            String title ="Notes";
            s.append(title);
            for (HashMap.Entry<String, String> entry : notes.values.entrySet()) {
                if (i == 0) {
                    s.append("\nPlayers:\n");
                } else if (i == 6) {
                    s.append("\nWeapons:\n");
                } else if (i == 12) {
                    s.append("\nRooms:\n");
                }
                String key = entry.getKey();
                String value = entry.getValue();
                s.append(String.format("%-14s -> %3s\n", key.trim(), value.trim()));
                i++;
            }

            return s.toString();
        }

        private boolean ownsCard(String cardName) {
            return values.get(cardName).equals("X");
        }

        private String getUnseenPlayer() {
            int loopCount = 0;
            Random rand = new Random();
            boolean found = false;
            String player = "";
            while (!found) {
                player = Names.SUSPECT_NAMES[rand.nextInt(6)];
                if (values.get(player).equals(" ")) {
                    found = true;
                }
                loopCount++;
                if (loopCount > 1000) {
                    System.out.println(notes.getNotesString());
                    throw new RuntimeException("Can't find it!");
                }
            }
            return player;
        }

        private String getOwnedPlayer() {
            int loopCount = 0;
            Random rand = new Random();
            boolean found = false;
            String player = "";
            while (!found) {
                player = Names.SUSPECT_NAMES[rand.nextInt(6)];
                if (values.get(player).equals("X")) {
                    found = true;
                }
                if (loopCount > 1000) {
                    System.out.println(notes.getNotesString());
                    throw new RuntimeException("Can't find it!");
                }
                loopCount++;
            }
            return player;
        }

        private String getUnseenWeapon() {
            int loopCount = 0;
            Random rand = new Random();
            boolean found = false;
            String weapon = "";
            while (!found) {
                weapon = Names.WEAPON_NAMES[rand.nextInt(6)];
                if (values.get(weapon).equals(" ")) {
                    found = true;
                }
                if (loopCount > 1000) {
                    System.out.println(notes.getNotesString());
                    throw new RuntimeException("Can't find it!");
                }
                loopCount++;
            }
            return weapon;
        }

        private String getOwnedWeapon() {
            int loopCount = 0;
            Random rand = new Random();
            boolean found = false;
            String weapon = "";
            while (!found) {
                weapon = Names.WEAPON_NAMES[rand.nextInt(6)];
                if (values.get(weapon).equals("X")) {
                    found = true;
                }
                if (loopCount > 1000) {
                    System.out.println(notes.getNotesString());
                    throw new RuntimeException("Can't find it!");
                }
                loopCount++;
            }
            return weapon;
        }

        private String getUnseenRoom() {
            int loopCount = 0;
            Random rand = new Random();
            boolean found = false;
            String room = "";
            while (!found) {
                room = Names.ROOM_NAMES[rand.nextInt(9)];
                if (values.get(room).equals(" ")) {
                    found = true;
                }
                if (loopCount > 1000) {
                    System.out.println(notes.getNotesString());
                    throw new RuntimeException("Can't find it!");
                }
                loopCount++;
            }
            return room;
        }

        private String getOwnedRoom() {
            int loopCount = 0;
            Random rand = new Random();
            boolean found = false;
            String room = "";
            while (!found) {
                room = Names.ROOM_NAMES[rand.nextInt(9)];
                if (values.get(room).equals("X")) {
                    found = true;
                }
                if (loopCount > 1000) {
                    System.out.println(notes.getNotesString());
                    throw new RuntimeException("Can't find it!");
                }
                loopCount++;
            }
            return room;
        }

        private boolean hasCardsRemaining(int requiredNumCards) {
            int numCards = 0;
            for (HashMap.Entry<String, String> entry : notes.values.entrySet()) {
                if (entry.getValue().equals(" ")) {
                    numCards++;
                }
            }
            return requiredNumCards == numCards;
        }

        private boolean hasEverySuspect() {
            for (int i = 0; i < 6; i++) {
                if (values.get(Names.SUSPECT_NAMES[i]).equals(" ")) {
                    return false;
                }
            }
            return true;
        }

        private boolean hasEveryWeapon() {
            for (int i = 0; i < 6; i++) {
                if (values.get(Names.WEAPON_NAMES[i]).equals(" ")) {
                    return false;
                }
            }
            return true;
        }

        private boolean hasEveryRoom() {
            for (int i = 0; i < 9; i++) {
                if (values.get(Names.ROOM_CARD_NAMES[i]).equals(" ")) {
                    return false;
                }
            }
            return true;
        }
    }

    private void pickNextRoom() {
        Random rand = new Random();
        boolean found = false;
        String randomRoom = "";
        while (!found) {
            randomRoom = Names.ROOM_NAMES[rand.nextInt(9)];
            if (pathways.get(player.getToken().getRoom().toString()).containsKey(randomRoom)) {
                found = true;
            }
        }
        path = pathways.get(player.getToken().getRoom().toString()).get(randomRoom);
    }
}
