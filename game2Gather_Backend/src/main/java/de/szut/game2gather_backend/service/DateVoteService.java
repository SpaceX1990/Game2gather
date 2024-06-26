package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.DateVoteDTO;
import de.szut.game2gather_backend.entity.DateVote;
import de.szut.game2gather_backend.entity.UserVote;
import de.szut.game2gather_backend.repository.DateVoteRepository;
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

public class DateVoteService {
    //Service that is used to manage DateVote-Objects in database

    private final DateVoteRepository dateVoteRepository;
    private final VoteService voteService;

    public List<DateVote> updateSessionVotesForSessionID(List<DateVote> updatedSessionVotes, List<DateVote> initialSessionVotes, int sessionID) {
        List<DateVote> updateVotes = new ArrayList<>();
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

    private void deleteUpdatedSessionVotes(List<DateVote> initialSessionVotes, List<DateVote> updatedSessionVotes, boolean updatedSessionVotesIsEmptyOrNull) {
        //iterate through userVotes of updatedSession
        for (DateVote vote : initialSessionVotes) {
            //delete vote that exists in initial Session if it doesn't exist on updated session
            if (updatedSessionVotesIsEmptyOrNull || !updatedSessionVotes.stream().map(DateVote::getId).toList().contains(vote.getId())) {
                dateVoteRepository.delete(vote);
            }
        }
    }

    private List<DateVote> saveAndGetUpdatedVotesForSessionID(int sessionID, List<DateVote> updatedSessionVotes) {
        var updateVotes = new ArrayList<DateVote>();
        //iterate through userVotes of updatedSession
        for (DateVote vote : updatedSessionVotes) {
            var dateVote = dateVoteRepository.findById(vote.getId());
            //if vote isn't saved, save vote and write saved vote into session, else write already saved vote into session
            if (dateVote.isEmpty()) {
                updateVotes.add(saveVote(sessionID, vote));
            } else {
                updateVotes.add(dateVote.get());
            }
        }
        return updateVotes;
    }

    public DateVote saveVote(int sessionID, DateVote vote) {
        vote.setSession_id(sessionID);
        if (vote.getUserVotes() != null) {
            //save all userVotes that exist on vote
            voteService.saveVotesForVoteOption(vote.getUserVotes());
        }
        return dateVoteRepository.save(vote);
    }

    public void saveVotesForSessionID(List<DateVote> votes, int sessionId) {
        for (var vote : votes) {
            saveVote(sessionId, vote);
        }
    }

    public DateVoteDTO updateUserVotes(DateVoteDTO vote) {
        var savedVote = dateVoteRepository.findById(vote.getId());
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
            return DateVoteDTO.fromModel(dateVoteRepository.save(voteModel));
        } else {
            throw new RuntimeException("GameVote not found or no changes");
        }
    }
}
