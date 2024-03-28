package de.szut.game2gather_backend.controller;

import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping(value = "/{id}")
    public Optional<Game> getGame(@PathVariable("id") int id) {
        return gameService.read(id);
    }

    @GetMapping()
    public List<Game> getAll() {
        return gameService.readAll();
    }

}
