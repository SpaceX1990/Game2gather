package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.commands.CreateGameCommand;
import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;
    private final TagService tagService;

    public Optional<Game> read(int id) {
        return repository.findById(id);
    }

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
        game.setTags(tagService.readAllWithIds(createGameCommand.getTags()));
        return repository.save(game);
    }
}
