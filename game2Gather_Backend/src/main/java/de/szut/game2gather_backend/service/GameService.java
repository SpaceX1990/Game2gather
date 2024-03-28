package de.szut.game2gather_backend.service;

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

    public Optional<Game> read(int id) {
        return repository.findById(id);
    }

    public List<Game> readAll() {
        return repository.findAll();
    }
}
