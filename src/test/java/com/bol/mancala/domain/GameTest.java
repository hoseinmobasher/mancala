package com.bol.mancala.domain;

import com.bol.mancala.domain.enumeration.GameState;
import com.bol.mancala.domain.enumeration.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    public void constructor_GameIsCreated_IdIsNotNullAndBoardIsNotNullAndStateIsInProgressAndTurnIsPlayer1AndWinnerIsNull() {
        var game = new Game();
        assertNotNull(game.getId());
        assertNotNull(game.getBoard());
        assertEquals(game.getState(), GameState.IN_PROGRESS);
        assertEquals(game.getTurn(), Player.PLAYER_1);
        assertNull(game.getWinner());
    }

    @Test
    public void equals_GivenTwoGameWithDifferentId_ReturnsFalse() {
        var game1 = new Game();
        var game2 = new Game();

        assertNotEquals(game1, game2);
    }

    @Test
    public void hashcode_GivenTwoGameWithDifferentId_ReturnsDifferentHashCodes() {
        var game1 = new Game();
        var game2 = new Game();

        assertNotEquals(game1.hashCode(), game2.hashCode());
    }
}
