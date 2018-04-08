/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

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