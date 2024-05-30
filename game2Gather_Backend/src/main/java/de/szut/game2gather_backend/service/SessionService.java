package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.SessionDTO;
import de.szut.game2gather_backend.entity.Session;
import de.szut.game2gather_backend.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

//marks this class so that a bean that gets created on application-build
//and that can then be injected into other useCases via Autowiring or Constructor injection
@Service

//automatically creates a constructor with each field declared as final as parameter
//so that they can be injected from the springboot / bean context
@RequiredArgsConstructor

public class SessionService {
    //Service that is used to manage Session-Objects in database

    private final SessionRepository sessionRepository;
    private final GameVoteService gameVoteService;
    private final FoodVoteService foodVoteService;
    private final DateVoteService dateVoteService;
    private final PlayerService playerService;

    //get all active Session-Objects in database and return them as DTOs
    public List<SessionDTO> getAllActiveSession() {
        return sessionRepository.findByActiveTrue().stream().map(SessionDTO::fromModel).toList();
    }

    //get all finished Session-Objects in database and return them as DTOs
    public List<SessionDTO> getAllPastSession() {
        return sessionRepository.findByActiveFalse().stream().map(SessionDTO::fromModel).toList();
    }

    //get all Session-Objects in database and return them as DTOs
    public List<SessionDTO> readAll() {
        return sessionRepository.findAll().stream().map(SessionDTO::fromModel).toList();
    }

    //delete the session (and corresponding GameVotes, DateVotes, FoodVotes and Users) that has the id
    public void delete(int id) {
        sessionRepository.deleteById(id);
    }

    //get single Session that has the id
    public Optional<Session> getById(int id) {
        return sessionRepository.findById(id);
    }

    //create a new session from a SessionDTO and each corresponding GameVote, FoodVote and DateVote
    public SessionDTO create(SessionDTO sessionDTO) {

        //create and set the link in the session, which the voting page will be reachable under
        String voteLink = generateRandomLink();
        sessionDTO.setSessionVoteLink(voteLink);

        //save the session
        var savedSession = sessionRepository.save(sessionDTO.toModel());

        //if the saved session contains FoodVotes
        if (savedSession.getFoodVotes() != null) {
            //save all foodVotes in the session and bind them to the session
            foodVoteService.saveVotesForSessionID(savedSession.getFoodVotes(), savedSession.getId());
        }

        //if the saved session contains GameVotes
        if (savedSession.getGameVotes() != null) {
            //save all gameVotes in the session and bind them to the session
            gameVoteService.saveVotesForSessionID(savedSession.getGameVotes(), savedSession.getId());
        }

        //if the saved session contains DateVotes
        if (savedSession.getDateVotes() != null) {
            //save all dateVotes in the session and bind them to the session
            dateVoteService.saveVotesForSessionID(savedSession.getDateVotes(), savedSession.getId());
        }

        return SessionDTO.fromModel(savedSession);
    }

    //manage VoteObjects in Database on update
    public SessionDTO update(SessionDTO sessionDTO) {
        var updatedSession = sessionDTO.toModel();
        var initialSession = sessionRepository.findById(updatedSession.getId());

        if (initialSession.isPresent()) {

            var updatedGameVotes = updatedSession.getGameVotes();
            var initialGameVotes = initialSession.get().getGameVotes();
            //if there are any differences, update or save gameVotes in relation to the session
            if (!Objects.equals(updatedGameVotes, initialGameVotes)) {
                updatedSession.setGameVotes(
                        gameVoteService.updateSessionVotesForSessionID(updatedGameVotes, initialGameVotes, updatedSession.getId())
                );
            }

            var updatedFoodVotes = updatedSession.getFoodVotes();
            var initialFoodVotes = initialSession.get().getFoodVotes();
            //if there are any differences, update or save foodVotes in relation to the session
            if (!Objects.equals(updatedFoodVotes, initialFoodVotes)) {
                updatedSession.setFoodVotes(
                        foodVoteService.updateSessionVotesForSessionID(updatedFoodVotes, initialFoodVotes, updatedSession.getId())
                );
            }

            var updatedDateVotes = updatedSession.getDateVotes();
            var initialDateVotes = initialSession.get().getDateVotes();
            //if there are any differences, update or save dateVotes in relation to the session
            if (!Objects.equals(updatedDateVotes, initialDateVotes)) {
                updatedSession.setDateVotes(
                        dateVoteService.updateSessionVotesForSessionID(updatedDateVotes, initialDateVotes, updatedSession.getId())
                );
            }

            var updatedPlayers = updatedSession.getPlayers();
            var initialPlayers = initialSession.get().getPlayers();
            //if there are any differences, update or save players in relation to the session
            if (!Objects.equals(updatedPlayers, initialPlayers)) {
                updatedSession.setPlayers(
                        playerService.updatePlayers(updatedPlayers, initialPlayers)
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
