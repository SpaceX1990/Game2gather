package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.SessionDTO;
import de.szut.game2gather_backend.entity.Session;
import de.szut.game2gather_backend.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final GameVoteService gameVoteService;
    private final FoodVoteService foodVoteService;
    private final DateVoteService dateVoteService;

    @GetMapping

    public List<SessionDTO> getAllActiveSession() {
        return sessionRepository.findByActiveTrue().stream().map(SessionDTO::fromModel).toList();
    }

    public List<SessionDTO> getAllPastSession() {
        return sessionRepository.findByActiveFalse().stream().map(SessionDTO::fromModel).toList();
    }

    public List<SessionDTO> readAll() {
        return sessionRepository.findAll().stream().map(SessionDTO::fromModel).toList();
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
            foodVoteService.saveVotesForSessionID(savedSession.getFoodVotes(), savedSession.getId());
        }
        if (savedSession.getGameVotes() != null) {
            gameVoteService.saveVotesForSessionID(savedSession.getGameVotes(), savedSession.getId());
        }
        if (savedSession.getDateVotes() != null) {
            dateVoteService.saveVotesForSessionID(savedSession.getDateVotes(), savedSession.getId());
        }

        return SessionDTO.fromModel(savedSession);
    }

    //manage VoteObjects in Database on update
    public SessionDTO update(SessionDTO sessionDTO) {
        var initialSession = sessionRepository.findById(sessionDTO.getId());
        var updatedSession = sessionDTO.toModel();

        if (initialSession.isPresent()) {

            var updatedGameVotes = updatedSession.getGameVotes();
            var initialGameVotes = initialSession.get().getGameVotes();
            //if there are any differences, update gameVotes
            if (!Objects.equals(updatedGameVotes, initialGameVotes)) {
                updatedSession.setGameVotes(
                        gameVoteService.updateSessionVotesForSessionID(updatedGameVotes, initialGameVotes, updatedSession.getId())
                );
            }

            var updatedFoodVotes = updatedSession.getFoodVotes();
            var initialFoodVotes = initialSession.get().getFoodVotes();
            //if there are any differences, update foodVotes
            if (!Objects.equals(updatedFoodVotes, initialFoodVotes)) {
                updatedSession.setFoodVotes(
                        foodVoteService.updateSessionVotesForSessionID(updatedFoodVotes, initialFoodVotes, updatedSession.getId())
                );
            }

            var updatedDateVotes = updatedSession.getDateVotes();
            var initialDateVotes = initialSession.get().getDateVotes();
            //if there are any differences, update dateVotes
            if (!Objects.equals(updatedDateVotes, initialDateVotes)) {
                updatedSession.setDateVotes(
                        dateVoteService.updateSessionVotesForSessionID(updatedDateVotes, initialDateVotes, updatedSession.getId())
                );
            }

            return SessionDTO.fromModel(sessionRepository.save(updatedSession));
        } else {
            throw new RuntimeException("Session not found");
        }
    }

    public String generateRandomLink() {
        return "http://game2gather.com/vote/" + UUID.randomUUID();
    }
}
