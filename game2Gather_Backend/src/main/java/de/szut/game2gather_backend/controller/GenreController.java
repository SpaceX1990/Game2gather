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
    //controller to access and manage Genres in Database via incoming http-requests

    private final GenreService genreService;

    //get all existing genres by receiving a get-request on "{host}/api/genre"
    //and using the genreService to get all genres in the database
    @GetMapping()
    public List<GenreDTO> getAllGenres() {
        return genreService.readAll();
    }
}
