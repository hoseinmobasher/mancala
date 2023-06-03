package com.bol.mancala.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    @Test
    public void constructor_BoardIsCreated_PitsSizeIsPitTotalCountAndPitsAreFilledWithZeroOrStoneCount() {
        var board = new Board();
        assertEquals(board.getPits().size(), Board.PIT_TOTAL_COUNT);
        assertEquals(board.getPits().stream()
                .filter(it -> it.getStones() == 0 || it.getStones() == Board.STONE_COUNT)
                .count(), Board.PIT_TOTAL_COUNT);
    }

    @Test
    void getPit_WhenGivenIndexGreaterThanPitTotalCount_ThrowsIndexOutOfBoundException() {
        var board = new Board();
        assertThrows(IndexOutOfBoundsException.class, () -> board.getPit(Board.PIT_TOTAL_COUNT + 1));
    }

    @Test
    void getPits_BoardIsCreated_ReturnsAListWithSizeOfPitTotalCount() {
        var board = new Board();
        assertEquals(board.getPits().size(), Board.PIT_TOTAL_COUNT);
    }
}
