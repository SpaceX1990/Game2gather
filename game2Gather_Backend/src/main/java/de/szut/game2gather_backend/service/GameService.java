package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.GameDTO;
import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//marks this class so that a bean that gets created on application-build
//and that can then be injected into other useCases via Autowiring or Constructor injection
@Service

//automatically creates a constructor for each field declared as final as parameter
//so that they can be injected from the springboot / bean context
@RequiredArgsConstructor

public class GameService {
    //Service that is used to manage Game-Objects in database

    private final GameRepository repository;

    //get all Game-Objects in database and return them as DTOs
    public List<GameDTO> readAll() {
        List<Game> games = repository.findAll();
        return games.stream().map(GameDTO::fromModel).toList();
    }

    //delete the game that has the id
    public void delete(int id) {
        repository.deleteById(id);
    }

    //create a new game from a GameDTO
    public GameDTO create(GameDTO gameDTO) {
        return GameDTO.fromModel(repository.save(gameDTO.toModel()));
    }

    //get single Game that has the id
    public Optional<Game> read(int id) {
        return repository.findById(id);
    }

    //update an existing Game by inserting the updated version with the same id
    public Game update(Game game) {
        return repository.save(game);
    }
}
