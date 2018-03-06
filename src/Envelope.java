import java.util.ArrayList;

public class Envelope  {

    private int xLocation, yLocation;

    private ArrayList<Card> cards = new ArrayList<>();
    private String person;
    private String weapon;
    private String room;
   
    Envelope(String person, String weapon, String room) {
    	this.person=person;
    	this.weapon=weapon;
    	this.room=room;
    }

    public String getPerson() {
        return person;
    }
    public String getWeapon() {
        return weapon;
    }
    public String getRoom() {
        return room;
    }
    public void addCard(Card c)
    {
    	cards.add(c);
    }
}