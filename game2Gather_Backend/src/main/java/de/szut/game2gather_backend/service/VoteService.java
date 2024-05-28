package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.entity.UserVote;
import de.szut.game2gather_backend.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    public void saveVotesForVoteOption(List<UserVote> gameUserVote) {
        voteRepository.saveAll(gameUserVote);
    }

    public void saveVote(UserVote userVote) {
        var savedVote = voteRepository.findById(userVote.getId());
        if (savedVote.isEmpty() || !savedVote.get().equals(userVote)) {
            voteRepository.save(userVote);
        }
    }
}
