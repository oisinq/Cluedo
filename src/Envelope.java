import java.util.ArrayList;

public class Envelope  {

    private int xLocation, yLocation;

    public static ArrayList<Card> cards = new ArrayList<>();
    private static Card person;
    private static Card weapon;
    private static Card room;
   
    Envelope() {

    }

    public void setPerson(Card c) {
    	person = c;
    }

    public void setWeapon(Card c) {
        weapon = c;
    }

    public void setRoom(Card c) {
        room = c;
    }

    public static Card getPerson() {
        return person;
    }

    public static Card getWeapon() {
        return weapon;
    }

    public static Card getRoom() {
        return room;
    }
}