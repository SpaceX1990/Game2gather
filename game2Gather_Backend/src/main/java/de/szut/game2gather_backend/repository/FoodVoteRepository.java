package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.FoodVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodVoteRepository extends JpaRepository<FoodVote, Integer> {
}
