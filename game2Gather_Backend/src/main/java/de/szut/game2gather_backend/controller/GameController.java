package de.szut.game2gather_backend.controller;

import de.szut.game2gather_backend.commands.CreateGameCommand;
import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping()
    public List<Game> getAll() {
        return gameService.readAll();
    }

    @PostMapping()
    public Game create(@RequestBody CreateGameCommand createGameCommand) {
        return gameService.create(createGameCommand);
    }

}
