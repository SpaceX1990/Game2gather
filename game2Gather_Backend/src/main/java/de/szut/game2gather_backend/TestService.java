package de.szut.game2gather_backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository repository;

    public TestEntity save(TestEntity entity) {
        return repository.save(entity);
    }

    public List<TestEntity> readAll() {
        return repository.findAll();
    }
}
