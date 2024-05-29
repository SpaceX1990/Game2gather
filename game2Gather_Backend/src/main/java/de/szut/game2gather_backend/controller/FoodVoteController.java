package de.szut.game2gather_backend.controller;


import de.szut.game2gather_backend.dto.FoodVoteDTO;
import de.szut.game2gather_backend.service.FoodVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/foodVote")
@RequiredArgsConstructor
public class FoodVoteController {

    private final FoodVoteService foodVoteService;

    @PutMapping("/vote")
    public FoodVoteDTO saveVote(@RequestBody FoodVoteDTO foodVoteDTO) {
        return foodVoteService.updateUserVotes(foodVoteDTO);
    }
}
