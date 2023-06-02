package com.bol.mancala.domain;

import com.bol.mancala.domain.enumeration.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Board {
    public static final int PIT_COUNT = 6;
    public static final int STONE_COUNT = 4;
    public static final int PIT_TOTAL_COUNT = PIT_COUNT * 2 + 2;

    private final List<Pit> pits;

    public Board() {
        this.pits = new ArrayList<>();
        for (var index = 0; index < PIT_TOTAL_COUNT; index++) {
            if (index == Player.PLAYER_1.getStore() || index == Player.PLAYER_2.getStore()) {
                pits.add(new Pit(index, 0));
            } else {
                pits.add(new Pit(index, STONE_COUNT));
            }
        }
    }

    public Pit getPit(int index) {
        return pits.get(index);
    }

    public List<Pit> getPits(Player player) {
        return pits.stream().skip(player.getStore() - PIT_COUNT).limit(PIT_COUNT).toList();
    }
}
