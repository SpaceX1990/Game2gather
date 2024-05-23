package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.GameVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameVoteRepository extends JpaRepository<GameVote, Integer> {
}
