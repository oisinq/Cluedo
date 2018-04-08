/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

/**
 * This interface is used by both counters and weapons, since both components are very similar
 */
public interface BoardPiece {

    /**
     * Moves the counter up the entered number of steps
     */
    void moveUp(int steps);

    /**
     * Moves the counter down the entered number of steps
     */
    void moveDown(int steps);

    /**
     * Moves the counter left the entered number of steps
     */
    void moveLeft(int steps);

    /**
     * Moves the counter right the entered number of steps
     */
    void moveRight(int steps);
}