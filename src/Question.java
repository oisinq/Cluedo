/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

public class Question {
    private GUI frame;
    private Room room;
    private Counter counter;
    private Weapon weapon;
    private Counter accuser;
    private Counter currentPlayer;
    private String[] playerOrder;
    private int orderStart;
    private int currentPlayerIndex;
    private int numPlayers;
    private boolean accusing = false;
    private String shownCard;
    private boolean waitingForConfirmation = false;

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
        currentPlayerIndex = orderStart;
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
                frame.appendText("You have accused " + counter.getCharacterName() + " of committing a murder with the "
                        + weapon.getName() + " in the " + room.getRoomName() + "\n");
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
        int tracker = 0;
        int position = currentPlayerIndex;
        while (tracker < numPlayers) {
            if (Counters.get(playerOrder[position]).hasCardName(counterName)) {
                System.out.println(playerOrder[position] + " has " + counterName);
            }
            if (Counters.get(playerOrder[position]).hasCardName(weaponName)) {
                System.out.println(playerOrder[position] + " has " + weaponName);
            }
            if (Counters.get(playerOrder[position]).hasCardName(roomName)) {
                System.out.println(playerOrder[position] + " has " + roomName);
            }
            tracker++;
            position = (position + 1) % numPlayers;
        }
    }

    private void done() {
        currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
        frame.resetInfoField();
        waitingForConfirmation = false;
    }

    public void confirmHandoff() {
        frame.appendText("Pass the screen to " + playerOrder[(currentPlayerIndex + 1) % numPlayers]);
        frame.appendText("To confirm that " + playerOrder[(currentPlayerIndex + 1) % numPlayers] + " now has the screen, type 'swapped'");
        waitingForConfirmation = true;
    }

    public boolean accusation(String command) {
        if (waitingForConfirmation) {
            if (command.equals("swapped")) {
                if (playerOrder[(currentPlayerIndex + 1) % numPlayers].equals(accuser.getCharacterName())) {
                    return showPlayer();
                }
                done();
            } else {
                frame.appendText("Incorrect command - type 'swapped' to confirm that " + playerOrder[(currentPlayerIndex + 1) % numPlayers] + " now has the screen");
                return false;
            }
        }
        if (command.equals("done")) {
            if (accusing) {
                frame.appendText("You must show one of the cards!");
            } else {
                confirmHandoff();
                return false;
            }
        }
        if (counter == null || weapon == null || room == null) {
            createAccusation(command);
        }
        if (checkInteger(command)) {
            int selection = Integer.parseInt(command);
            if (selection >= 1 && selection <= 3) {
                switch (selection) {
                    case 1:
                        if (currentPlayer.hasCardName(counter.getCharacterName())) {
                            shownCard = counter.getCharacterName();
                            frame.appendText(shownCard + " is selected");
                            accuser.addNotes(shownCard);
                            return showPlayer();

                        } else {
                            frame.appendText("You don't have " + counter.getCharacterName());
                        }
                        break;
                    case 2:
                        if (currentPlayer.hasCardName(weapon.getName())) {
                            shownCard = weapon.getName();
                            frame.appendText(shownCard + " is selected");
                            accuser.addNotes(shownCard);
                            return showPlayer();
                        } else {
                            frame.appendText("You don't have " + weapon.getName());
                        }
                        break;
                    case 3:
                        if (currentPlayer.hasCardName(room.getRoomName())) {
                            shownCard = room.getRoomName();
                            frame.appendText(shownCard + " is selected");
                            accuser.addNotes(shownCard);
                            return showPlayer();
                        } else {
                            frame.appendText("You don't have " + room.getRoomName());
                        }
                        break;
                }
            }
        }

        frame.appendText("It's " + playerOrder[currentPlayerIndex] + "'s turn to show cards.");

        boolean haveCounter = false, haveWeapon = false, haveRoom = false;
        currentPlayer = Counters.get(playerOrder[currentPlayerIndex]);
        if (currentPlayer.hasCardName(counter.getCharacterName())) {
            haveCounter = true;
        }
        if (currentPlayer.hasCardName(weapon.getName())) {
            haveWeapon = true;
        }
        if (currentPlayer.hasCardName(room.getRoomName())) {
            haveRoom = true;
        }

        if (haveCounter || haveWeapon || haveRoom) {
            accusing = true;
            frame.appendText("You have some of the cards:");
            if (haveCounter) frame.appendText("Enter '1' to show " + counter.getCharacterName());
            if (haveWeapon) frame.appendText("Enter '2' to show " + weapon.getName());
            if (haveRoom) frame.appendText("Enter '3' to show " + room.getRoomName());
        } else {
            accusing = false;
            frame.appendText("You have no cards. Type 'done' to finish your turn");
        }
        return false;
    }

    private boolean showPlayer() {
        frame.resetInfoField();
        frame.appendText(accuser.getCharacterName() + ": Here are the results from the questioning!");
        int loopIndex = orderStart+1;
        if (shownCard == null) {
            frame.appendText("Nobody had the cards you asked.");
        } else {
            while (!playerOrder[currentPlayerIndex].equals(playerOrder[loopIndex])) {
                frame.appendText(playerOrder[loopIndex] + " had no cards");
                loopIndex++;
                if (loopIndex == numPlayers) {
                    loopIndex = loopIndex % numPlayers;
                }
            }
            frame.appendText(playerOrder[currentPlayerIndex] + " showed you the " + shownCard + " card");
        }
        frame.appendText("You can now type 'done' to end your turn, or 'notes' to view your updated notes");

        return true;
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

    /**
     * Checks if a string is an integer
     */
    private static boolean checkInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        char c;
        while (i < length) {
            c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
            i++;
        }
        return true;
    }
}
