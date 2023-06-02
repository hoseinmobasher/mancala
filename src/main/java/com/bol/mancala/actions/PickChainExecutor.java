package com.bol.mancala.actions;

import com.bol.mancala.actions.base.Executor;
import com.bol.mancala.actions.executors.*;
import com.bol.mancala.domain.Game;
import org.springframework.stereotype.Service;

@Service
public class PickChainExecutor {
    private final Executor<Integer> rootExecutor;

    public PickChainExecutor() {
        rootExecutor = new PrePickExecutor();
        rootExecutor.next(new PickExecutor())
                .next(new PostPickProcessor())
                .next(new BoardPrinterExecutor());
    }

    public void execute(Game game, int selectedPit) {
        rootExecutor.execute(game, selectedPit);
    }
}
