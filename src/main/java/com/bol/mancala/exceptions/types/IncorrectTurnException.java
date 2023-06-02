package com.bol.mancala.exceptions.types;

import com.bol.mancala.domain.enumeration.Player;
import lombok.Getter;

@Getter
public class IncorrectTurnException extends RuntimeException {
    private final Player turn;

    public IncorrectTurnException(Player turn) {
        this.turn = turn;
    }
}
