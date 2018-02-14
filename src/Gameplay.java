import java.util.Random;

public class Gameplay {

    public Gameplay() {
        System.out.println(rollDice());
        System.out.println(rollDice());
        System.out.println(rollDice());
        System.out.println(rollDice());
        System.out.println(rollDice());
        System.out.println(rollDice());
        System.out.println(rollDice());
        System.out.println(rollDice());
        System.out.println(rollDice());
        System.out.println(rollDice());
    }

    static int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }
}
