package de.szut.game2gather_backend.service;

import de.szut.game2gather_backend.dto.SessionDTO;
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
        return sessionRepository.findByActiveTrue();
    }
    public List<Session> getAllPastSession() {
        return sessionRepository.findByActiveFalse();
    }

    public List<SessionDTO> readAll() {
    List<Session> sessions = sessionRepository.findAll();
    return sessions.stream().map(SessionDTO::ofEntity).toList();
    }

    public void delete(int id) {
        sessionRepository.deleteById(id);
    }


}
