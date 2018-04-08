/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This creates and contains each of the individual rooms
 */
public class Rooms implements Iterable<Room>, Iterator<Room> {

    private static final ArrayList<Room> rooms = new ArrayList<>();
    private Iterator<Room> iterator;

    /**
     * This constructor creats each of the different rooms
     */
    Rooms() {
        createKitchen();
        createBallRoom();
        createConservatory();
        createDiningRoom();
        createCellar();
        createBilliardRoom();
        createLibrary();
        createLounge();
        createHall();
        createStudy();
    }

    /**
     * Returns the corresponding room to the entered name
     */
    public static Room get(String name) {
        for (Room r : rooms) {
            if (r.hasRoomName(name)) {
                return r;
            }
        }
        return null;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public Room next() {
        return iterator.next();
    }

    public Iterator<Room> iterator() {
        iterator = rooms.iterator();
        return iterator;
    }

    // The below methods create a room, add its entrances and counterSquares and adds it to the rooms arraylist

    private void createKitchen() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        ArrayList<Coordinates> counterSquares = new ArrayList<>();
        entrances.add(new Coordinates(4, 6));
        // We use (-1, -1) to signify a secret passageway - this is much easier than using the actual location, because we don't need to know the actual location
        // This helped us avoid a lot of bugs when moving rooms via secret passageways
        entrances.add(new Coordinates(-1, -1));
        setCounterSquares(2, 3, counterSquares);
        rooms.add(new Room("Kitchen", entrances, counterSquares));
    }

    private void createBallRoom() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        ArrayList<Coordinates> counterSquares = new ArrayList<>();
        entrances.add(new Coordinates(8, 5));
        entrances.add(new Coordinates(9, 7));
        entrances.add(new Coordinates(14, 7));
        entrances.add(new Coordinates(15, 5));
        setCounterSquares(11, 3, counterSquares);
        rooms.add(new Room("Ball Room", entrances, counterSquares));
    }

    private void createConservatory() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        ArrayList<Coordinates> counterSquares = new ArrayList<>();
        entrances.add(new Coordinates(18, 4));
        // We use (-1, -1) to signify a secret passageway - this is much easier than using the actual location, because we don't need to know the actual location
        // This helped us avoid a lot of bugs when moving rooms via secret passageways
        entrances.add(new Coordinates(-1, -1));
        setCounterSquares(20, 2, counterSquares);
        rooms.add(new Room("Conservatory", entrances, counterSquares));
    }

    private void createDiningRoom() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        ArrayList<Coordinates> counterSquares = new ArrayList<>();
        entrances.add(new Coordinates(7, 12));
        entrances.add(new Coordinates(6, 15));
        setCounterSquares(4, 11, counterSquares);
        rooms.add(new Room("Dining Room", entrances, counterSquares));
    }

    private void createCellar() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        ArrayList<Coordinates> counterSquares = new ArrayList<>();
        entrances.add(new Coordinates(12, 16));
        setCounterSquares(12, 13, counterSquares);
        rooms.add(new Room("Cellar", entrances, counterSquares));
    }

    private void createBilliardRoom() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        ArrayList<Coordinates> counterSquares = new ArrayList<>();
        entrances.add(new Coordinates(18, 9));
        entrances.add(new Coordinates(22, 12));
        setCounterSquares(19, 9, counterSquares);
        rooms.add(new Room("Billiard Room", entrances, counterSquares));
    }

    private void createLibrary() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        ArrayList<Coordinates> counterSquares = new ArrayList<>();
        entrances.add(new Coordinates(17, 16));
        entrances.add(new Coordinates(20, 14));
        setCounterSquares(19, 15, counterSquares);
        rooms.add(new Room("Library", entrances, counterSquares));
    }

    private void createLounge() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        ArrayList<Coordinates> counterSquares = new ArrayList<>();
        entrances.add(new Coordinates(6, 19));
        // We use (-1, -1) to signify a secret passageway - this is much easier than using the actual location, because we don't need to know the actual location
        // This helped us avoid a lot of bugs when moving rooms via secret passageways
        entrances.add(new Coordinates(-1, -1));
        setCounterSquares(3, 20, counterSquares);
        rooms.add(new Room("Lounge", entrances, counterSquares));
    }

    private void createHall() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        ArrayList<Coordinates> counterSquares = new ArrayList<>();
        entrances.add(new Coordinates(11, 18));
        entrances.add(new Coordinates(12, 18));
        entrances.add(new Coordinates(14, 20));
        setCounterSquares(11, 19, counterSquares);
        rooms.add(new Room("Hall", entrances, counterSquares));
    }

    private void createStudy() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        ArrayList<Coordinates> counterSquares = new ArrayList<>();
        entrances.add(new Coordinates(17, 21));
        // We use (-1, -1) to signify a secret passageway - this is much easier than using the actual location, because we don't need to know the actual location
        // This helped us avoid a lot of bugs when moving rooms via secret passageways
        entrances.add(new Coordinates(-1, -1));
        setCounterSquares(19, 22, counterSquares);
        rooms.add(new Room("Study", entrances, counterSquares));
    }

    /**
     * This creates a 3*2 rectangle of counterSquares in each room - this saves us from reusing code for each room
     */
    private void setCounterSquares(int colStartingGrid, int rowStartingGrid, ArrayList<Coordinates> counterSquares) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                counterSquares.add(new Coordinates(colStartingGrid + j, rowStartingGrid + i));
            }
        }
    }
}