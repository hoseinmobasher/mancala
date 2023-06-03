package com.bol.mancala.controllers;

import com.bol.mancala.domain.Board;
import com.bol.mancala.domain.enumeration.Player;
import com.bol.mancala.services.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static com.bol.mancala.domain.Board.STONE_COUNT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameService gameService;

    @Test
    public void gameApi_WhenIsCalled_StatusIsCreatedAndContentIsSetWithValidValues() throws Exception {
        ResultActions result = mockMvc.perform(post("/game"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value("IN_PROGRESS"));

        for (var index = 0; index < Board.PIT_TOTAL_COUNT; index++) {
            if (index == Player.PLAYER_2.getStore() || index == Player.PLAYER_1.getStore()) {
                result.andExpect(MockMvcResultMatchers.jsonPath("$.board.pits[" + index + "].index").value(index));
                result.andExpect(MockMvcResultMatchers.jsonPath("$.board.pits[" + index + "].stones").value(0));
            } else {
                result.andExpect(MockMvcResultMatchers.jsonPath("$.board.pits[" + index + "].index").value(index));
                result.andExpect(MockMvcResultMatchers.jsonPath("$.board.pits[" + index + "].stones").value(STONE_COUNT));
            }
        }
    }

    @Test
    public void pickApi_GivenValidId_StatusIsOkAndReturnGameWithSameId() throws Exception {
        var game = gameService.create();

        mockMvc.perform(put("/game/" + game.getId().toString() + "/pick/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(game.getId().toString()));
    }

    @Test
    public void pickApi_GivenInvalidId_StatusIsNotFound() throws Exception {
        mockMvc.perform(put("/game/" + UUID.randomUUID().toString() + "/pick/1"))
                .andExpect(status().isNotFound());
    }
}
