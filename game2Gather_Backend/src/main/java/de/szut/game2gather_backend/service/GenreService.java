package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.GameDTO;
import de.szut.game2gather_backend.dto.GenreDTO;
import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.entity.Genre;
import de.szut.game2gather_backend.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class GenreService {


    private final GenreRepository repository;

    public List<GenreDTO> readAll() {
        List<Genre> genres = repository.findAll();
        return genres.stream().map(GenreDTO::ofEntity).toList();
    }
}
