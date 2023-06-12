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
    /**
     * Each game includes a unique identifier (UUID).
     */
    @EqualsAndHashCode.Include
    private final UUID id;

    /**
     * Each game played on a board.
     */
    private final Board board;

    /**
     * State of the game which could be either IN_PROGRESS or FINISHED.
     */
    private GameState state;

    /**
     * Which player should be played for current pick.
     */
    private Player turn;

    /**
     * The winner of the game which will be determined in end of the game.
     */
    private Player winner;

    public Game() {
        id = UUID.randomUUID();
        board = new Board();

        state = GameState.IN_PROGRESS;
        turn = Player.PLAYER_1;
        winner = null;
    }
}
