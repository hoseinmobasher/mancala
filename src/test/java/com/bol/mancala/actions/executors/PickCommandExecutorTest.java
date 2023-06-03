package com.bol.mancala.actions.executors;

import com.bol.mancala.domain.Game;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PickCommandExecutorTest {
    private static PickCommandExecutor executor;

    @BeforeAll
    public static void setUp() {
        executor = new PickCommandExecutor();
    }

    @Test
    public void sowStones_GivenSelectedPit_SelectedPitStoneIsZero() {
        Game game = new Game();
        executor.executeInternal(game, 0);

        assertEquals(game.getBoard().getPit(0).getStones(), 0);
    }

    @Test
    public void sowStones_LastStoneSowedInEmptyPitOfOwnerAndOppositePitHadStones_StonesIsCapturedAnd() {

    }
}
