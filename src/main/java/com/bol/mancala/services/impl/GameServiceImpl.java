package com.bol.mancala.services.impl;

import com.bol.mancala.actions.PickChainExecutor;
import com.bol.mancala.domain.Game;
import com.bol.mancala.exceptions.types.GameNotFoundException;
import com.bol.mancala.repositories.GameRepository;
import com.bol.mancala.services.GameService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository repository;
    private final PickChainExecutor pickChainExecutor;

    public GameServiceImpl(GameRepository repository, PickChainExecutor pickChainExecutor) {
        this.repository = repository;
        this.pickChainExecutor = pickChainExecutor;
    }

    /**
     *
     * @return This method returns an object from Game.
     */
    @Override
    public Game create() {
        var game = new Game();
        return repository.saveOrUpdate(game);
    }

    /**
     * @param id          ID for corresponding game.
     * @param selectedPit Pit is chosen to apply the pick action.
     */
    @Override
    public Game pick(UUID id, int selectedPit) {
        var game = repository.findById(id).orElseThrow(GameNotFoundException::new);
        pickChainExecutor.execute(game, selectedPit - 1);
        return repository.saveOrUpdate(game);
    }
}
