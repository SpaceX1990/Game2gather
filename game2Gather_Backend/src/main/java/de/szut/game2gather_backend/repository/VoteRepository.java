package de.szut.game2gather_backend.repository;

import de.szut.game2gather_backend.entity.UserVote;
import org.springframework.data.repository.CrudRepository;

//JpaRepository-Interface to persist and manage Objects of type UserVote in database
public interface VoteRepository extends CrudRepository<UserVote, Integer> {
}
