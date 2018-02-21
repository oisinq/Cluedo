import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class Rooms implements Iterable<Room>, Iterator<Room> {

    private static final ArrayList<Room> rooms = new ArrayList<>();
    private Iterator<Room> iterator;

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

    public static Room get(String name) {
        for (Room c : rooms) {
            if (c.hasRoomName(name)) {
                return c;
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

    private void createKitchen() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        ArrayList<Coordinates> counterSquares = new ArrayList<>();
        entrances.add(new Coordinates(4, 6));
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
        entrances.add(new Coordinates(20, 14));
        entrances.add(new Coordinates(17, 16));
        setCounterSquares(19, 15, counterSquares);
        rooms.add(new Room("Library", entrances, counterSquares));
    }

    private void createLounge() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        ArrayList<Coordinates> counterSquares = new ArrayList<>();
        entrances.add(new Coordinates(6, 19));
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
        setCounterSquares(19, 22, counterSquares);
        rooms.add(new Room("Study", entrances, counterSquares));
    }

    private void setCounterSquares(int colStartingGrid, int rowStartingGrid, ArrayList<Coordinates> counterSquares) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 2; j++) {
                counterSquares.add(new Coordinates(colStartingGrid + j, rowStartingGrid + i));
            }
        }
    }
}