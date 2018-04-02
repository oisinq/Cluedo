/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import javax.swing.*;

public class Accusation {
    private GUI frame;
    private Room room;
    private Counter counter;
    private Weapon weapon;
    private Counter accuser;
    public boolean accusationCreated = false;


    Accusation(Counter accuser, GUI frame) {
        this.accuser = accuser;
        this.frame = frame;

        frame.appendText("Enter the person:");
    }

    public boolean createAccusation(String command) {
        command = command.toLowerCase();
        if (counter == null) {
            selectCounter(command.toLowerCase());
            if (counter != null) {
                frame.appendText("Enter the weapon:");
            } else {
                frame.appendText("Please enter a valid counter.");
            }
        } else if (weapon == null) {
            selectWeapon(command.toLowerCase());
            if (weapon != null) {
                counter.setCurrentRoom(room);
                frame.appendText("Enter the room:");
            } else {
                frame.appendText("Please enter a valid weapon.");
            }
        } else if (room == null) {
            selectRoom(command.toLowerCase());
            if (room != null) {
                frame.appendText("You have accused " + counter.getCharacterName() + " of committing a murder with the "
                        + weapon.getName() + " in the " + room.getRoomName() + "\n");
                accusationCreated = true;
                return false;
            } else {
                frame.appendText("Please enter a valid room.");
            }
        } else {
            frame.appendText("Invalid input. Please try again!");
        }
        return true;
    }

    public boolean checkAccusation() {
        if (counter.getCharacterName().equals(Envelope.getPerson().getName()) && room.getRoomName().equals(Envelope.getRoom().getName()) && weapon.getName().equals(Envelope.getWeapon().getName())) {
            frame.appendText("you win! good job!");
            JOptionPane.showMessageDialog(null, "Congratulations, " + accuser.getCharacterName() + " (" + accuser.getUserName() + ") wins!");
            return true;
        } else {
            frame.appendText("you lose noob");
            return false;
        }
    }

    private void selectCounter(String person) {
        counter = Counters.get(person.substring(0, 1).toUpperCase() + person.substring(1));
    }

    private void selectWeapon(String weaponName) {
        weapon = Weapons.get(weaponName.toLowerCase());
    }

    private void selectRoom(String roomName) {
        room = Rooms.get(roomName.substring(0, 1).toUpperCase() + roomName.substring(1));
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
