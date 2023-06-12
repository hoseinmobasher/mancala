package com.bol.mancala.domain.enumeration;

import static com.bol.mancala.domain.Board.PIT_TOTAL_COUNT;

/**
 * Used to define a player, and its own big pit.
 */
public enum Player {
    PLAYER_1(PIT_TOTAL_COUNT / 2 - 1),
    PLAYER_2(PIT_TOTAL_COUNT - 1);

    private final int bigPit;

    Player(int bigPit) {
        this.bigPit = bigPit;
    }

    public int getBigPit() {
        return bigPit;
    }
}
