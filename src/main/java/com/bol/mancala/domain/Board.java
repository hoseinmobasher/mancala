package com.bol.mancala.domain;

import com.bol.mancala.domain.enumeration.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Board {
    // The count of pits which each player could have.
    public static final int PIT_COUNT = 6;

    // The count of stones which each pit should have.
    public static final int STONE_COUNT = 6;

    // Total number of pits exist in a board.
    public static final int PIT_TOTAL_COUNT = PIT_COUNT * 2 + 2;

    // Each board has its own pits.
    private final List<Pit> pits;

    public Board() {
        this.pits = new ArrayList<>();

        // Initialize pits with 0 (big pit) or STONE_COUNT (small pit) of stones.
        for (var index = 0; index < PIT_TOTAL_COUNT; index++) {
            if (index == Player.PLAYER_1.getBigPit() || index == Player.PLAYER_2.getBigPit()) {
                pits.add(new Pit(index, 0));
            } else {
                pits.add(new Pit(index, STONE_COUNT));
            }
        }
    }

    // Retrieve the pit
    public Pit getPit(int index) {
        return pits.get(index);
    }

    // Retrieve all pits correspond with the given player.
    public List<Pit> getPits(Player player) {
        return pits.stream().skip(player.getBigPit() - PIT_COUNT).limit(PIT_COUNT).toList();
    }
}
