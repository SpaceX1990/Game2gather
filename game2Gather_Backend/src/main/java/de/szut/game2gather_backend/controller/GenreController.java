package de.szut.game2gather_backend.controller;


import de.szut.game2gather_backend.dto.GenreDTO;
import de.szut.game2gather_backend.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping()
    public List<GenreDTO> getAllGenres() {
        return genreService.readAll();
    }
}
