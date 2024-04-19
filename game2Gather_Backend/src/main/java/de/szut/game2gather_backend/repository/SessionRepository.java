package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Integer> {
    List<Session> findAllByActiveTrue();
}
