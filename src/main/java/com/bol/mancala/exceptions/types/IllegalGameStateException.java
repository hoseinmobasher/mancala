package com.bol.mancala.exceptions.types;

import com.bol.mancala.domain.enumeration.GameState;
import lombok.Getter;

@Getter
public class IllegalGameStateException extends RuntimeException {
    private final GameState state;

    public IllegalGameStateException(GameState state) {
        this.state = state;
    }
}
