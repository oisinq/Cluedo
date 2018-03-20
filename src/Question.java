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
        command = command.toLowerCase();
        if (counter == null) {
            selectCounter(command.toLowerCase());
            if (counter != null) {
                frame.appendText("Enter the weapon to question:");
            } else {
                frame.appendText("Invalid input. Please try again!");
            }
        } else if (weapon == null) {
            selectWeapon(command.toLowerCase());
            if (weapon != null) {
                frame.appendText("You have accused " + counter.getCharacterName() + " of committing a murder with the " +  weapon.getName() + " in the "+ room.getRoomName());
            } else {
                frame.appendText("Invalid input. Please try again!");
            }
        }
    }

    //TODO - We probably don't need a switch statement here - get() should return null if the counter doesn't exist
    private void selectCounter(String person) {
        counter = Counters.get(person.substring(0, 1).toUpperCase() + person.substring(1));
    }

    private void selectWeapon(String weaponName) {
        weapon = Weapons.get(weaponName.toLowerCase());
    }
}
