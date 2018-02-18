import java.util.Random;

public class Dice {

    Random rand;

    public Dice() {
        rand = new Random();
    }

    int rollDice() {
        // This returns a random number between 1 and 6
        return rand.nextInt(6) + 1;
    }
}
