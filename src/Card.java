
public class Card  {

    private int xLocation, yLocation;
    // 43, 50 is top of grid
  
    private String cardName;
    private String Type;
    private Boolean Owned;

    Card(String cardName, String Type, Boolean Owned) {
    	this.cardName=cardName;
    	this.Type=Type;
    	this.Owned=Owned;
    }

    public String getName() {
        return cardName;
    }
    public String getType() {
        return Type;
    }

    public Boolean isOwned() {
        return Owned;
    }
    public boolean hasCardName(String characterName) {
        return this.cardName.toLowerCase().equals(characterName.toLowerCase().trim());
    }
}