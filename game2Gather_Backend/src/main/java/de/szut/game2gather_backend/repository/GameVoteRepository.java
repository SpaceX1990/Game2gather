package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.GameVote;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository-Interface to persist and manage Objects of type GameVote in database
public interface GameVoteRepository extends JpaRepository<GameVote, Integer> {
}
