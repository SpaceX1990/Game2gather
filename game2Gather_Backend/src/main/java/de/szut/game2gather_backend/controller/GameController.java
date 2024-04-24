package de.szut.game2gather_backend.controller;

import de.szut.game2gather_backend.dto.GameDTO;
import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping()
    public List<GameDTO> getAll() {
        return gameService.readAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGame(@PathVariable int id) {
        gameService.delete(id);
    }

    @PostMapping()
    public GameDTO create(@RequestBody GameDTO gameDTO) {
        return gameService.create(gameDTO);
    }
}
