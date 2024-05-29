package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.FoodVoteDTO;
import de.szut.game2gather_backend.entity.FoodVote;
import de.szut.game2gather_backend.entity.UserVote;
import de.szut.game2gather_backend.repository.FoodVoteRepository;
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

public class FoodVoteService {
    //Service that is used to manage FoodVote-Objects in database

    private final FoodVoteRepository foodVoteRepository;
    private final VoteService voteService;

    public List<FoodVote> updateSessionVotesForSessionID(List<FoodVote> updatedSessionVotes, List<FoodVote> initialSessionVotes, int sessionID) {
        List<FoodVote> updateVotes = new ArrayList<>();
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

    private void deleteUpdatedSessionVotes(List<FoodVote> initialSessionFoodVotes, List<FoodVote> updatedSessionVotes, boolean updatedSessionVotesIsEmptyOrNull) {
        //iterate through userVotes of updatedSession
        for (FoodVote vote : initialSessionFoodVotes) {
            //delete vote that exists in initial Session if it doesn't exist on updated session
            if (updatedSessionVotesIsEmptyOrNull || !updatedSessionVotes.stream().map(FoodVote::getId).toList().contains(vote.getId())) {
                foodVoteRepository.delete(vote);
            }
        }
    }

    private List<FoodVote> saveAndGetUpdatedVotesForSessionID(int sessionID, List<FoodVote> updatedSessionVotes) {
        var updateVotes = new ArrayList<FoodVote>();
        //iterate through userVotes of updatedSession
        for (FoodVote vote : updatedSessionVotes) {
            var foodVote = foodVoteRepository.findById(vote.getId());
            //if vote isn't saved, save vote and write saved vote into session, else write already saved vote into session
            if (foodVote.isEmpty()) {
                updateVotes.add(saveVote(sessionID, vote));
            } else {
                updateVotes.add(foodVote.get());
            }
        }
        return updateVotes;
    }

    private FoodVote saveVote(int sessionId, FoodVote foodVote) {
        foodVote.setSession_id(sessionId);
        if (foodVote.getUserVotes() != null) {
            //save all userVotes that exist on vote
            voteService.saveVotesForVoteOption(foodVote.getUserVotes());
        }
        return foodVoteRepository.save(foodVote);
    }

    public void saveVotesForSessionID(List<FoodVote> votes, int sessionId) {
        for (var vote : votes) {
            saveVote(sessionId, vote);
        }
    }

    public FoodVoteDTO updateUserVotes(FoodVoteDTO vote) {
        var savedVote = foodVoteRepository.findById(vote.getId());
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
            return FoodVoteDTO.fromModel(foodVoteRepository.save(voteModel));
        } else {
            throw new RuntimeException("GameVote not found or no changes");
        }
    }
}