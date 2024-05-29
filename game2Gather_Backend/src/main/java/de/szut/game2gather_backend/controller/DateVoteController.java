package de.szut.game2gather_backend.controller;


import de.szut.game2gather_backend.dto.DateVoteDTO;
import de.szut.game2gather_backend.service.DateVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//marks this class as a controller that maps requests that are sent
//to a specific url, to specific methods
@RestController

//declares the base url for the requests this controller will handle
@RequestMapping(value = "/api/dateVote")

//automatically creates a constructor for each field declared as final as parameter
//so that they can be injected from the springboot / bean context
@RequiredArgsConstructor

public class DateVoteController {
    //controller to access and manage DateVotes in Database via incoming http-requests

    private final DateVoteService dateVoteService;

    //update the dateVote that is passed via the body of the received request
    //by receiving a put-request on "{host}/api/dateVote/vote", where a request-body is transmitted
    //and using the dateVoteService to update the dateVote, that is transmitted, in the database
    @PutMapping("/vote")
    public DateVoteDTO saveVote(@RequestBody DateVoteDTO dateVoteDTO) {
        return dateVoteService.updateUserVotes(dateVoteDTO);
    }
}
