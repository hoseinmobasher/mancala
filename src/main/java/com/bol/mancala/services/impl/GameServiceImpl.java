package com.bol.mancala.services.impl;

import com.bol.mancala.domain.Game;
import com.bol.mancala.repositories.GameRepository;
import com.bol.mancala.services.GameService;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository repository;

    public GameServiceImpl(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public Game create() {
        Game game = new Game();
        return repository.saveOrUpdate(game);
    }
}
