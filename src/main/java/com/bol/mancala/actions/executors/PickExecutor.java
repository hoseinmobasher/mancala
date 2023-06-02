package com.bol.mancala.actions.executors;

import com.bol.mancala.actions.base.Executor;
import com.bol.mancala.domain.Board;
import com.bol.mancala.domain.Game;
import com.bol.mancala.domain.Pit;
import com.bol.mancala.domain.enumeration.Player;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PickExecutor extends Executor<Integer> {
    @Override
    public void executeInternal(Game game, Integer selectedPit) {
        var lastIndex = sowStones(game, selectedPit);
        captureStones(game, lastIndex);
        changeTurn(game, lastIndex);
    }

    private int sowStones(Game game, int selectedPit) {
        var pit = game.getBoard().getPit(selectedPit);
        var stones = pit.getStones();
        pit.setStones(0);

        int index = selectedPit;
        while (stones > 0) {
            index = ++index % Board.PIT_TOTAL_COUNT;
            var next = game.getBoard().getPit(index);
            if (pit.getOwner() != next.getOwner() && next.isStore()) {
                log.debug("Opponent store[{}] is ignored.", next.getIndex());
                continue;
            }

            next.setStones(next.getStones() + 1);
            stones--;
        }

        return index;
    }

    private void captureStones(Game game, int lastIndex) {
        var lastPit = game.getBoard().getPit(lastIndex);
        if (!lastPit.isStore()
                && lastPit.getOwner() == game.getTurn()
                && lastPit.getStones() == 1) {
            var oppositePit = game.getBoard().getPit(Board.PIT_COUNT * 2 - lastIndex);
            if (oppositePit.getStones() != 0) {
                log.debug("{} is going to capture {} stones from {}.", game.getTurn(),
                        oppositePit.getStones(), oppositePit.getOwner());

                Pit storePit = game.getBoard().getPit(game.getTurn().getStore());
                storePit.setStones(storePit.getStones() + lastPit.getStones() + oppositePit.getStones());

                lastPit.setStones(0);
                oppositePit.setStones(0);

            }
        }
    }

    private void changeTurn(Game game, int lastIndex) {
        var lastPit = game.getBoard().getPit(lastIndex);
        if (lastPit.isStore() && game.getTurn() == lastPit.getOwner()) {
            log.debug("{} got a free turn.", game.getTurn());
            return;
        }

        if (Player.PLAYER_1 == game.getTurn()) {
            game.setTurn(Player.PLAYER_2);
        } else {
            game.setTurn(Player.PLAYER_1);
        }

        log.debug("Turn is changed to {}.", game.getTurn());
    }
}
