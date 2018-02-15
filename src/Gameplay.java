import java.util.Random;

public class Gameplay {

    GUI gui;

    public Gameplay(GUI gui) {
    }

    void addPlayer() {

    }

    int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }
}
