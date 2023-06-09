package com.bol.mancala.actions.executors;

import com.bol.mancala.actions.base.Executor;
import com.bol.mancala.domain.Game;
import com.bol.mancala.domain.enumeration.Player;
import lombok.extern.log4j.Log4j2;

import static com.bol.mancala.domain.Board.PIT_TOTAL_COUNT;

/**
 * Print the current state of the given game.
 */
@Log4j2
public class BoardPrinterExecutor implements Executor<Integer> {
    @Override
    public void execute(Game game, Integer selectedPit) {
        log.info("=================================================");

        StringBuilder player2Row = new StringBuilder();
        player2Row.append("PLAYER_2");
        for (var index = Player.PLAYER_2.getBigPit(); index >= PIT_TOTAL_COUNT / 2; index--) {
            player2Row.append("\t");
            if (index == PIT_TOTAL_COUNT - 1) {
                player2Row.append("[ ");
            }

            player2Row.append(game.getBoard().getPit(index));
            if (index == PIT_TOTAL_COUNT - 1) {
                player2Row.append("]");
            }
        }

        log.info(player2Row.toString());
        log.info("-------------------------------------------------");

        StringBuilder player1Row = new StringBuilder();
        player1Row.append("PLAYER_1").append("\t\t");
        for (var index = 0; index <= Player.PLAYER_1.getBigPit(); index++) {
            player1Row.append("\t");
            if (index == Player.PLAYER_1.getBigPit()) {
                player1Row.append("[ ");
            }

            player1Row.append(game.getBoard().getPit(index));
            if (index == Player.PLAYER_1.getBigPit()) {
                player1Row.append("]");
            }
        }

        log.info(player1Row.toString());
        log.info("=================================================");
    }
}
