package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.CreateGameDTO;
import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;
    private final TagService tagService;
    private final GenreService genreService;

    public List<Game> readAll() {
        return repository.findAll();
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
    public Game create(CreateGameDTO createGameDTO) {
        Game game = new Game();
        game.setId(createGameDTO.getId());
        game.setMinPlayer(createGameDTO.getMinimumPlayers());
        game.setMaxPlayer(createGameDTO.getMaximumPlayers());
        game.setTitle(createGameDTO.getTitle());
        game.setTags(tagService.readAllWithIds(createGameDTO.getTags()));
        game.setGenre(createGameDTO.getGenre());
        return repository.save(game);
    }
}
