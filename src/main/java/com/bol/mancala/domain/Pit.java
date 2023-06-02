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
    private final int index;
    private int stones;

    @JsonIgnore
    public Player getOwner() {
        return index <= Player.PLAYER_1.getStore() ? Player.PLAYER_1 : Player.PLAYER_2;
    }

    @JsonIgnore
    public boolean isStore() {
        return index == Player.PLAYER_1.getStore() || index == Player.PLAYER_2.getStore();
    }

    @Override
    public String toString() {
        return Integer.toString(stones);
    }
}
