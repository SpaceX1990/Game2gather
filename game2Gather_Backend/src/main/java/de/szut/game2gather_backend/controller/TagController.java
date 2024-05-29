package de.szut.game2gather_backend.controller;


import de.szut.game2gather_backend.dto.TagDTO;
import de.szut.game2gather_backend.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//marks this class as a controller that maps requests that are sent
//to a specific url, to specific methods
@RestController

//declares the base url for the requests this controller will handle
@RequestMapping(value = "/api/tag")

//automatically creates a constructor for each field declared as final
//so that they can be injected from the springboot / bean context
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
