package com.bol.mancala.services;

import com.bol.mancala.domain.Game;

import java.util.UUID;

public interface GameService {
    Game create();

    Game pick(UUID id, int selectedPit);
}
