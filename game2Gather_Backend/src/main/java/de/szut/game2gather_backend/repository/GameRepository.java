package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameRepository extends JpaRepository<Game, Integer> {
}
