package de.szut.game2gather_backend.controller;

import de.szut.game2gather_backend.dto.SessionDTO;
import de.szut.game2gather_backend.entity.GameVote;
import de.szut.game2gather_backend.entity.Session;
import de.szut.game2gather_backend.repository.GameVoteRepository;
import de.szut.game2gather_backend.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final GameVoteRepository gameVoteRepository;

    @GetMapping("/{id}")
    public Optional<Session> getSession(@PathVariable int id) {
        return sessionService.getById(id);
    }

    @GetMapping("/active")
    public List<SessionDTO> getAllActiveSessions() {
        return sessionService.getAllActiveSession();
    }

    @GetMapping()
    public List<SessionDTO> getAll() {
        return sessionService.readAll();
    }

    @GetMapping("/past")
    public List<SessionDTO> getAllPastSessions() {
        return sessionService.getAllPastSession();
    }

    @PutMapping
    public SessionDTO updateSession(@RequestBody SessionDTO sessionDTO) {
        return sessionService.update(sessionDTO);
    }

    @GetMapping("/test")
    public List<GameVote> test() {
        return gameVoteRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSession(@PathVariable int id) {
        sessionService.delete(id);
    }

    @PostMapping
    public SessionDTO createSession(@RequestBody SessionDTO sessionDTO) {
        return sessionService.create(sessionDTO);
    }
}
