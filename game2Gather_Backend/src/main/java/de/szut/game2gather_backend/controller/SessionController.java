package de.szut.game2gather_backend.controller;

import de.szut.game2gather_backend.dto.SessionDTO;
import de.szut.game2gather_backend.entity.Session;
import de.szut.game2gather_backend.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//marks this class as a controller that maps requests that are sent
//to a specific url, to specific methods
@RestController

//declares the base url for the requests this controller will handle
@RequestMapping(value = "/api/session")

//automatically creates a constructor for each field declared as final as parameter
//so that they can be injected from the springboot / bean context
@RequiredArgsConstructor

public class SessionController {
    //controller to access and manage Sessions in Database via incoming http-requests

    private final SessionService sessionService;

    //get the session that has the id, which is passed via url-parameter,
    //by receiving a get-request on "{host}/api/session/:id", where id represents the url-parameter
    //and using the sessionService to get the matching session from the database
    @GetMapping("/{id}")
    public Optional<Session> getSession(@PathVariable int id) {
        return sessionService.getById(id);
    }

    //get all existing sessions that are active by receiving a get-request on "{host}/api/session/active"
    //and using the sesionService to get all matching sessions in the database
    @GetMapping("/active")
    public List<SessionDTO> getAllActiveSessions() {
        return sessionService.getAllActiveSession();
    }

    //get all existing sessions by receiving a get-request on "{host}/api/session/"
    //and using the sesionService to get all sessions in the database
    @GetMapping()
    public List<SessionDTO> getAll() {
        return sessionService.readAll();
    }

    //get all existing sessions that are finished by receiving a get-request on "{host}/api/session/past"
    //and using the sesionService to get all matching sessions in the database
    @GetMapping("/past")
    public List<SessionDTO> getAllPastSessions() {
        return sessionService.getAllPastSession();
    }

    //update the session that is passed via the body of the received request
    //by receiving a put-request on "{host}/api/session", where a request-body is transmitted
    //and using the sessionService to update the session, that is transmitted, in the database
    @PutMapping
    public SessionDTO updateSession(@RequestBody SessionDTO sessionDTO) {
        return sessionService.update(sessionDTO);
    }

    //delete the session that has the id, which is passed via url-parameter,
    //by receiving a get-request on "{host}/api/session/delete/:id", where id represents the url-parameter
    //and using the sessionService to delete the matching session from the database
    @DeleteMapping("/delete/{id}")
    public void deleteSession(@PathVariable int id) {
        sessionService.delete(id);
    }

    //create the session that is passed via the body of the received request
    //by receiving a post-request on "{host}/api/session", where a request-body is transmitted
    //and using the sessionService to create the session that is transmitted in the database
    @PostMapping
    public SessionDTO createSession(@RequestBody SessionDTO sessionDTO) {
        return sessionService.create(sessionDTO);
    }
}
