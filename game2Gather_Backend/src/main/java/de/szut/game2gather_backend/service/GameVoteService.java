package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.GameVoteDTO;
import de.szut.game2gather_backend.entity.GameVote;
import de.szut.game2gather_backend.entity.UserVote;
import de.szut.game2gather_backend.repository.GameVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameVoteService {

    private final GameVoteRepository gameVoteRepository;
    private final VoteService voteService;

    public List<GameVote> updateSessionVotesForSessionID(List<GameVote> updatedSessionVotes, List<GameVote> initialSessionVotes, int sessionID) {
        List<GameVote> updateVotes = new ArrayList<>();
        var updatedSessionVotesIsEmptyOrNull = updatedSessionVotes == null || updatedSessionVotes.isEmpty();
        var initialSessionVotesIsEmptyOrNull = initialSessionVotes == null || initialSessionVotes.isEmpty();
        //if updated session gameVotes exists and aren't null
        if (!updatedSessionVotesIsEmptyOrNull) {
            updateVotes = saveAndGetUpdatedVotesForSessionID(sessionID, updatedSessionVotes);
        }
        //delete all gameVotes that don't exist in updated Session but in saved Session, if gameVotes in Session already exist
        if (!initialSessionVotesIsEmptyOrNull) {
            deleteUpdatedSessionVotes(initialSessionVotes, updatedSessionVotes, updatedSessionVotesIsEmptyOrNull);
        }
        return updateVotes;
    }


    private void deleteUpdatedSessionVotes(List<GameVote> initialSessionVotes, List<GameVote> updatedSessionVotes, boolean updatedSessionVotesIsEmptyOrNull) {
        //iterate through userVotes of updatedSession
        for (GameVote vote : initialSessionVotes) {
            //delete vote that exists in initial Session if it doesn't exist on updated session
            if (updatedSessionVotesIsEmptyOrNull || !updatedSessionVotes.stream().map(GameVote::getId).toList().contains(vote.getId())) {
                gameVoteRepository.delete(vote);
            }
        }
    }

    private List<GameVote> saveAndGetUpdatedVotesForSessionID(int sessionID, List<GameVote> updatedSessionVotes) {
        var updateVotes = new ArrayList<GameVote>();
        //iterate through userVotes of updatedSession
        for (GameVote vote : updatedSessionVotes) {
            var gameVote = gameVoteRepository.findById(vote.getId());
            //if vote isn't saved, save vote and write saved vote into session, else write already saved vote into session
            if (gameVote.isEmpty()) {
                updateVotes.add(saveVote(sessionID, vote));
            } else {
                updateVotes.add(gameVote.get());
            }
        }
        return updateVotes;
    }

    public GameVote saveVote(int sessionId, GameVote vote) {
        vote.setSession_id(sessionId);
        if (vote.getUserVotes() != null) {
            voteService.saveVotesForVoteOption(vote.getUserVotes());
        }
        return gameVoteRepository.save(vote);
    }

    public void saveVotesForSessionID(List<GameVote> votes, int sessionId) {
        for (var vote : votes) {
            saveVote(sessionId, vote);
        }
    }

    public GameVoteDTO updateUserVotes(GameVoteDTO vote) {
        var savedVote = gameVoteRepository.findById(vote.getId());
        if (savedVote.isPresent() && !savedVote.get().getUserVotes().equals(vote.getUserVotes())) {
            var voteModel = vote.toModel();
            if (voteModel.getUserVotes() != null) {
                for (UserVote userVote : voteModel.getUserVotes()) {
                    voteService.saveVote(userVote);
                }
            }
            return GameVoteDTO.fromModel(gameVoteRepository.save(voteModel));
        } else {
            throw new RuntimeException("GameVote not found or no changes");
        }
    }

}