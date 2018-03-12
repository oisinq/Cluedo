/*  Cluedo - Sprint 2
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

/**
 * This is the main class that is ran when the program starts
 */
public class Main {

    private final Counters counters = new Counters();
    private final Weapons weapons = new Weapons();
    private final Rooms rooms = new Rooms();
    private GUI frame;

    private Main() {
        new GameSetup(counters);
        frame = new GUI(counters, weapons, rooms);
    }

    public static void main(String args[]) {
        new Main();
    }
}