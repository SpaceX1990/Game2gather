package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository-Interface to persist and manage Objects of type Game in database
public interface GameRepository extends JpaRepository<Game, Integer> {
}
