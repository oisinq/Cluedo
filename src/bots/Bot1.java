package bots;

import gameengine.*;

import java.util.HashMap;

public class Bot1 implements BotAPI {

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
    private int movesLeft;
    private boolean usedPassage = false;
    private boolean rolled = false;
    private int i = 0;
    private int l = 0;
    private boolean firstTurn = true;
    private String path;
    private HashMap<String, HashMap <String, String>> pathways = new HashMap<>();

    public Bot1 (Player player, PlayersInfo playersInfo, Map map, Dice dice, Log log, Deck deck) {
        this.player = player;
        this.playersInfo = playersInfo;
        this.map = map;
        this.dice = dice;
        this.log = log;
        this.deck = deck;

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

    public String getName() {
        return "Bot1"; // must match the class name
    }

    public String getCommand() {
        if (rolled) {
            rolled = false;
            return "done";
        }
        if (usedPassage) {
            usedPassage = false;
            if (l % 2 == 0) {
                l++;
                return "done";
            }
            return "roll";
        }
        if (player.getToken().isInRoom()) {
            if (player.getToken().getRoom().toString().equals("Cellar")) return "done";
            String room = player.getToken().getRoom().toString();
            HashMap<String, String> roomMap = pathways.get(room);
            path = roomMap.get("Cellar");
            i++;
            if(player.getToken().isInRoom() && path.length() != 0 && path.charAt(0) == 'p') {
                path = path.substring(1, path.length());
                usedPassage = true;
                return "passage";
            }
            return "roll";
        }
        // Add your code here
        if (!rolled) {
            rolled = true;
            return "roll";
        } else {
            return "done";
        }
    }

    public String getMove() {
        try {
            Thread.sleep(50);
        } catch(Exception e) {
            throw new RuntimeException("jsjd");
        }

        if (firstTurn) {
            findFirstPath();
            movesLeft = dice.getTotal();
            firstTurn = false;
        }

        String move = path.substring(0, 1);
        path = path.substring(1, path.length());

        movesLeft--;
        return move;
    }

    public String getSuspect() {
        // Add your code here
        return Names.SUSPECT_NAMES[0];
    }

    public String getWeapon() {
        // Add your code here
        return Names.WEAPON_NAMES[0];
    }

    public String getRoom() {
        // Add your code here
        return Names.ROOM_NAMES[0];
    }

    public String getDoor() {
        String move = path.substring(0, 1);
        path = path.substring(1, path.length());

        movesLeft--;
        // Add your code here
        return move;
    }

    public String getCard(Cards matchingCards) {
        // Add your code here
        return matchingCards.get().toString();
    }

    public void notifyResponse(Log response) {
        // Add your code here
    }

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

    private String moveIntoRoom() {
        Coordinates currentPosition = player.getToken().getPosition();
        if (map.isDoor(currentPosition, map.getNewPosition(player.getToken().getPosition(), "u"))) {
            movesLeft = 0;
            return "u";
        } else if (map.isDoor(currentPosition, map.getNewPosition(player.getToken().getPosition(), "r"))) {
            movesLeft = 0;
            return "r";
        } else if (map.isDoor(currentPosition, map.getNewPosition(player.getToken().getPosition(), "d"))) {
            movesLeft = 0;
            return "d";
        } else if (map.isDoor(currentPosition, map.getNewPosition(player.getToken().getPosition(), "l"))) {
            movesLeft = 0;
            return "l";
        }
        return "x";
    }
}
