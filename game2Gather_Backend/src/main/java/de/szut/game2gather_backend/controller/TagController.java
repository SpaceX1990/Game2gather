package de.szut.game2gather_backend.controller;

import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.entity.Tag;
import de.szut.game2gather_backend.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping()
    public List<Tag> getAll() {
        return tagService.readAll();
    }
}
