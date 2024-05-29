package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository-Interface to persist and manage Objects of type Session in database
public interface SessionRepository extends JpaRepository<Session, Integer> {
    List<Session> findByActiveTrue();
    List<Session> findByActiveFalse();
}
