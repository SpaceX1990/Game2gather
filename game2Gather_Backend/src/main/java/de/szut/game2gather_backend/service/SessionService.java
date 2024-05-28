package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.SessionDTO;
import de.szut.game2gather_backend.entity.*;
import de.szut.game2gather_backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final GameVoteRepository gameVoteRepository;
    private final FoodVoteRepository foodVoteRepository;
    private final DateVoteRepository dateVoteRepository;
    private final VoteRepository voteRepository;

    @GetMapping

    public List<SessionDTO> getAllActiveSession() {
        return sessionRepository.findByActiveTrue().stream().map(SessionDTO::ofModel).toList();
    }

    public List<SessionDTO> getAllPastSession() {
        return sessionRepository.findByActiveFalse().stream().map(SessionDTO::ofModel).toList();
    }

    public List<SessionDTO> readAll() {
        return sessionRepository.findAll().stream().map(SessionDTO::ofModel).toList();
    }

    public void delete(int id) {
        sessionRepository.deleteById(id);
    }

    public Optional<Session> getById(int id) {
        return sessionRepository.findById(id);
    }

    public SessionDTO create(SessionDTO sessionDTO) {
        String voteLink = generateRandomLink();
        sessionDTO.setSessionVoteLink(voteLink);
        var savedSession = sessionRepository.save(sessionDTO.toModel());

        if (savedSession.getFoodVotes() != null) {
            for (var foodVote : savedSession.getFoodVotes()) {
                saveFoodVote(savedSession, foodVote);
            }
        }
        if (savedSession.getGameVotes() != null) {
            for (var gameVote : savedSession.getGameVotes()) {
                saveGameVote(savedSession, gameVote);
            }
        }
        if (savedSession.getDateVotes() != null) {
            for (var dateVote : savedSession.getDateVotes()) {
                saveDateVote(savedSession, dateVote);
            }
        }

        return SessionDTO.ofModel(savedSession);
    }

    private DateVote saveDateVote(Session savedSession, DateVote dateVote) {
        dateVote.setSession_id(savedSession.getId());
        if (dateVote.getVotes() != null) {
            saveVotesForVoteOption(dateVote.getVotes());
        }
        return dateVoteRepository.save(dateVote);
    }

    private FoodVote saveFoodVote(Session savedSession, FoodVote foodVote) {
        foodVote.setSession_id(savedSession.getId());
        if (foodVote.getVotes() != null) {
            saveVotesForVoteOption(foodVote.getVotes());
        }
        return foodVoteRepository.save(foodVote);
    }

    private GameVote saveGameVote(Session savedSession, GameVote gameVote) {
        gameVote.setSession_id(savedSession.getId());
        if (gameVote.getVotes() != null) {
            saveVotesForVoteOption(gameVote.getVotes());
        }
        return gameVoteRepository.save(gameVote);
    }

    public SessionDTO update(SessionDTO sessionDTO) {
        var initialSession = sessionRepository.findById(sessionDTO.getId());
        var updatedSession = sessionDTO.toModel();

        if (initialSession.isPresent()) {
            //manage gameVoteObjects in Database on update
            updateSessionGameVotes(updatedSession, initialSession.get());
            //manage foodVoteObjects in Database on update
            updateSessionFoodVotes(updatedSession, initialSession.get());
            //manage dateVoteObjects in Database on update
            updateSessionDateVotes(updatedSession, initialSession.get());

            return SessionDTO.ofModel(sessionRepository.save(updatedSession));
        } else {
            throw new RuntimeException("Session not found");
        }
    }

    private void updateSessionDateVotes(Session updatedSession, Session initialSession) {
        //get DateVotes from updated and initial Session
        var updatedSessionDateVotes = updatedSession.getDateVotes();
        var initialSessionDateVotes = initialSession.getDateVotes();
        //if there are any differences, update dateVotes
        if (!Objects.equals(updatedSessionDateVotes, initialSessionDateVotes)) {
            var updatedSessionGameVotesIsEmptyOrNull = updatedSessionDateVotes == null || updatedSessionDateVotes.isEmpty();
            var initialSessionGameVotesIsEmptyOrNull = initialSessionDateVotes == null || initialSessionDateVotes.isEmpty();
            //if updated session dateVotes exists and aren't null
            if (!updatedSessionGameVotesIsEmptyOrNull) {
                var updateDateVotes = new ArrayList<DateVote>();
                saveUpdatedSessionDateVotes(updatedSession, updatedSessionDateVotes, updateDateVotes);
                updatedSession.setDateVotes(updateDateVotes);
            }
            //delete all dateVotes that don't exist in updated Session but in saved Session, if dateVotes in Session already exist
            if (!initialSessionGameVotesIsEmptyOrNull) {
                deleteUpdatedSessionDateVotes(initialSessionDateVotes, updatedSessionGameVotesIsEmptyOrNull, updatedSessionDateVotes);
            }
        }
    }

    private void deleteUpdatedSessionDateVotes(List<DateVote> initialSessionDateVotes, boolean updatedSessionGameVotesIsEmptyOrNull, List<DateVote> updatedSessionFoodVotes) {
        //iterate through votes of updatedSession
        for (DateVote vote : initialSessionDateVotes) {
            //delete vote that exists in initial Session if it doesn't exist on updated session
            if (updatedSessionGameVotesIsEmptyOrNull || !updatedSessionFoodVotes.stream().map(DateVote::getId).toList().contains(vote.getId())) {
                dateVoteRepository.delete(vote);
            }
        }
    }

    private void saveUpdatedSessionDateVotes(Session updatedSession, List<DateVote> updatedSessionFoodVotes, ArrayList<DateVote> updateFoodVotes) {
        //iterate through votes of updatedSession
        for (DateVote vote : updatedSessionFoodVotes) {
            var dateVote = dateVoteRepository.findById(vote.getId());
            //if vote isn't saved, save vote and write saved vote into session, else write already saved vote into session
            if (dateVote.isEmpty()) {
                updateFoodVotes.add(saveDateVote(updatedSession, vote));
            } else {
                updateFoodVotes.add(dateVote.get());
            }
        }
    }

    private void updateSessionFoodVotes(Session updatedSession, Session initialSession) {
        //get FoodVotes from updated and initial Session
        var updatedSessionFoodVotes = updatedSession.getFoodVotes();
        var initialSessionFoodVotes = initialSession.getFoodVotes();
        //if there are any differences, update foodVotes
        if (!Objects.equals(updatedSessionFoodVotes, initialSessionFoodVotes)) {
            var updatedSessionGameVotesIsEmptyOrNull = updatedSessionFoodVotes == null || updatedSessionFoodVotes.isEmpty();
            var initialSessionGameVotesIsEmptyOrNull = initialSessionFoodVotes == null || initialSessionFoodVotes.isEmpty();
            //if updated session foodVotes exists and aren't null
            if (!updatedSessionGameVotesIsEmptyOrNull) {
                var updateFoodVotes = new ArrayList<FoodVote>();
                saveUpdatedSessionFoodVotes(updatedSession, updatedSessionFoodVotes, updateFoodVotes);
                updatedSession.setFoodVotes(updateFoodVotes);
            }
            //delete all foodVotes that don't exist in updated Session but in saved Session, if foodVotes in Session already exist
            if (!initialSessionGameVotesIsEmptyOrNull) {
                deleteUpdatedSessionFoodVotes(initialSessionFoodVotes, updatedSessionGameVotesIsEmptyOrNull, updatedSessionFoodVotes);
            }
        }
    }

    private void deleteUpdatedSessionFoodVotes(List<FoodVote> initialSessionFoodVotes, boolean updatedSessionGameVotesIsEmptyOrNull, List<FoodVote> updatedSessionFoodVotes) {
        //iterate through votes of updatedSession
        for (FoodVote vote : initialSessionFoodVotes) {
            //delete vote that exists in initial Session if it doesn't exist on updated session
            if (updatedSessionGameVotesIsEmptyOrNull || !updatedSessionFoodVotes.stream().map(FoodVote::getId).toList().contains(vote.getId())) {
                foodVoteRepository.delete(vote);
            }
        }
    }

    private void saveUpdatedSessionFoodVotes(Session updatedSession, List<FoodVote> updatedSessionFoodVotes, List<FoodVote> updateFoodVotes) {
        //iterate through votes of updatedSession
        for (FoodVote vote : updatedSessionFoodVotes) {
            var foodVote = foodVoteRepository.findById(vote.getId());
            //if vote isn't saved, save vote and write saved vote into session, else write already saved vote into session
            if (foodVote.isEmpty()) {
                updateFoodVotes.add(saveFoodVote(updatedSession, vote));
            } else {
                updateFoodVotes.add(foodVote.get());
            }
        }
    }

    private void updateSessionGameVotes(Session updatedSession, Session initialSession) {
        //get GameVotes from updated and initial Session
        var updatedSessionGameVotes = updatedSession.getGameVotes();
        var initialSessionGameVotes = initialSession.getGameVotes();
        //if there are any differences, update gameVotes
        if (!Objects.equals(updatedSessionGameVotes, initialSessionGameVotes)) {
            var updatedSessionGameVotesIsEmptyOrNull = updatedSessionGameVotes == null || updatedSessionGameVotes.isEmpty();
            var initialSessionGameVotesIsEmptyOrNull = initialSessionGameVotes == null || initialSessionGameVotes.isEmpty();
            //if updated session gameVotes exists and aren't null
            if (!updatedSessionGameVotesIsEmptyOrNull) {
                var updateGameVotes = new ArrayList<GameVote>();
                saveUpdatedGameVotes(updatedSession, updatedSessionGameVotes, updateGameVotes);
                updatedSession.setGameVotes(updateGameVotes);
            }
            //delete all gameVotes that don't exist in updated Session but in saved Session, if gameVotes in Session already exist
            if (!initialSessionGameVotesIsEmptyOrNull) {
                deleteUpdatedSessionGameVotes(initialSessionGameVotes, updatedSessionGameVotesIsEmptyOrNull, updatedSessionGameVotes);
            }
        }
    }

    private void deleteUpdatedSessionGameVotes(List<GameVote> initialSessionGameVotes, boolean updatedSessionGameVotesIsEmptyOrNull, List<GameVote> updatedSessionGameVotes) {
        //iterate through votes of updatedSession
        for (GameVote vote : initialSessionGameVotes) {
            //delete vote that exists in initial Session if it doesn't exist on updated session
            if (updatedSessionGameVotesIsEmptyOrNull || !updatedSessionGameVotes.stream().map(GameVote::getId).toList().contains(vote.getId())) {
                gameVoteRepository.delete(vote);
            }
        }
    }

    private void saveUpdatedGameVotes(Session updatedSession, List<GameVote> updatedSessionGameVotes, ArrayList<GameVote> updateGameVotes) {
        //iterate through votes of updatedSession
        for (GameVote vote : updatedSessionGameVotes) {
            var gameVote = gameVoteRepository.findById(vote.getId());
            //if vote isn't saved, save vote and write saved vote into session, else write already saved vote into session
            if (gameVote.isEmpty()) {
                updateGameVotes.add(saveGameVote(updatedSession, vote));
            } else {
                updateGameVotes.add(gameVote.get());
            }
        }
    }

    public String generateRandomLink() {
        return "http://game2gather.com/vote/" + UUID.randomUUID();
    }

    public void saveVotesForVoteOption(List<Vote> gameVote) {
        voteRepository.saveAll(gameVote);
    }

}
