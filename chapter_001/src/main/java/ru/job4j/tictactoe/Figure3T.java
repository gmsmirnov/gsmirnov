package ru.job4j.tictactoe;

import javafx.scene.shape.Rectangle;

/**
 * Cell on the board. Checks for X or O in the cell, or is cell empty.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 08/05/2018
 */
public class Figure3T extends Rectangle {
    private boolean markX = false;

    private boolean markO = false;

    public Figure3T() {

    }

    public Figure3T(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }

    public void take(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }

    public boolean hasMarkX() {
        return this.markX;
    }

    public boolean hasMarkO() {
        return this.markO;
    }
}