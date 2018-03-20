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

    public boolean interpretInput(String command) {
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
                counter.setCurrentRoom(room);
                frame.appendText("You have accused " + counter.getCharacterName() + " of committing a murder with the " +  weapon.getName() + " in the "+ room.getRoomName());
                return false;
            } else {
                frame.appendText("Invalid input. Please try again!");
            }
        }
        return true;
    }

    //TODO - We probably don't need a switch statement here - get() should return null if the counter doesn't exist
    private void selectCounter(String person) {
        counter = Counters.get(person.substring(0, 1).toUpperCase() + person.substring(1));
    }

    private void selectWeapon(String weaponName) {
        weapon = Weapons.get(weaponName.toLowerCase());
    }

    public Room getRoom() {
        return room;
    }

    public Counter getCounter() {
        return counter;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
