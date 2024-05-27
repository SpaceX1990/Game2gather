package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.SessionDTO;
import de.szut.game2gather_backend.entity.GameVote;
import de.szut.game2gather_backend.entity.Session;
import de.szut.game2gather_backend.entity.Vote;
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
                foodVote.setSession_id(savedSession.getId());
                if (foodVote.getVotes() != null) {
                    saveVotesForVoteOption(foodVote.getVotes());
                }
                foodVoteRepository.save(foodVote);
            }
        }
        saveGameVotes(savedSession);
        if (savedSession.getDateVotes() != null) {
            for (var dateVote : savedSession.getDateVotes()) {
                dateVote.setSession_id(savedSession.getId());
                if (dateVote.getVotes() != null) {
                    saveVotesForVoteOption(dateVote.getVotes());
                }
                dateVoteRepository.save(dateVote);
            }
        }

        return SessionDTO.ofModel(savedSession);
    }

    private void saveGameVotes(Session savedSession) {
        if (savedSession.getGameVotes() != null) {
            for (var gameVote : savedSession.getGameVotes()) {
                saveGameVote(savedSession, gameVote);
            }
        }
    }

    private GameVote saveGameVote(Session savedSession, GameVote gameVote) {
        gameVote.setSession_id(savedSession.getId());
        if (gameVote.getVotes() != null) {
            saveVotesForVoteOption(gameVote.getVotes());
        }
        return gameVoteRepository.save(gameVote);
    }
//TODO: Vote Updates; Votes need to be deleted on removal on update
    public SessionDTO update(SessionDTO sessionDTO) {
        var initialSession = sessionRepository.findById(sessionDTO.getId());
        var updatedSession = sessionDTO.toModel();
        if (initialSession.isPresent()) {
            var gameVotes = updatedSession.getGameVotes();
            var foodVotes = updatedSession.getFoodVotes();
            var dateVotes = updatedSession.getDateVotes();

            //manage gameVoteObjects in Database on update
            if (!Objects.deepEquals(gameVotes, initialSession.get().getGameVotes())) {
                //save newly addded gamevoteObjects
                if (gameVotes != null) {
                    var updateGameVotes = new ArrayList<GameVote>();
                    for (GameVote vote : gameVotes) {
                        var gameVote = gameVoteRepository.findById(vote.getId());
                        //if vote isnt saved, save vote and write saved vote into session
                        if (gameVote.isEmpty()) {
                            updateGameVotes.add(saveGameVote(updatedSession, vote));
                        } else {
                            updateGameVotes.add(gameVote.get());
                        }
                    }
                    updatedSession.setGameVotes(updateGameVotes);
                }

            }
        }

        return SessionDTO.ofModel(sessionRepository.save(updatedSession));
    }

    public String generateRandomLink() {
        return "http://game2gather.com/vote/" + UUID.randomUUID();
    }

    public void saveVotesForVoteOption(List<Vote> gameVote) {
        voteRepository.saveAll(gameVote);
    }

}
