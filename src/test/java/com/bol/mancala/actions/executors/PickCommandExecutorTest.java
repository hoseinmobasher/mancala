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
    public void testSowStones() {
        var game = new Game();
        executor.execute(game, 0);

        assertEquals(game.getBoard().getPit(0).getStones(), 0);
        assertEquals(game.getBoard().getPit(1).getStones(), STONE_COUNT + 1);
    }

    @Test
    public void testSowStonesForOpponentBigPit() {
        Game game = new Game();
        game.getBoard().getPit(5).setStones(14);
        var p2BigPitStones = game.getBoard().getPit(Player.PLAYER_2.getBigPit()).getStones();

        executor.execute(game, 5);
        assertEquals(game.getBoard().getPit(Player.PLAYER_2.getBigPit()).getStones(), p2BigPitStones);
    }

    @Test
    public void testSowStonesForPlayerStorePit() {
        Game game = new Game();
        game.getBoard().getPit(5).setStones(1);
        var p2BigPitStones = game.getBoard().getPit(Player.PLAYER_1.getBigPit()).getStones();

        executor.execute(game, 5);
        assertEquals(game.getBoard().getPit(Player.PLAYER_1.getBigPit()).getStones(), p2BigPitStones + 1);
    }

    @Test
    public void testCaptureStone() {
        var game = new Game();
        game.getBoard().getPit(2).setStones(1);
        game.getBoard().getPit(3).setStones(0);
        assertEquals(game.getBoard().getPit(Player.PLAYER_1.getBigPit()).getStones(), 0);

        executor.execute(game, 2);
        assertEquals(game.getBoard().getPit(Player.PLAYER_1.getBigPit()).getStones(), STONE_COUNT + 1);
    }

    @Test
    public void testCaptureStoneWhenOpponentPitIsEmpty() {
        var game = new Game();
        game.getBoard().getPit(2).setStones(1);
        game.getBoard().getPit(3).setStones(0);
        game.getBoard().getPit(PIT_COUNT * 2 - 2).setStones(0);
        executor.execute(game, 2);

        assertEquals(game.getBoard().getPit(Player.PLAYER_2.getBigPit()).getStones(), 0);
    }

    @Test
    public void testChangeTurnWhenLastIndexIsBigPit() {
        var game = new Game();
        game.getBoard().getPit(0).setStones(PIT_COUNT);

        var lastTurn = game.getTurn();
        executor.execute(game, 0);
        assertEquals(game.getTurn(), lastTurn);
    }

    @Test
    public void testChangeTurnWhenLastIndexIsNotInBigPit() {
        var game = new Game();
        game.getBoard().getPit(0).setStones(PIT_COUNT - 1);

        var lastTurn = game.getTurn();
        executor.execute(game, 0);
        assertNotEquals(game.getTurn(), lastTurn);
    }
}
