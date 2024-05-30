package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.FoodVote;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository-Interface to persist and manage Objects of type DateVote in database
public interface FoodVoteRepository extends JpaRepository<FoodVote, Integer> {
}
