package com.bol.mancala.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    @Test
    public void testConstructBoard() {
        var board = new Board();
        assertEquals(board.getPits().size(), Board.PIT_TOTAL_COUNT);
        assertEquals(board.getPits().stream()
                .filter(it -> it.getStones() == 0 || it.getStones() == Board.STONE_COUNT)
                .count(), Board.PIT_TOTAL_COUNT);
    }

    @Test
    void testGetPitWhenGivenIndexGreaterThanPitTotalCount() {
        var board = new Board();
        assertThrows(IndexOutOfBoundsException.class, () -> board.getPit(Board.PIT_TOTAL_COUNT + 1));
    }

    @Test
    void testGetPitsWhenBoardIsCreated() {
        var board = new Board();
        assertEquals(board.getPits().size(), Board.PIT_TOTAL_COUNT);
    }
}
