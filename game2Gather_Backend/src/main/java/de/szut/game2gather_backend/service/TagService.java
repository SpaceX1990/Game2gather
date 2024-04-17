package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.entity.Tag;
import de.szut.game2gather_backend.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository repository;

    public List<Tag> readAll() {
        return repository.findAll();
    }
}
