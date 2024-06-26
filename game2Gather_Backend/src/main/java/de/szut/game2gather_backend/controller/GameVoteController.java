package de.szut.game2gather_backend.controller;


import de.szut.game2gather_backend.dto.GameVoteDTO;
import de.szut.game2gather_backend.service.GameVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//marks this class as a controller that maps requests that are sent
//to a specific url, to specific methods
@RestController

//declares the base url for the requests this controller will handle
@RequestMapping(value = "/api/gameVote")

//automatically creates a constructor for each field declared as final as parameter
//so that they can be injected from the springboot / bean context
@RequiredArgsConstructor

public class GameVoteController {
    //controller to access and manage GameVotes in Database via incoming http-requests

    private final GameVoteService gameVoteService;

    //update the gameVote that is passed via the body of the received request
    //by receiving a put-request on "{host}/api/gameVote/vote", where a request-body is transmitted
    //and using the gameVoteService to update the gameVote, that is transmitted, in the database
    @PutMapping("/vote")
    public GameVoteDTO saveGameVote(@RequestBody GameVoteDTO gameVoteDTO) {
        return gameVoteService.updateUserVotes(gameVoteDTO);
    }
}
