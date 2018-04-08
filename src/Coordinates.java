/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

/**
 * Stores a row and column value - used for references to locations on the board
 */
public class Coordinates {

    private int row, column;

    Coordinates(int column, int row) {
        this.column = column;
        this.row = row;
    }

    /**
     * Returns the row value
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column value
     */
    public int getColumn() {
        return column;
    }
}
