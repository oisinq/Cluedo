/*  Cluedo - Sprint 1
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Each counter represents a player on the board
 */
public class Room {

    // We store these variables here to make it easier to move around the players on the board
    private String roomName;
    private ArrayList<Coordinates> entrances;

    Room(String roomName, ArrayList entrances) {
        this.roomName=roomName;
        this.entrances = entrances;
    }

    public boolean hasRoomName(String counterName) {
        return this.roomName.toLowerCase().equals(counterName.toLowerCase().trim());
    }

    public ArrayList<Coordinates> getEntrances() {
        return entrances;
    }
}
