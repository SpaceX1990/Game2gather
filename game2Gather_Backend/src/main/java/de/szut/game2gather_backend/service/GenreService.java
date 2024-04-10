package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.entity.Genre;
import de.szut.game2gather_backend.entity.GenreEnum;
import de.szut.game2gather_backend.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository repository;

   /* public List<GenreEnum> readAll() {
        return repository.findAll();
    }
    public Set<GenreEnum> readAllWithIds(List<String> labels) {
        List<GenreEnum> genres = labels.stream()
                .map(label -> repository.findByLabel(label))
                .collect(Collectors.toList());
    }*/

    public List<Genre> readAll() {
        return repository.findAll();
    }
}
