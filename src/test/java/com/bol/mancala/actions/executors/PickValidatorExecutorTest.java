package com.bol.mancala.actions.executors;

import com.bol.mancala.domain.Game;
import com.bol.mancala.domain.enumeration.GameState;
import com.bol.mancala.domain.enumeration.Player;
import com.bol.mancala.exceptions.types.EmptyPitException;
import com.bol.mancala.exceptions.types.IllegalGameStateException;
import com.bol.mancala.exceptions.types.IncorrectTurnException;
import com.bol.mancala.exceptions.types.InvalidPitException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PickValidatorExecutorTest {
    private static PickValidatorExecutor executor;

    @BeforeAll
    public static void setUp() {
        executor = new PickValidatorExecutor();
    }

    @Test
    public void testExecuteInternalWhenGameIsFinished() {
        var game = new Game();
        game.setState(GameState.FINISHED);

        assertThrows(IllegalGameStateException.class, () -> executor.execute(game, 0));
    }

    @Test
    public void testExecuteInternalWhenTurnIsNotConsidered() {
        var game = new Game();
        game.setTurn(Player.PLAYER_1);

        assertThrows(IncorrectTurnException.class, () -> executor.execute(game, Player.PLAYER_2.getBigPit() - 1));
    }

    @Test
    public void testExecuteInternalWhenSelectedPitIsEmpty() {
        var game = new Game();
        game.getBoard().getPit(0).setStones(0);

        assertThrows(EmptyPitException.class, () -> executor.execute(game, 0));
    }

    @Test
    public void testExecuteInternalWhenABigPitIsSelected() {
        var game = new Game();

        assertThrows(InvalidPitException.class, () -> executor.execute(game, Player.PLAYER_1.getBigPit()));
        assertThrows(InvalidPitException.class, () -> executor.execute(game, Player.PLAYER_2.getBigPit()));
    }
}
