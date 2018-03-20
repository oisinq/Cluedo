import javax.swing.*;

public class Question {
    private GUI frame;
    private Room room;
    private Counter counter;
    private  Weapon weapon;

    Question(Room room, GUI frame) {
        this.room = room;
        this.frame = frame;
        frame.appendText("Enter the person to question:");
    }

    public void interpretInput(String command) {
        if (counter == null) {
            selectCounter(command.toLowerCase());
            if (counter != null) {
                frame.appendText("Enter the weapon to question:");
            }
        } else if (weapon == null) {
            selectWeapon(command.toLowerCase());
            if (weapon != null) {
                frame.appendText("You have accused " + counter.getCharacterName() + " of committing a murder with the " +  weapon.getName() + " in the "+ room.getRoomName());
            }
        }
    }

    //TODO - We probably don't need a switch statement here - get() should return null if the counter doesn't exist
    private void selectCounter(String person) {
        switch (person) {
            case "scarlet":
                counter = Counters.get("Scarlet");
                break;
            case "mustard":
                counter = Counters.get("Mustard");
                break;
            case "peacock":
                counter = Counters.get("Peacock");
                break;
            case "green":
                counter = Counters.get("Green");
                break;
            case "white":
                counter = Counters.get("White");
                break;
            case "plum":
                counter = Counters.get("Plum");
                break;
            default:
                frame.appendText("Invalid entry. Please try again!");
        }
    }

    private void selectWeapon(String weaponName) {
        switch (weaponName) {
            case "dagger":
                weapon = Weapons.get(weaponName.toLowerCase());
                break;
            case "pistol":
                weapon = Weapons.get(weaponName.toLowerCase());
                break;
            case "leadpipe":
                weapon = Weapons.get(weaponName.toLowerCase());
                break;
            case "wrench":
                weapon = Weapons.get(weaponName.toLowerCase());
                break;
            case "candlestick":
                weapon = Weapons.get(weaponName.toLowerCase());
                break;
            case "rope":
                weapon = Weapons.get(weaponName.toLowerCase());
                break;
            default:
                frame.appendText("Invalid entry. Please try again!");
        }
    }
}
