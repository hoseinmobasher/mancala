package com.bol.mancala.controllers;

import com.bol.mancala.domain.Game;
import com.bol.mancala.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame() {
        return service.create();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/pick/{selectedPit}")
    public Game pick(@PathVariable UUID id, @PathVariable int selectedPit) {
        return service.pick(id, selectedPit);
    }
}
