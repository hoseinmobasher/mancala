package com.bol.mancala.actions;

import com.bol.mancala.actions.base.Executor;
import com.bol.mancala.actions.executors.BoardPrinterExecutor;
import com.bol.mancala.actions.executors.PickCommandExecutor;
import com.bol.mancala.actions.executors.PickFinalizeExecutor;
import com.bol.mancala.actions.executors.PickValidatorExecutor;
import com.bol.mancala.domain.Game;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Command processor pattern which is used to run a chain of commands.
 */
@Service
public class PickChainExecutor {
    private final List<Executor<Integer>> executors;

    public PickChainExecutor() {
        executors = new LinkedList<>();
        executors.add(new PickValidatorExecutor());
        executors.add(new PickCommandExecutor());
        executors.add(new PickFinalizeExecutor());
        executors.add(new BoardPrinterExecutor());
    }

    public void execute(Game game, int selectedPit) {
        for (Executor<Integer> executor : executors) {
            executor.execute(game, selectedPit);
        }
    }
}
