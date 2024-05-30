package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.entity.UserVote;
import de.szut.game2gather_backend.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteService {
    //Service that is used to manage UserVote-Objects in database

    private final VoteRepository voteRepository;

    //save a list of userVotes to the database
    public void saveVotesForVoteOption(List<UserVote> gameUserVote) {
        voteRepository.saveAll(gameUserVote);
    }

    //save a single userVote to the database if it doesn't exist already or update
    //if it's different to the already saved version
    public void saveVote(UserVote userVote) {
        if (userVote.getId() != null) {
            var savedVote = voteRepository.findById(userVote.getId());
            if (savedVote.isPresent() && savedVote.get().equals(userVote)) {
                return;
            }
        }
        voteRepository.save(userVote);
    }
}
