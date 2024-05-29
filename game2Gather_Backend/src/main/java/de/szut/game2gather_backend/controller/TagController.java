package de.szut.game2gather_backend.controller;


import de.szut.game2gather_backend.dto.TagDTO;
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
    //controller to access and manage Tags in Database via incoming http-requests

    private final TagService tagService;

    //get all existing tags by receiving a get-request on "{host}/api/tag"
    //and using the tagService to get all genres in the database
    @GetMapping()
    public List<TagDTO> getAllTags() {
        return tagService.readAll();
    }
}
