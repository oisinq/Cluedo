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
    private boolean hasCard = false;
    private String shownCard = null;
    private boolean waitingForConfirmation = false;

    Question(Counter accuser, GUI frame, String[] playerOrder) {
        this.accuser = accuser;
        this.room = accuser.getCurrentRoom();
        this.frame = frame;
        this.playerOrder = playerOrder;

        numPlayers = 0;
        // This counts the number of players in the game and finds the starting position in playerOrder
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

    /**
     * This is used to let the accuser enter in details about the question he/she wants to ask
     */
    public boolean defineQuestion(String command) {
        command = command.toLowerCase();

        // We let the user access their notes here and not in Gameplay, because we want to show the notes for the player in currentPlayerIndex
        if (command.equals("notes")) {
            Counter c = Counters.get(playerOrder[currentPlayerIndex]);
            frame.appendText(c.getNotesString());
        } else if (counter == null) {
            // If a counter hasn't been entered yet, we let the user pick a counter
            selectCounter(command.toLowerCase());
            // If a valid counter has been selected, we ask for the weapon
            if (counter != null) {
                frame.appendText("Enter the weapon to question:");
            } else {
                frame.appendText("Invalid player. Please try again!");
            }
        } else if (weapon == null) {
            // If a weapon hasn't been entered yet, we let the user pick a weapon
            selectWeapon(command.toLowerCase());
            if (weapon != null) {
                counter.setCurrentRoom(room);
                frame.appendText("You have accused " + counter.getCharacterName() + " of committing a murder with the "
                    + weapon.getName() + " in the " + room.getRoomName() + "\n");
                return false;
            } else {
                frame.appendText("Invalid weapon. Please try again!");
            }
        }
        return true;
    }

    /**
     * This checks if the entered counter is valid
     */
    private void selectCounter(String person) {
        counter = Counters.get(person.substring(0, 1).toUpperCase() + person.substring(1));
    }

    /**
     * This checks if the entered weapon is valid
     */
    private void selectWeapon(String weaponName) {
        weapon = Weapons.get(weaponName.toLowerCase());
    }

    /**
     * This moves to the next player in playerOrder
     */
    private void done() {
        currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
        frame.resetInfoField();
        waitingForConfirmation = false;
    }

    /**
     * This is the confirmation screen to ensure that nobody sees info they shouldn't
     */
    public void confirmHandoff() {
        frame.resetInfoField();
        String name;

        // If a card hasn't been shown, then name is the next player in the list
        // Otherwise, it's the accuser (as the accuser will now be shown the results of the questioning)
        if (shownCard == null) {
            name = playerOrder[(currentPlayerIndex + 1) % numPlayers];
        } else {
            name = accuser.getCharacterName();
        }
        frame.appendText("Pass the screen to " + name + " (" + Counters.get(name).getUserName() + ")");
        frame.appendText("To confirm that " + name + " (" + Counters.get(name).getUserName() + ") now has the screen, type 'swapped'");
        // This tells us that we're waiting on the user to type "swapped"
        waitingForConfirmation = true;
    }

    /**
     * Method for checking if a player has cards and letting them select one to show the accuser if they do
     */
    public boolean questioning(String command) {
        // If we're waiting for a confirmation, this if statement is ran
        if (waitingForConfirmation) {
            if (command.equals("swapped")) {
                if (shownCard == null) {
                    // If we're back at the accuser, we return the result of showPlayer (a boolean)
                    if (playerOrder[(currentPlayerIndex + 1) % numPlayers].equals(accuser.getCharacterName())) {
                        showResults();
                        return true;
                    }
                } else {
                    // If a card has been selected, we return the result of showPlayer (a boolean)
                    showResults();
                    return true;
                }
                // Otherwise, we move to the next player
                done();
            } else {
                // Otherwise, we show an error asking the user to swap the computer to the next player
                if (shownCard != null) {
                    frame.appendText("Incorrect command - type 'swapped' to confirm that " + accuser.getCharacterName() + " (" + accuser.getUserName() + ") now has the screen");
                    return false;
                } else {
                    frame.appendText("Incorrect command - type 'swapped' to confirm that " + playerOrder[(currentPlayerIndex + 1) % numPlayers] + " (" + Counters.get(playerOrder[(currentPlayerIndex + 1) % numPlayers]).getUserName() + ") now has the screen");
                    return false;
                }
            }
        }
        // If you're done, we move to the next turn
        if (command.equals("done")) {
            // If you have a card, you can't skip your turn and must show a card
            if (hasCard) {
                frame.appendText("You must show one of the cards!");
            } else {
                // Otherwise, we confirm the screen is handed off to the next player
                confirmHandoff();
                return false;
            }
            // This prints the notes for the current player
            // We have to put this here so it prints the correct player (printing them from Gameplay would be incorrect)
        } else if (command.equals("notes")) {
            Counter c = Counters.get(playerOrder[currentPlayerIndex]);
            frame.appendText(c.getNotesString());
        }

        // If the question isn't complete yet, we pass the input to defineQuestion
        if (counter == null || weapon == null || room == null) {
            defineQuestion(command);
        }
        // If the input is a number, we check if it's a valid selection
        if (checkInteger(command)) {
            int selection = Integer.parseInt(command);
            if (selection >= 1 && selection <= 3) {
                // This switch statement checks if the currentPlayer has the selected card
                // if they do, it lets them selected it, and goes back to the accuser
                switch (selection) {
                    case 1:
                        if (currentPlayer.hasCardName(counter.getCharacterName())) {
                            shownCard = counter.getCharacterName();
                            frame.appendText(shownCard + " is selected");
                            accuser.addNotes(shownCard);
                            confirmHandoff();
                            return false;
                        } else {
                            frame.appendText("You don't have " + counter.getCharacterName());
                        }
                        break;
                    case 2:
                        if (currentPlayer.hasCardName(weapon.getName())) {
                            shownCard = weapon.getName();
                            frame.appendText(shownCard + " is selected");
                            accuser.addNotes(shownCard);
                            confirmHandoff();
                            return false;
                        } else {
                            frame.appendText("You don't have " + weapon.getName());
                        }
                        break;
                    case 3:
                        if (currentPlayer.hasCardName(room.getRoomName())) {
                            shownCard = room.getRoomName();
                            frame.appendText(shownCard + " is selected");
                            accuser.addNotes(shownCard);
                            confirmHandoff();
                            return false;
                        } else {
                            frame.appendText("You don't have " + room.getRoomName());
                        }
                        break;
                }
            } else {
                frame.appendText(selection + " is not a valid card selection!");
            }
        } else {
            frame.appendText("Please select a valid card!");
        }

        checkCards();

        return false;
    }

    /**
     * This checks if the user has one of the cards and tells them if they do
     */
    private void checkCards() {
        frame.appendText("It's " + playerOrder[currentPlayerIndex] + "'s (" + Counters.get(playerOrder[currentPlayerIndex]).getUserName() + ") turn to show cards.");

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

        // If the player has one of the cards, we let them know
        if (haveCounter || haveWeapon || haveRoom) {
            hasCard = true;
            frame.appendText("You have some of the cards:");
            if (haveCounter) frame.appendText("Enter '1' to show " + counter.getCharacterName());
            if (haveWeapon) frame.appendText("Enter '2' to show " + weapon.getName());
            if (haveRoom) frame.appendText("Enter '3' to show " + room.getRoomName());
        } else {
            hasCard = false;
            frame.appendText("You have no cards. Type 'done' to finish your turn");
        }
    }

    /**
     * This prints the results from the questioning
     */
    private void showResults() {
        frame.resetInfoField();
        frame.appendText(accuser.getCharacterName() + " (" + accuser.getUserName() + "): Here are the results from the questioning!");
        int loopIndex = (orderStart + 1) % numPlayers;
        if (shownCard == null) {
            frame.appendText("Nobody had the cards you asked.");
        } else {
            while (!playerOrder[currentPlayerIndex].equals(playerOrder[loopIndex])) {
                frame.appendText(playerOrder[loopIndex] + " (" + Counters.get(playerOrder[currentPlayerIndex]).getUserName() + ") had no cards");
                loopIndex++;
                if (loopIndex == numPlayers) {
                    loopIndex = loopIndex % numPlayers;
                }
            }
            frame.appendText(playerOrder[currentPlayerIndex] + " (" + Counters.get(playerOrder[currentPlayerIndex]).getUserName() + ") showed you the " + shownCard + " card");
        }
        frame.appendText("You can now type 'done' to end your turn, or 'notes' to view your updated notes");
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
