import java.util.Random;

public class Dice {

    Random rand;

    public Dice() {
        rand = new Random();
    }

    int rollDice() {
        return rand.nextInt(6) + 1;
    }
}
