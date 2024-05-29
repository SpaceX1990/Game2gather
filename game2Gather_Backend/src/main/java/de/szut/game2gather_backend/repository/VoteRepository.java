package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.UserVote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<UserVote, Integer> {
}
