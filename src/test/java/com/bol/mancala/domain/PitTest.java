package com.bol.mancala.domain;

import com.bol.mancala.domain.enumeration.Player;
import org.junit.jupiter.api.Test;

import static com.bol.mancala.domain.Board.PIT_TOTAL_COUNT;
import static com.bol.mancala.domain.Board.STONE_COUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PitTest {
    @Test
    public void getOwner_WhenIndexIsLessThanPlayer1Store_ReturnsPlayer1() {
        var pit = new Pit(Player.PLAYER_1.getStore() - 1, STONE_COUNT);
        assertEquals(pit.getOwner(), Player.PLAYER_1);
    }

    @Test
    public void getOwner_WhenIndexIsMoreThanPlayer1Store_ReturnsPlayer1() {
        var pit = new Pit(Player.PLAYER_1.getStore() + 1, STONE_COUNT);
        assertEquals(pit.getOwner(), Player.PLAYER_2);
    }

    @Test
    public void isStore_WhenIndexIsPitTotalCountMinusOne_ReturnTrue() {
        var pit = new Pit(PIT_TOTAL_COUNT - 1, STONE_COUNT);
        assertTrue(pit.isStore());
    }

    @Test
    public void isStore_WhenIndexIsPitTotalCountDividedByTwoMinusOne_ReturnTrue() {
        var pit = new Pit(PIT_TOTAL_COUNT / 2 - 1, STONE_COUNT);
        assertTrue(pit.isStore());
    }

    @Test
    public void toString_ReturnsStringFormatOfStones() {
        var pit = new Pit(1, STONE_COUNT);
        assertEquals(pit.toString(), Integer.toString(STONE_COUNT));
    }
}