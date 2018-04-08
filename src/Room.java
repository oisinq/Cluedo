/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import java.util.ArrayList;

/**
 * These are the individual rooms on the board and stores information about their entrances and token squares
 */
public class Room {

    // We store these variables here to make it easier to move around the players on the board
    // "entrances" is a list of all of the locations of the entrances on the board
    // "tokenSquares" is the locations where a token can be placed inside a board
    private String roomName;
    private ArrayList<Coordinates> entrances;
    private ArrayList<Coordinates> tokenSquares;

    Room(String roomName, ArrayList<Coordinates> entrances, ArrayList<Coordinates> tokenSquares) {
        this.roomName = roomName;
        this.entrances = entrances;
        this.tokenSquares = tokenSquares;
    }

    public boolean hasRoomName(String counterName) {
        return this.roomName.toLowerCase().equals(counterName.toLowerCase().trim());
    }

    public ArrayList<Coordinates> getEntrances() {
        return entrances;
    }

    public ArrayList<Coordinates> getTokenSquares() {
        return tokenSquares;
    }

    public String getRoomName() {
        return roomName;
    }

}
