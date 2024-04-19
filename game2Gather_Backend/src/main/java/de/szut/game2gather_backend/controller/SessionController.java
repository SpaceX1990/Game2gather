package de.szut.game2gather_backend.controller;

import de.szut.game2gather_backend.entity.Session;
import de.szut.game2gather_backend.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/session")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;

    @GetMapping("/active")
    public List<Session> getAllActiveSessions() {
        return sessionService.getAllActiveSession();
    }

}
