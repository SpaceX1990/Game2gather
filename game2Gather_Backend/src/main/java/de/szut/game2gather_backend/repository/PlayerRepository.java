package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
