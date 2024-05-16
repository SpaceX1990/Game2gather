package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {
}
