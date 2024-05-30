package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.GenreDTO;
import de.szut.game2gather_backend.entity.Genre;
import de.szut.game2gather_backend.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//marks this class so that a bean that gets created on application-build
//and that can then be injected into other useCases via Autowiring or Constructor injection
@Service

//automatically creates a constructor for each field declared as final as parameter
//so that they can be injected from the springboot / bean context
@RequiredArgsConstructor

public class GenreService {
    //Service that is used to manage Genre-Objects in database

    private final GenreRepository repository;

    //get all Genre-Objects in database and return them as DTOs
    public List<GenreDTO> readAll() {
        List<Genre> genres = repository.findAll();
        return genres.stream().map(GenreDTO::fromModel).toList();
    }
}
