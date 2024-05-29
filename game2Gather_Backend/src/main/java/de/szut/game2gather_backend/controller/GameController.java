package de.szut.game2gather_backend.controller;

import de.szut.game2gather_backend.dto.GameDTO;
import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//marks this class as a controller that maps requests that are sent
//to a specific url, to specific methods
@RestController

//declares the base url for the requests this controller will handle
@RequestMapping(value = "/api/game")

//automatically creates a constructor for each field declared as final
//so that they can be injected from the springboot / bean context
@RequiredArgsConstructor

public class GameController {
    //controller to access and manage Games in Database via incoming http-requests

    private final GameService gameService;

    //get all existing games by receiving a get-request on "{host}/api/game" and using the gameService to get all games in the database
    @GetMapping()
    public List<GameDTO> getAll() {
        return gameService.readAll();
    }

    //get the game that has the id, which is passed via url-parameter,
    //by receiving a get-request on "{host}/api/game/:id", where id represents the url-parameter
    //and using the gameService to get the matching game from the database
    @GetMapping("/{id}")
    public Optional<Game> get(@PathVariable int id) {
        return gameService.read(id);
    }

    //delete the game that has the id, which is passed via url-parameter,
    //by receiving a get-request on "{host}/api/game/delete/:id", where id represents the url-parameter
    //and using the gameService to delete the matching game from the database
    @DeleteMapping("/delete/{id}")
    public void deleteGame(@PathVariable int id) {
        gameService.delete(id);
    }

    //create the game that is passed via the body of the received request
    //by receiving a post-request on "{host}/api/game", where a request-body is transmitted
    //and using the gameService to create the game that is transmitted in the database
    @PostMapping()
    public GameDTO create(@RequestBody GameDTO gameDTO) {
        return gameService.create(gameDTO);
    }

    //update the game that is passed via the body of the received request
    //by receiving a put-request on "{host}/api/game", where a request-body is transmitted
    //and using the gameService to update the game, that is transmitted, in the database
    @PutMapping()
    public Game update(@RequestBody Game game) {
        return gameService.update(game);
    }
}
