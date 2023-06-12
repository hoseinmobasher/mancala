package com.bol.mancala.actions.executors;

import com.bol.mancala.actions.base.Executor;
import com.bol.mancala.domain.Game;
import com.bol.mancala.domain.enumeration.GameState;
import com.bol.mancala.exceptions.types.EmptyPitException;
import com.bol.mancala.exceptions.types.IllegalGameStateException;
import com.bol.mancala.exceptions.types.IncorrectTurnException;
import com.bol.mancala.exceptions.types.InvalidPitException;
import lombok.extern.log4j.Log4j2;

/**
 * Validate pick command of selected pit regarding the following rules:
 * <li> Game should be in {@code GameState.IN_PROGRESS}. </li>
 * <li> Big pit, empty pit, and opponent's pit could not be selected. </li>
 */
@Log4j2
public class PickValidatorExecutor implements Executor<Integer> {
    @Override
    public void execute(Game game, Integer selectedPit) {
        if (GameState.FINISHED == game.getState()) {
            throw new IllegalGameStateException(game.getState());
        }

        var pit = game.getBoard().getPit(selectedPit);
        if (pit.isBigPit()) {
            throw new InvalidPitException();
        }

        if (!game.getTurn().equals(pit.getOwner())) {
            throw new IncorrectTurnException(game.getTurn());
        }

        if (pit.getStones() == 0) {
            throw new EmptyPitException();
        }

        log.debug("Game[{}] is in-progress.", game.getId());
    }
}
