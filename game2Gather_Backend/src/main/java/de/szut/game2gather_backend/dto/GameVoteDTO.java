package de.szut.game2gather_backend.dto;

import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.entity.GameVote;
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
public class GameVoteDTO {
    //DataTransferObject for GameVotes that is used to ensure type-safety
    //and possibly prevent code injections

    @Nullable
    private int id;
    @Nullable
    private int session_id;
    @Nullable
    private Game voteoption;
    private List<UserVote> userVotes;

    //create DTO from normal GameVote
    public static GameVoteDTO fromModel(GameVote gameVote) {
        return GameVoteDTO.builder()
                .id(gameVote.getId())
                .voteoption(gameVote.getVoteoption())
                .userVotes(gameVote.getUserVotes())
                .build();
    }

    //create normal GameVote from DTO
    public GameVote toModel(int sessionId) {
        return GameVote.builder()
                .id(id)
                .session_id(sessionId)
                .userVotes(userVotes)
                .voteoption(voteoption)
                .build();
    }

    //create normal GameVote from DTO
    public GameVote toModel() {
        return GameVote.builder()
                .id(id)
                .session_id(session_id)
                .userVotes(userVotes)
                .voteoption(voteoption)
                .build();
    }

}
