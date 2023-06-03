package com.bol.mancala.domain;

import com.bol.mancala.domain.enumeration.Player;
import org.junit.jupiter.api.Test;

import static com.bol.mancala.domain.Board.PIT_TOTAL_COUNT;
import static com.bol.mancala.domain.Board.STONE_COUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PitTest {
    @Test
    public void testGetOwnerWhenIndexBelongsToPlayer1() {
        var pit = new Pit(Player.PLAYER_1.getStore() - 1, STONE_COUNT);
        assertEquals(pit.getOwner(), Player.PLAYER_1);
    }

    @Test
    public void testGetOwnerWhenIndexBelongsToPlayer2() {
        var pit = new Pit(Player.PLAYER_1.getStore() + 1, STONE_COUNT);
        assertEquals(pit.getOwner(), Player.PLAYER_2);
    }

    @Test
    public void testIsStoreWhenIndexIsPitTotalCountMinusOne() {
        var pit = new Pit(PIT_TOTAL_COUNT - 1, STONE_COUNT);
        assertTrue(pit.isStore());
    }

    @Test
    public void testIsStoreWhenIndexIsPitTotalCountDividedByTwoMinusOne() {
        var pit = new Pit(PIT_TOTAL_COUNT / 2 - 1, STONE_COUNT);
        assertTrue(pit.isStore());
    }

    @Test
    public void testToStringWhenStoneIsStoneCount() {
        var pit = new Pit(1, STONE_COUNT);
        assertEquals(pit.toString(), Integer.toString(STONE_COUNT));
    }
}
