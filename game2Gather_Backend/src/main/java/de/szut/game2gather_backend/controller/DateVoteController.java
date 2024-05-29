package de.szut.game2gather_backend.controller;


import de.szut.game2gather_backend.dto.DateVoteDTO;
import de.szut.game2gather_backend.service.DateVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/dateVote")
@RequiredArgsConstructor
public class DateVoteController {

    private final DateVoteService dateVoteService;

    @PutMapping("/vote")
    public DateVoteDTO saveVote(@RequestBody DateVoteDTO dateVoteDTO) {
        return dateVoteService.updateUserVotes(dateVoteDTO);
    }
}
