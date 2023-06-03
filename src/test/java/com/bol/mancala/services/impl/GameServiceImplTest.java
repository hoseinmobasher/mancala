package com.bol.mancala.services.impl;

import com.bol.mancala.actions.PickChainExecutor;
import com.bol.mancala.domain.Game;
import com.bol.mancala.exceptions.types.GameNotFoundException;
import com.bol.mancala.repositories.GameRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GameServiceImplTest {
    @Mock
    private GameRepository gameRepository;

    @Spy
    private PickChainExecutor pickChainExecutor;

    @InjectMocks
    private GameServiceImpl gameService;

    @Test
    public void testCreateGame() {
        var game = new Game();
        when(gameRepository.saveOrUpdate(any())).thenReturn(game);

        assertEquals(game, gameService.create());
    }

    @Test
    public void testPickWhenGameIsNotFound() {
        when(gameRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(GameNotFoundException.class, () -> gameService.pick(UUID.randomUUID(), 0));
    }

    @Test
    public void testPickWhenGameExists() {
        var game = new Game();
        when(gameRepository.findById(any())).thenReturn(Optional.of(game));
        when(gameRepository.saveOrUpdate(any())).thenReturn(game);
        doNothing().when(pickChainExecutor).execute(any(), anyInt());

        assertEquals(game.getId(), gameService.pick(game.getId(), 0).getId());
    }
}
