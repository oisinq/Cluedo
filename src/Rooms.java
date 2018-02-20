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
        entrances.add(new Coordinates(4, 6));
        rooms.add(new Room("Kitchen", entrances));
    }

    private void createBallRoom() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        entrances.add(new Coordinates(8, 5));
        entrances.add(new Coordinates(9, 7));
        entrances.add(new Coordinates(14, 7));
        entrances.add(new Coordinates(15, 5));
        rooms.add(new Room("Ball Room", entrances));
    }

    private void createConservatory() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        entrances.add(new Coordinates(18, 4));
        rooms.add(new Room("Conservatory", entrances));
    }

    private void createDiningRoom() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        entrances.add(new Coordinates(7, 12));
        entrances.add(new Coordinates(6, 15));
        rooms.add(new Room("Dining Room", entrances));
    }

    private void createCellar() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        entrances.add(new Coordinates(12, 16));
        rooms.add(new Room("Cellar", entrances));
    }

    private void createBilliardRoom() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        entrances.add(new Coordinates(18, 9));
        entrances.add(new Coordinates(22, 12));
        rooms.add(new Room("Billiard Room", entrances));
    }

    private void createLibrary() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        entrances.add(new Coordinates(20, 14));
        entrances.add(new Coordinates(17, 16));
        rooms.add(new Room("Library", entrances));
    }

    private void createLounge() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        entrances.add(new Coordinates(6, 19));
        rooms.add(new Room("Lounge", entrances));
    }

    private void createHall() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        entrances.add(new Coordinates(11, 18));
        entrances.add(new Coordinates(12, 18));
        entrances.add(new Coordinates(14, 20));
        rooms.add(new Room("Hall", entrances));
    }

    private void createStudy() {
        ArrayList<Coordinates> entrances = new ArrayList<>();
        entrances.add(new Coordinates(17, 21));
        rooms.add(new Room("Study", entrances));
    }
}