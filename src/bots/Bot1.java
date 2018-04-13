package bots;

import gameengine.*;

import static java.lang.Thread.sleep;

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
    private int i = 0;
    private boolean firstTurn = true;
    private String path;

    public Bot1 (Player player, PlayersInfo playersInfo, Map map, Dice dice, Log log, Deck deck) {
        this.player = player;
        this.playersInfo = playersInfo;
        this.map = map;
        this.dice = dice;
        this.log = log;
        this.deck = deck;
    }

    public String getName() {
        return "Bot1"; // must match the class name
    }

    public String getCommand() {
    //    System.out.println(findNearestDoor(player.getToken().getPosition(), 0));

        //green, mustard, white, scarlett
        //white, plum,mustard, green

        // Add your code here
        if (i % 2 == 0) {
            i++;
            return "roll";
        } else {
            i++;
            return "done";
        }
    }

    public String getMove() {
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        // Add your code here
        return "1";
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
