package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.GameDTO;
import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;

    public List<GameDTO> readAll() {
        List<Game> games = repository.findAll();
        return games.stream().map(GameDTO::fromModel).toList();
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public GameDTO create(GameDTO gameDTO) {
        return GameDTO.fromModel(repository.save(gameDTO.toModel()));
    }

    public Optional<Game> read(int id) {
        return repository.findById(id);
    }

    public Game update(Game game) {
        return repository.save(game);
    }
}
