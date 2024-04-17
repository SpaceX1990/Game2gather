package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
