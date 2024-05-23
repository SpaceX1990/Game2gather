package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.SessionDTO;
import de.szut.game2gather_backend.entity.Session;
import de.szut.game2gather_backend.entity.Vote;
import de.szut.game2gather_backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<Session> getById(int id) {
        return sessionRepository.findById(id);
    }

    public SessionDTO create(SessionDTO sessionDTO) {
        String voteLink = generateRandomLink();
        sessionDTO.setSessionVoteLink(voteLink);
        var savedSession = sessionRepository.save(sessionDTO.toEntity());

        if (savedSession.getFoodVotes() != null) {
            for (var foodVote : savedSession.getFoodVotes()) {
                foodVote.setSession_id(savedSession.getId());
                if (foodVote.getVotes() != null) {
                    saveVotesForVoteOption(foodVote.getVotes());
                }
                foodVoteRepository.save(foodVote);
            }
        }
        if (savedSession.getGameVotes() != null) {
            for (var gameVote : savedSession.getGameVotes()) {
                gameVote.setSession_id(savedSession.getId());
                if (gameVote.getVotes() != null) {
                    saveVotesForVoteOption(gameVote.getVotes());
                }
                gameVoteRepository.save(gameVote);
            }
        }
        if (savedSession.getDateVotes() != null) {
            for (var dateVote : savedSession.getDateVotes()) {
                dateVote.setSession_id(savedSession.getId());
                if (dateVote.getVotes() != null) {
                    saveVotesForVoteOption(dateVote.getVotes());
                }
                dateVoteRepository.save(dateVote);
            }
        }

        return SessionDTO.ofEntity(savedSession);
    }

    public String generateRandomLink() {
        return "http://game2gather.com/vote/" + UUID.randomUUID();
    }

    public void saveVotesForVoteOption(List<Vote> gameVote) {
        voteRepository.saveAll(gameVote);
    }


}
