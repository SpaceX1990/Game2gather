package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.Genre;
import de.szut.game2gather_backend.entity.GenreEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    // List<GenreEnum> findByLabel(String label);
}
