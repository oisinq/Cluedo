/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

/**
 * This is the main class that is ran when the program starts
 */
public class Main {

    private Main() {
        Counters counters = new Counters();
        Rooms rooms = new Rooms();
        Weapons weapons = new Weapons();

        new GameSetup(counters);
        new GUI(counters, weapons, rooms, Counters.players);
    }

    public static void main(String args[]) {
        new Main();
    }
}