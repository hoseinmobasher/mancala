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
    public void executeInternal_WhenGameIsFinished_ThrowIllegalGameStateException() {
        var game = new Game();
        game.setState(GameState.FINISHED);

        assertThrows(IllegalGameStateException.class, () -> executor.executeInternal(game, 0));
    }

    @Test
    public void executeInternal_WhenTurnIsNotConsidered_ThrowIncorrectTurnException() {
        var game = new Game();
        game.setTurn(Player.PLAYER_1);

        assertThrows(IncorrectTurnException.class, () -> executor.executeInternal(game, Player.PLAYER_2.getStore() - 1));
    }

    @Test
    public void executeInternal_WhenSelectedPitIsEmpty_ThrowEmptyPitException() {
        var game = new Game();
        game.getBoard().getPit(0).setStones(0);

        assertThrows(EmptyPitException.class, () -> executor.executeInternal(game, 0));
    }

    @Test
    public void executeInternal_WhenABigPitIsSelected_ThrowInvalidPitException() {
        var game = new Game();

        assertThrows(InvalidPitException.class, () -> executor.executeInternal(game, Player.PLAYER_1.getStore()));
        assertThrows(InvalidPitException.class, () -> executor.executeInternal(game, Player.PLAYER_2.getStore()));
    }
}
