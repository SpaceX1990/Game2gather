package de.szut.game2gather_backend.dto;

import de.szut.game2gather_backend.entity.FoodVote;
import de.szut.game2gather_backend.entity.Vote;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FoodVoteDTO {
    @Nullable
    private int id;
    @Nullable
    private String voteoption;
    @Nullable
    private List<Vote> votes;

    public static FoodVoteDTO fromModel(FoodVote foodVote) {
        return FoodVoteDTO.builder()
                .id(foodVote.getId())
                .voteoption(foodVote.getVoteoption())
                .votes(foodVote.getVotes())
                .build();
    }

    public FoodVote toModel(int sessionId) {
        return FoodVote.builder()
                .id(id)
                .session_id(sessionId)
                .votes(votes)
                .voteoption(voteoption)
                .build();
    }

}
