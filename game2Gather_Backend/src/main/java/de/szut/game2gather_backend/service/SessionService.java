package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.entity.Session;
import de.szut.game2gather_backend.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    public List<Session> getAllActiveSession() {
        return sessionRepository.findAllByActiveTrue();
    }
}
