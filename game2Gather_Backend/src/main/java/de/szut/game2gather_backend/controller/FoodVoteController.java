package de.szut.game2gather_backend.controller;


import de.szut.game2gather_backend.dto.FoodVoteDTO;
import de.szut.game2gather_backend.service.FoodVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//marks this class as a controller that maps requests that are sent
//to a specific url, to specific methods
@RestController

//declares the base url for the requests this controller will handle
@RequestMapping(value = "/api/foodVote")

//automatically creates a constructor for each field declared as final
//so that they can be injected from the springboot / bean context
@RequiredArgsConstructor

public class FoodVoteController {
    //controller to access and manage FoodVotes in Database via incoming http-requests

    private final FoodVoteService foodVoteService;

    //update the foodVote that is passed via the body of the received request
    //by receiving a put-request on "{host}/api/foodVote/vote", where a request-body is transmitted
    //and using the foodVoteService to update the foodVote, that is transmitted, in the database
    @PutMapping("/vote")
    public FoodVoteDTO saveVote(@RequestBody FoodVoteDTO foodVoteDTO) {
        return foodVoteService.updateUserVotes(foodVoteDTO);
    }
}
