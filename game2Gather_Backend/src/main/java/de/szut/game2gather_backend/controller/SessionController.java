package de.szut.game2gather_backend.controller;

import de.szut.game2gather_backend.dto.SessionDTO;
import de.szut.game2gather_backend.entity.Session;
import de.szut.game2gather_backend.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    //TODO: use DTOs

    @GetMapping("/active")
    public List<Session> getAllActiveSessions() {
        return sessionService.getAllActiveSession();
    }

    @GetMapping()
    public List<Session> getAll() {
        return sessionService.readAll();
    }

    @GetMapping("/past")
    public List<Session> getAllPastSessions() {
        return sessionService.getAllPastSession();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSession(@PathVariable int id) {
        sessionService.delete(id);
    }
}
