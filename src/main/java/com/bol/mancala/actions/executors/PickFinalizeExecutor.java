package com.bol.mancala.actions.executors;

import com.bol.mancala.actions.base.Executor;
import com.bol.mancala.domain.Board;
import com.bol.mancala.domain.Game;
import com.bol.mancala.domain.Pit;
import com.bol.mancala.domain.enumeration.Player;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PickFinalizeExecutor implements Executor<Integer> {
    @Override
    public void execute(Game game, Integer selectedPit) {
        int p1Count = game.getBoard().getPits(Player.PLAYER_1).stream().map(Pit::getStones).reduce(0, Integer::sum);
        int p2Count = game.getBoard().getPits(Player.PLAYER_2).stream().map(Pit::getStones).reduce(0, Integer::sum);

        if (p1Count == 0 || p2Count == 0) {
            if (p1Count == 0) {
                Pit p2Store = game.getBoard().getPit(Player.PLAYER_2.getStore());
                p2Store.setStones(p2Store.getStones() + p2Count);
            }

            if (p2Count == 0) {
                Pit p1Store = game.getBoard().getPit(Player.PLAYER_1.getStore());
                p1Store.setStones(p1Store.getStones() + p1Count);
            }

            determineWinner(game);
            closeGame(game);
        }
    }

    private void determineWinner(Game game) {
        int p1Stones = game.getBoard().getPit(Player.PLAYER_1.getStore()).getStones();
        int p2Stones = game.getBoard().getPit(Player.PLAYER_2.getStore()).getStones();

        if (p1Stones > p2Stones) {
            game.setWinner(Player.PLAYER_1);
        } else if (p1Stones < p2Stones) {
            game.setWinner(Player.PLAYER_2);
        }

        if (game.getWinner() != null) {
            log.debug("Winner is determined: {}", game.getWinner());
        } else {
            log.debug("Draw match.");
        }
    }

    private void closeGame(Game game) {
        for (var index = 0; index < Board.PIT_TOTAL_COUNT; index++) {
            if (index == Player.PLAYER_1.getStore() || index == Player.PLAYER_2.getStore()) {
                continue;
            }

            game.getBoard().getPit(index).setStones(0);
        }
    }
}
