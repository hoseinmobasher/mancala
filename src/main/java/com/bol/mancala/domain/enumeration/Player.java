package com.bol.mancala.domain.enumeration;

import static com.bol.mancala.domain.Board.PIT_TOTAL_COUNT;

public enum Player {
    PLAYER_1(PIT_TOTAL_COUNT / 2 - 1),
    PLAYER_2(PIT_TOTAL_COUNT - 1);

    private final int store;

    Player(int store) {
        this.store = store;
    }

    public int getStore() {
        return store;
    }
}
