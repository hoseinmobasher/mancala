package com.bol.mancala.exceptions.types;

import com.bol.mancala.domain.enumeration.Player;
import lombok.Getter;

/**
 * This exception will be thrown in case of invalid turn.
 * Each player should be played in their desired turn.
 */
@Getter
public class IncorrectTurnException extends RuntimeException {
    private final Player turn;

    public IncorrectTurnException(Player turn) {
        this.turn = turn;
    }
}
