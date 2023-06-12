package com.bol.mancala.exceptions.types;

import com.bol.mancala.domain.enumeration.GameState;
import lombok.Getter;

/**
 * Game could be played whenever state is IN_PROGRESS.
 */
@Getter
public class IllegalGameStateException extends RuntimeException {
    private final GameState state;

    public IllegalGameStateException(GameState state) {
        this.state = state;
    }
}
