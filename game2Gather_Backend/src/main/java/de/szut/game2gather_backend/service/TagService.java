package de.szut.game2gather_backend.service;


import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.entity.Tag;
import de.szut.game2gather_backend.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository repository;

    public List<Tag> readAll() {
        return repository.findAll();
    }

    public List<Tag> readAllWithIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return repository.findAllById(ids);
    }
}
