package ru.job4j.bomberman;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BoardTest {
    @Test
    public void whenBombermanMovesThanTrue() {
        Board.init(); // 3*3
        Hero bomberman = new Hero(0, 0);
        Board.locate(bomberman);
        assertThat(Board.move(0, 0, 0, 1), is(true));
        assertThat(Board.move(0, 1, 1, 1), is(true));
        assertThat(Board.move(1, 1, 1, 2), is(true));
        assertThat(Board.move(1, 2, 2, 2), is(true));
    }
}