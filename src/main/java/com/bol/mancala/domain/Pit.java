package com.bol.mancala.domain;

import com.bol.mancala.domain.enumeration.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pit {
    /**
     * Each pit has an index which differs from other pits, and can be accessed via it.
     */
    private final int index;

    /**
     * A pit includes number of stones which initially
     * filled with {@code Board.STONE_COUNT} for the small pits, and 0 for big pits.
     */
    private int stones;

    /**
     * Helper function to determine the owner of pit.
     * @return the player who owns this pit.
     */
    @JsonIgnore
    public Player getOwner() {
        return index <= Player.PLAYER_1.getBigPit() ? Player.PLAYER_1 : Player.PLAYER_2;
    }

    /**
     * Helper function to determine either pit is big (store) or not.
     * @return true, if the pit is big (store), otherwise false.
     */
    @JsonIgnore
    public boolean isBigPit() {
        return index == Player.PLAYER_1.getBigPit() || index == Player.PLAYER_2.getBigPit();
    }

    @Override
    public String toString() {
        return Integer.toString(stones);
    }
}
