package ru.job4j.tictactoe;

/**
 * Logic of application.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 08/05/2018
 */
public class Logic3T {
    /**
     * Cell marked by X.
     */
    private final static boolean MARK_X = true;

    /**
     * Cell marked by O.
     */
    private final static boolean MARK_O = true;

    /**
     * The game board 3x3.
     */
    private final Figure3T[][] table;

    /**
     * Constructor.
     *
     * @param table initial table.
     */
    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Checks for winning combination of X.
     *
     * @return - true if there is winning combination of X.
     */
    public boolean isWinnerX() {
        return this.checkLineX() || this.checkColumnX() || this.checkDiagonalsX();
    }

    /**
     * Checks for winning combination of O.
     *
     * @return - true if there is winning combination of O.
     */
    public boolean isWinnerO() {
        return this.checkLineO() || this.checkColumnO() || this.checkDiagonalsO();
    }

    /**
     * Checks for empty cells on the table.
     *
     * @return true if there is empty cells.
     */
    public boolean hasGap() {
        boolean result = false;
        for (int indexX = 0; indexX < this.table[0].length; indexX++) {
            for (int indexY = 0; indexY < this.table.length; indexY++) {
                if (!this.table[indexX][indexY].hasMarkX() && !this.table[indexX][indexY].hasMarkO()) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Checks columns for X winning combination.
     *
     * @return true if winning combination exists.
     */
    private boolean checkColumnX() {
        boolean result = true;
        for (int indexX = 0; indexX < this.table.length; indexX++) {
            result = true;
            for (int indexY = 0; indexY < this.table[0].length - 1; indexY++) {
                if (!(this.table[indexX][indexY].hasMarkX() == Logic3T.MARK_X
                        && this.table[indexX][indexY].hasMarkX() == this.table[indexX][indexY + 1].hasMarkX())) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    /**
     * Checks columns for O winning combination.
     *
     * @return true if winning combination exists.
     */
    private boolean checkColumnO() {
        boolean result = true;
        for (int indexX = 0; indexX < this.table.length; indexX++) {
            result = true;
            for (int indexY = 0; indexY < this.table[0].length - 1; indexY++) {
                if (!(this.table[indexX][indexY].hasMarkO() == Logic3T.MARK_O
                        && this.table[indexX][indexY].hasMarkO() == this.table[indexX][indexY + 1].hasMarkO())) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    /**
     * Checks lines for X winning combination.
     *
     * @return true if winning combination exists.
     */
    private boolean checkLineX() {
        boolean result = true;
        for (int indexX = 0; indexX < this.table.length; indexX++) {
            result = true;
            for (int indexY = 0; indexY < this.table[0].length - 1; indexY++) {
                if (!(this.table[indexY][indexX].hasMarkX() == Logic3T.MARK_X
                        && this.table[indexY][indexX].hasMarkX() == this.table[indexY + 1][indexX].hasMarkX())) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    /**
     * Checks lines for O winning combination.
     *
     * @return true if winning combination exists.
     */
    private boolean checkLineO() {
        boolean result = true;
        for (int indexX = 0; indexX < this.table.length; indexX++) {
            result = true;
            for (int indexY = 0; indexY < this.table[0].length - 1; indexY++) {
                if (!(this.table[indexY][indexX].hasMarkO() == Logic3T.MARK_O
                        && this.table[indexY][indexX].hasMarkO() == this.table[indexY + 1][indexX].hasMarkO())) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    /**
     * Checks diagonals for X winning combination.
     *
     * @return true if winning combination exists.
     */
    private boolean checkDiagonalsX() {
        boolean result;
        boolean diagonal1 = true;
        boolean diagonal2 = true;
        for (int index = 0; index < this.table[0].length - 1; index++) {
            if (!(this.table[index][index].hasMarkX() == Logic3T.MARK_X
                    && this.table[index][index].hasMarkX() == this.table[index + 1][index + 1].hasMarkX())) {
                diagonal1 = false;
                break;
            }
        }
        for (int index = 0; index < this.table[0].length - 1; index++) {
            if (!(this.table[this.table.length - index - 1][index].hasMarkX() == Logic3T.MARK_X
                    && this.table[this.table.length - index - 1][index].hasMarkX() == this.table[this.table.length - index - 2][index + 1].hasMarkX())) {
                diagonal2 = false;
                break;
            }
        }
        result = diagonal1 || diagonal2;
        return result;
    }

    /**
     * Checks diagonals for O winning combination.
     *
     * @return true if winning combination exists.
     */
    private boolean checkDiagonalsO() {
        boolean result;
        boolean diagonal1 = true;
        boolean diagonal2 = true;
        for (int index = 0; index < this.table[0].length - 1; index++) {
            if (!(this.table[index][index].hasMarkO() == Logic3T.MARK_O
                    && this.table[index][index].hasMarkO() == this.table[index + 1][index + 1].hasMarkO())) {
                diagonal1 = false;
                break;
            }
        }
        for (int index = 0; index < this.table[0].length - 1; index++) {
            if (!(this.table[this.table.length - index - 1][index].hasMarkO() == Logic3T.MARK_O
                    && this.table[this.table.length - index - 1][index].hasMarkO() == this.table[this.table.length - index - 2][index + 1].hasMarkO())) {
                diagonal2 = false;
                break;
            }
        }
        result = diagonal1 || diagonal2;
        return result;
    }
}