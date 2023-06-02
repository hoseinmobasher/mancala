package com.bol.mancala.domain;

import com.bol.mancala.domain.enumeration.GameState;
import com.bol.mancala.domain.enumeration.Player;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Game {
    @EqualsAndHashCode.Include
    private final UUID id;
    private final Board board;

    private GameState state;
    private Player turn;
    private Player winner;

    public Game() {
        id = UUID.randomUUID();
        board = new Board();

        state = GameState.IN_PROGRESS;
        turn = Player.PLAYER_1;
        winner = null;
    }
}
