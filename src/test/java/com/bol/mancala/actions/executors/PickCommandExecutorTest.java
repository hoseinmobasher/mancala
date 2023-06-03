package com.bol.mancala.actions.executors;

import com.bol.mancala.domain.Game;
import com.bol.mancala.domain.enumeration.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.bol.mancala.domain.Board.PIT_COUNT;
import static com.bol.mancala.domain.Board.STONE_COUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PickCommandExecutorTest {
    private static PickCommandExecutor executor;

    @BeforeAll
    public static void setUp() {
        executor = new PickCommandExecutor();
    }

    @Test
    public void testSowStonesWhenSelectedPitIsValid() {
        Game game = new Game();
        executor.executeInternal(game, 0);

        assertEquals(game.getBoard().getPit(0).getStones(), 0);
        assertEquals(game.getBoard().getPit(1).getStones(), STONE_COUNT + 1);
    }

    @Test
    public void testCaptureStoneWhenLastIndexIsEmpty() {
        Game game = new Game();
        game.getBoard().getPit(2).setStones(1);
        game.getBoard().getPit(3).setStones(0);
        assertEquals(game.getBoard().getPit(Player.PLAYER_1.getStore()).getStones(), 0);

        executor.executeInternal(game, 2);
        assertEquals(game.getBoard().getPit(Player.PLAYER_1.getStore()).getStones(), STONE_COUNT + 1);
    }

    @Test
    public void testChangeTurnWhenLastIndexIsBigPit() {
        Game game = new Game();
        game.getBoard().getPit(0).setStones(PIT_COUNT);

        Player lastTurn = game.getTurn();
        executor.executeInternal(game, 0);
        assertEquals(game.getTurn(), lastTurn);
    }

    @Test
    public void testChangeTurnWhenLastIndexIsNotInBigPit() {
        Game game = new Game();
        game.getBoard().getPit(0).setStones(PIT_COUNT - 1);

        Player lastTurn = game.getTurn();
        executor.executeInternal(game, 0);
        assertNotEquals(game.getTurn(), lastTurn);
    }
}
