/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import java.util.Random;

/**
 * Dice used for user turns
 */
public class Dice {

    private Random rand;

    Dice() {
        rand = new Random();
    }

    /**
     * Returns a number between 1 and 6
     */
    int roll() {
        return rand.nextInt(6) + 1;
    }
}
