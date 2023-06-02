package com.bol.mancala.repositories;

import com.bol.mancala.domain.Game;

import java.util.Optional;
import java.util.UUID;

public interface GameRepository {
    Game saveOrUpdate(Game entity);

    Optional<Game> findById(UUID id);
}
