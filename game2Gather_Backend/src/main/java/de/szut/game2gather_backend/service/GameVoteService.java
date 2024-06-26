package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.GameVoteDTO;
import de.szut.game2gather_backend.entity.GameVote;
import de.szut.game2gather_backend.entity.UserVote;
import de.szut.game2gather_backend.repository.GameVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//marks this class so that a bean that gets created on application-build
//and that can then be injected into other useCases via Autowiring or Constructor injection
@Service

//automatically creates a constructor for each field declared as final as parameter
//so that they can be injected from the springboot / bean context
@RequiredArgsConstructor

public class GameVoteService {
    //Service that is used to manage GameVote-Objects in database

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
            //save all userVotes that exist on vote
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
        //if the vote already exists and the saved version is different
        if (savedVote.isPresent() && !savedVote.get().getUserVotes().equals(vote.getUserVotes())) {
            var voteModel = vote.toModel();
            //if there exist userVotes on the updated vote
            if (voteModel.getUserVotes() != null) {
                //save each userVote that exists in vote
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