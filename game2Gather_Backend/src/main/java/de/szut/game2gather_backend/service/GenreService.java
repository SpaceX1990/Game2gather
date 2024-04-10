package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.GenreDTO;
import de.szut.game2gather_backend.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class GenreService {


    public List<GenreDTO> readAll() {
        return Stream.of(Genre.values())
                .map(genre -> new GenreDTO(genre.name(), genre.getLabel()))
                .toList();
    }
}
