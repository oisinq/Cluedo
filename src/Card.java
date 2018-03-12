/**
 * This represents a single card that is assigned to another object
 */
public class Card {
    private String cardName;
    private String type;
    private Boolean owned;

    Card(String cardName, String type, Boolean owned) {
        this.cardName = cardName;
        this.type = type;
        this.owned = owned;
    }

    public String getName() {
        return cardName;
    }

    public String getType() {
        return type;
    }

    public Boolean isOwned() {
        return owned;
    }

    /**
     * Checks if the entered name matches the card's name
     */
    public boolean hasCardName(String characterName) {
        return this.cardName.toLowerCase().equals(characterName.toLowerCase().trim());
    }
}