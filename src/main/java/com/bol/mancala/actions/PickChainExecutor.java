package com.bol.mancala.actions;

import com.bol.mancala.actions.base.Executor;
import com.bol.mancala.actions.executors.*;
import com.bol.mancala.domain.Game;
import org.springframework.stereotype.Service;

@Service
public class PickChainExecutor {
    private final Executor<Integer> rootExecutor;

    public PickChainExecutor() {
        rootExecutor = new PickValidatorExecutor();
        rootExecutor.next(new PickCommandExecutor())
                .next(new PickFinalizeExecutor())
                .next(new BoardPrinterExecutor());
    }

    public void execute(Game game, int selectedPit) {
        rootExecutor.execute(game, selectedPit);
    }
}
