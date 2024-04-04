package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.commands.CreateGameCommand;
import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;

    public List<Game> readAll() {
        return repository.findAll();
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
    public Game create(@RequestBody CreateGameCommand createGameCommand) {
        Game game = new Game();
        game.setId(createGameCommand.getId());
        game.setMinPlayer(createGameCommand.getMinimumPlayers());
        game.setMaxPlayer(createGameCommand.getMaximumPlayers());
        game.setTitle(createGameCommand.getTitle());
        return repository.save(game);
    }
}
