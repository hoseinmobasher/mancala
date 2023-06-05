package com.bol.mancala.actions.base;

import com.bol.mancala.domain.Game;

public interface Executor<T> {
    void execute(Game game, T data);
}
