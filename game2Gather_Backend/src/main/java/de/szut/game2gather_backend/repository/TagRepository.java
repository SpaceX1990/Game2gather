package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository-Interface to persist and manage Objects of type Tag in database
public interface TagRepository extends JpaRepository<Tag, Integer> {
}
