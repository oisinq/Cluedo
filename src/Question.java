/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

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
    private int numPlayers;

    Question(Counter accuser, GUI frame, String[] playerOrder) {
        this.accuser = accuser;
        this.room = accuser.getCurrentRoom();
        this.frame = frame;
        this.playerOrder = playerOrder;

        numPlayers = 0;
        for (String s : playerOrder) {
            if (accuser.getCharacterName().equals(s)) {
                orderStart = numPlayers;
            }
            numPlayers++;
            if (s == null) {
                numPlayers--;
            }
        }
        currentPlayer = (orderStart + 1)  % numPlayers;
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
                checkCards(counter.getCharacterName(), weapon.getName(), room.getRoomName());
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

    private void checkCards(String counterName, String weaponName, String roomName) {
        int tracker=0;
        int position = currentPlayer;
        while(tracker < numPlayers) {
            if(Counters.get(playerOrder[position]).hasCardName(counterName)) {
                System.out.println(playerOrder[position] + " has " + counterName);
            }
            if(Counters.get(playerOrder[position]).hasCardName(weaponName)) {
                System.out.println(playerOrder[position] + " has " + weaponName);
            }
            if(Counters.get(playerOrder[position]).hasCardName(roomName)) {
                System.out.println(playerOrder[position] + " has " + roomName);
            }
            tracker++;
            position = (position+1) % numPlayers;
        }

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
