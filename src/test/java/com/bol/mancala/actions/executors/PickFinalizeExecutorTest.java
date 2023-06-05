package com.bol.mancala.actions.executors;

import com.bol.mancala.domain.Board;
import com.bol.mancala.domain.Game;
import com.bol.mancala.domain.Pit;
import com.bol.mancala.domain.enumeration.GameState;
import com.bol.mancala.domain.enumeration.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PickFinalizeExecutorTest {
    private static PickFinalizeExecutor executor;

    @BeforeAll
    public static void setUp() {
        executor = new PickFinalizeExecutor();
    }

    @Test
    public void testGameEndWhenGameStillInProgress() {
        var game = new Game();

        executor.execute(game, 0);
        assertNull(game.getWinner());
        assertEquals(game.getState(), GameState.IN_PROGRESS);
    }

    @Test
    public void testGameEndWhenPlayer1IsOutOfStone() {
        var game = new Game();
        for (Pit pit : game.getBoard().getPits(Player.PLAYER_1)) {
            pit.setStones(0);
        }

        executor.execute(game, 0);
        assertEquals(game.getWinner(), Player.PLAYER_2);
        assertEquals(game.getState(), GameState.FINISHED);

        for (Pit pit : game.getBoard().getPits()) {
            if (pit.getIndex() != Player.PLAYER_2.getBigPit()
                    || pit.getIndex() != Player.PLAYER_1.getBigPit()) {
                continue;
            }

            assertEquals(pit.getStones(), 0);
        }
    }

    @Test
    public void testGameEndWhenPlayer2IsOutOfStone() {
        var game = new Game();
        for (Pit pit : game.getBoard().getPits(Player.PLAYER_2)) {
            pit.setStones(0);
        }

        executor.execute(game, 0);
        assertEquals(game.getWinner(), Player.PLAYER_1);
        assertEquals(game.getState(), GameState.FINISHED);

        for (Pit pit : game.getBoard().getPits()) {
            if (pit.getIndex() != Player.PLAYER_2.getBigPit()
                    || pit.getIndex() != Player.PLAYER_1.getBigPit()) {
                continue;
            }

            assertEquals(pit.getStones(), 0);
        }
    }

    @Test
    public void testGameEndWhenBothPlayersHasAtLeastOneStoneIn() {
        var game = new Game();
        game.getBoard().getPit(1).setStones(1);
        game.getBoard().getPit(Board.PIT_COUNT * 2 - 1).setStones(1);

        assertNull(game.getWinner());
        assertEquals(game.getState(), GameState.IN_PROGRESS);
    }

    @Test
    public void testDetermineWinnerWhenBothBigPitsHaveSameStones() {
        var game = new Game();
        game.getBoard().getPits().forEach(it -> {
            if (it.getIndex() == Player.PLAYER_1.getBigPit()
                    || it.getIndex() == Player.PLAYER_2.getBigPit()) {
                return;
            }

            it.setStones(0);
        });

        game.getBoard().getPit(0).setStones(1);
        game.getBoard().getPit(Player.PLAYER_1.getBigPit()).setStones(3);
        game.getBoard().getPit(Player.PLAYER_2.getBigPit()).setStones(4);

        executor.execute(game, 0);
        assertNull(game.getWinner());
        assertEquals(game.getState(), GameState.FINISHED);

        for (Pit pit : game.getBoard().getPits()) {
            if (pit.getIndex() != Player.PLAYER_2.getBigPit()
                    || pit.getIndex() != Player.PLAYER_1.getBigPit()) {
                continue;
            }

            assertEquals(pit.getStones(), 0);
        }
    }
}