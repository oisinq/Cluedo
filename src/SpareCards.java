import java.util.ArrayList;

public class SpareCards  {

    private int xLocation, yLocation;

    private ArrayList<Card> cards = new ArrayList<>();
   
   
    SpareCards() {
    	
    }

    
    public void addCard(Card c)
    {
    	cards.add(c);
    }
}