/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

/**
 * This represents a single card that is assigned to another object
 */
public class Card {
    private String cardName;

    Card(String cardName) {
        this.cardName = cardName;
    }

    public String getName() {
        return cardName;
    }

    /**
     * Checks if the entered name matches the card's name
     */
    public boolean hasCardName(String characterName) {
        return this.cardName.toLowerCase().equals(characterName.toLowerCase().trim());
    }
}