package com.bol.mancala.repositories.impl;

import com.bol.mancala.domain.Game;
import com.bol.mancala.repositories.GameRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryGameRepository implements GameRepository {
    private final Map<UUID, Game> gameMap = new ConcurrentHashMap<>();

    @Override
    public Game saveOrUpdate(Game entity) {
        gameMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Game> findById(UUID id) {
        return Optional.ofNullable(gameMap.get(id));
    }
}
