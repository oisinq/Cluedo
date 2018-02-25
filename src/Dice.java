/*  Cluedo - Sprint 2
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import java.util.Random;

public class Dice {

    Random rand;

    public Dice() {
        rand = new Random();
    }

    int roll() {
        // This returns a random number between 1 and 6
        return rand.nextInt(6) + 1;
    }
}
