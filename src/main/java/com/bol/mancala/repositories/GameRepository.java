package com.bol.mancala.repositories;

import com.bol.mancala.domain.Game;

import java.util.Optional;
import java.util.UUID;

public interface GameRepository {
    /**
     * Used to save or update the given entity.
     *
     * @param entity a game to be saved.
     * @return the saved game.
     */
    Game saveOrUpdate(Game entity);

    /**
     * Retrieve the game with given id.
     *
     * @param id game id
     * @return the corresponding game which was saved into repository.
     */
    Optional<Game> findById(UUID id);
}
