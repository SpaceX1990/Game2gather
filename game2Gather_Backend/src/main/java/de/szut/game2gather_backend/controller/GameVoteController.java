package de.szut.game2gather_backend.controller;


import de.szut.game2gather_backend.dto.GameVoteDTO;
import de.szut.game2gather_backend.service.GameVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/gameVote")
@RequiredArgsConstructor
public class GameVoteController {

    private final GameVoteService gameVoteService;

    @PutMapping("/vote")
    public GameVoteDTO saveGameVote(@RequestBody GameVoteDTO gameVoteDTO) {
        return gameVoteService.updateUserVotes(gameVoteDTO);
    }
}
