import javax.swing.*;

public class Question {
    private GUI frame;
    private Room room;
    private Counter counter;
    private Weapon weapon;
    private Counter accuser;
    private String[] playerOrder;
    private int orderStart;
    private int currentPlayer;

    Question(Counter accuser, GUI frame, String[] playerOrder) {
        this.accuser = accuser;
        this.room = accuser.getCurrentRoom();
        this.frame = frame;
        this.playerOrder = playerOrder;

        int i = 0;
        for (String s : playerOrder) {
            if (accuser.getCharacterName().equals(s)) {
                orderStart = i;
            }
            i++;
            if (s == null) {
                i--;
            }
        }
        currentPlayer = (orderStart + 1)  % i;
        frame.appendText("Enter the person to question:");
    }

    public boolean createAccusation(String command) {
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

    private void selectCounter(String person) {
        counter = Counters.get(person.substring(0, 1).toUpperCase() + person.substring(1));
    }

    private void selectWeapon(String weaponName) {
        weapon = Weapons.get(weaponName.toLowerCase());
    }


    public void accusation(String command) {

    }

    private void confirmReset() {
        frame.resetInfoField();
        
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
