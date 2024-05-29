package de.szut.game2gather_backend.service;


import de.szut.game2gather_backend.dto.TagDTO;
import de.szut.game2gather_backend.entity.Tag;
import de.szut.game2gather_backend.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//marks this class so that a bean that gets created on application-build
//and that can then be injected into other useCases via Autowiring or Constructor injection
@Service

//automatically creates a constructor for each field declared as final as parameter
//so that they can be injected from the springboot / bean context
@RequiredArgsConstructor

public class TagService {
    //Service that is used to manage Tag-Objects in database

    private final TagRepository repository;

    //get all Tag-Objects in database and return them as DTOs
    public List<TagDTO> readAll() {
        List<Tag> tags = repository.findAll();
        return tags.stream().map(TagDTO::fromModel).toList();
    }
}
