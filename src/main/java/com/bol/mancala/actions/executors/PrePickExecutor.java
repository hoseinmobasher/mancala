package com.bol.mancala.actions.executors;

import com.bol.mancala.actions.base.Executor;
import com.bol.mancala.domain.Game;
import com.bol.mancala.domain.enumeration.GameState;
import com.bol.mancala.exceptions.types.EmptyPitException;
import com.bol.mancala.exceptions.types.IllegalGameStateException;
import com.bol.mancala.exceptions.types.IncorrectTurnException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PrePickExecutor extends Executor<Integer> {
    @Override
    public void executeInternal(Game game, Integer selectedPit) {
        if (GameState.FINISHED == game.getState()) {
            throw new IllegalGameStateException(game.getState());
        }

        var pit = game.getBoard().getPit(selectedPit);
        if (!game.getTurn().equals(pit.getOwner())) {
            throw new IncorrectTurnException(game.getTurn());
        }

        if (pit.getStones() == 0) {
            throw new EmptyPitException();
        }

        log.debug("Game[{}] is in-progress.", game.getId());
    }
}
