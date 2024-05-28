package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.entity.Vote;
import de.szut.game2gather_backend.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteService {

    private VoteRepository voteRepository;

    public void saveVotesForVoteOption(List<Vote> gameVote) {
        voteRepository.saveAll(gameVote);
    }
}
