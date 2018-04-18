import static java.lang.Thread.sleep;

public class Bot implements BotAPI {

    // The public API of Bot must not change
    // This is ONLY class that you can edit in the program
    // Rename Bot to the name of your team. Use camel case.
    // Bot may not alter the state of the board or the player objects
    // It may only inspect the state of the board and the player objects
    private PlayerAPI player;
    private MapAPI map;
    private DiceAPI dice;
    private LogAPI log;
    int i = 0;
    int j = 0;
    int movesLeft = 0;

    Bot (PlayerAPI player, MapAPI map, DiceAPI dice, LogAPI log) {
        this.player = player;
        this.map = map;
        this.dice = dice;
        this.log = log;
    }

    public String getName() {
        return "Bot";
    }

    /**
     * Commands are entered before and after you start moving aka before and after you roll
     * Once you roll, you can't enter a command until you stop moving
     */
    public String getCommand() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(findNearestDoor(player.getToken().getPosition(), 0));
        return "done";

        //green, mustard, white, scarlett
        //white, plum,mustard, green

        // Add your code here
//        if (i % 2 == 0) {
//            i++;
//            return "roll";
//        } else {
//            i++;
//            return "done";
//        }
    }

    /**
     * Enters your move
     */
    public String getMove() {
        Coordinates currentPosition = player.getToken().getPosition();

        if (movesLeft == 0) {
            movesLeft = dice.getTotal();
//            System.out.println(findNearestDoor(currentPosition, 0));
        }
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        return "lol";
    }

    /*
     These three methods are used to select the suspect, weapon and room in a questioning or an accusation
     */
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

    /*
    This lets you select a door in a room to leave through
     */
    public String getDoor() {
        // Add your code here
        return "1";
    }

    public String getCard(Cards matchingCards) {
        // Add your code here
        return matchingCards.get().toString();
    }

    private String findNearestDoor(Coordinates currentPosition, int steps) {
        if (map.isValidMove(currentPosition, "u")) {
            return "u" + findNearestDoor(map.getNewPosition(currentPosition, "u"), 1, "u");
        } else if (map.isValidMove(currentPosition, "r")) {
            return "r" + findNearestDoor(map.getNewPosition(currentPosition, "r"), 1, "r");
        } else if (map.isValidMove(currentPosition, "d")) {
            return "d" + findNearestDoor(map.getNewPosition(currentPosition, "d"), 1, "d");
        }  else if (map.isValidMove(currentPosition, "l")) {
            return "l" + findNearestDoor(map.getNewPosition(currentPosition, "l"), 1, "l");
        }
        else return "";
    }

    private String findNearestDoor(Coordinates currentPosition, int steps, String direction) {
        if (steps > 13) {
            return "LONG";
        }
        if (map.isValidMove(currentPosition, "u") && !direction.equals("d")) {
            if (map.isDoor(currentPosition, map.getNewPosition(currentPosition, "u"))) {
                return "u";
            }
            return "u" + findNearestDoor(map.getNewPosition(currentPosition, "u"), steps+1, "u");

        } else if (map.isValidMove(currentPosition, "r") && !direction.equals("l")) {
            if (map.isDoor(currentPosition, map.getNewPosition(currentPosition, "r"))) {
                return "r";
            }
            return "r" + findNearestDoor(map.getNewPosition(currentPosition, "r"), steps+1, "r");

        } else if (map.isValidMove(currentPosition, "d") && !direction.equals("u")) {
            if (map.isDoor(currentPosition, map.getNewPosition(currentPosition, "d"))) {
                return "d";
            }
            return "d" + findNearestDoor(map.getNewPosition(currentPosition, "d"), steps+1, "d");

        } else if (map.isValidMove(currentPosition, "l") && !direction.equals("r")) {
            if (map.isDoor(currentPosition, map.getNewPosition(currentPosition, "l"))) {
                return "l";
            }
            return "l" + findNearestDoor(map.getNewPosition(currentPosition, "l"), steps+1, "l");
        }
        return "x";
    }
}
