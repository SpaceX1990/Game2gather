package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.SessionDTO;
import de.szut.game2gather_backend.entity.Vote;
import de.szut.game2gather_backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final GameVoteRepository gameVoteRepository;
    private final FoodVoteRepository foodVoteRepository;
    private final DateVoteRepository dateVoteRepository;
    private final VoteRepository voteRepository;

    public List<SessionDTO> getAllActiveSession() {
        return sessionRepository.findByActiveTrue().stream().map(SessionDTO::ofEntity).toList();
    }

    public List<SessionDTO> getAllPastSession() {
        return sessionRepository.findByActiveFalse().stream().map(SessionDTO::ofEntity).toList();
    }

    public List<SessionDTO> readAll() {
        return sessionRepository.findAll().stream().map(SessionDTO::ofEntity).toList();
    }

    public void delete(int id) {
        sessionRepository.deleteById(id);
    }


    public SessionDTO create(SessionDTO sessionDTO) {
        var savedSession = sessionRepository.save(sessionDTO.toEntity());

        if (savedSession.getFoodVotes() != null) {
            for (var foodVote : savedSession.getFoodVotes()) {
                foodVote.setSession_id(savedSession.getId());
                foodVoteRepository.save(foodVote);
                saveVotesForVoteOption(foodVote.getVotes());
            }
        }
        if (savedSession.getGameVotes() != null) {
            for (var gameVote : savedSession.getGameVotes()) {
                gameVote.setSession_id(savedSession.getId());
                gameVoteRepository.save(gameVote);
                saveVotesForVoteOption(gameVote.getVotes());
            }
        }
        if (savedSession.getDateVotes() != null) {
            for (var gameVote : savedSession.getDateVotes()) {
                gameVote.setSession_id(savedSession.getId());
                dateVoteRepository.save(gameVote);
                saveVotesForVoteOption(gameVote.getVotes());
            }
        }

        return SessionDTO.ofEntity(savedSession);
    }

    private void saveVotesForVoteOption(List<Vote> gameVote) {
        voteRepository.saveAll(gameVote);
    }

}
