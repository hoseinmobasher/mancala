package com.bol.mancala.actions.base;

import com.bol.mancala.domain.Game;

/**
 * Command pattern
 * @param <T> data should be used in the underlying commands.
 */
public interface Executor<T> {
    void execute(Game game, T data);
}
