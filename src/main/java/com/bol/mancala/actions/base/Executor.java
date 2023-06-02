package com.bol.mancala.actions.base;

import com.bol.mancala.domain.Game;

public abstract class Executor<T> {
    public Executor<T> next;

    public abstract void executeInternal(Game game, T data);

    public void execute(Game game, T data) {
        executeInternal(game, data);
        if (next != null) {
            next.execute(game, data);
        }
    }

    public Executor<T> next(Executor<T> next) {
        return this.next = next;
    }
}
