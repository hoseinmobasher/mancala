package com.bol.mancala.services;

import com.bol.mancala.domain.Game;

import java.util.UUID;

public interface GameService {
    /**
     * Create an instance of game.
     *
     * @return newly created game
     */
    Game create();

    /**
     * Pick is used to play the turn with the given selectedPit.
     *
     * @param id game id
     * @param selectedPit pit id
     * @return game after pick.
     */
    Game pick(UUID id, int selectedPit);
}
