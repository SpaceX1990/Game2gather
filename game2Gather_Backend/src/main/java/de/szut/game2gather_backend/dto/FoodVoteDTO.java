package de.szut.game2gather_backend.dto;

import de.szut.game2gather_backend.entity.FoodVote;
import de.szut.game2gather_backend.entity.UserVote;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

//automatically creates getters and setters for each declared field
@Data

//automatically creates a Builder that can be used to build the object
@Builder

@AllArgsConstructor
public class FoodVoteDTO {
    //DataTransferObject for FoodVotes that is used to ensure type-safety
    //and possibly prevent code injections

    @Nullable
    private int id;
    @Nullable
    private int session_id;
    @Nullable
    private String voteoption;
    @Nullable
    private List<UserVote> userVotes;

    //create DTO from normal FoodVote
    public static FoodVoteDTO fromModel(FoodVote foodVote) {
        return FoodVoteDTO.builder()
                .id(foodVote.getId())
                .voteoption(foodVote.getVoteoption())
                .userVotes(foodVote.getUserVotes())
                .build();
    }

    //create normal FoodVote from DTO
    public FoodVote toModel(int sessionId) {
        return FoodVote.builder()
                .id(id)
                .session_id(sessionId)
                .userVotes(userVotes)
                .voteoption(voteoption)
                .build();
    }

    //create normal FoodVote from DTO
    public FoodVote toModel() {
        return FoodVote.builder()
                .id(id)
                .session_id(session_id)
                .userVotes(userVotes)
                .voteoption(voteoption)
                .build();
    }

}
