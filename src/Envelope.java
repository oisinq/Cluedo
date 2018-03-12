/**
 * The envelope contains the cards that are held in the murder envelope
 */
public class Envelope {
    private static Card person;
    private static Card weapon;
    private static Card room;

    Envelope() {
    }

    public static Card getPerson() {
        return person;
    }

    public void setPerson(Card c) {
        person = c;
    }

    public static Card getWeapon() {
        return weapon;
    }

    public void setWeapon(Card c) {
        weapon = c;
    }

    public static Card getRoom() {
        return room;
    }

    public void setRoom(Card c) {
        room = c;
    }
}