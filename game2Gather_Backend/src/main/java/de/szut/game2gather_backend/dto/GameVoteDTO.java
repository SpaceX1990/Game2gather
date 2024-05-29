package de.szut.game2gather_backend.dto;

import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.entity.GameVote;
import de.szut.game2gather_backend.entity.UserVote;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GameVoteDTO {
    @Nullable
    private int id;
    @Nullable
    private int session_id;
    @Nullable
    private Game voteoption;
    private List<UserVote> userVotes;

    public static GameVoteDTO fromModel(GameVote gameVote) {
        return GameVoteDTO.builder()
                .id(gameVote.getId())
                .voteoption(gameVote.getVoteoption())
                .userVotes(gameVote.getUserVotes())
                .build();
    }

    public GameVote toModel(int sessionId) {
        return GameVote.builder()
                .id(id)
                .session_id(sessionId)
                .userVotes(userVotes)
                .voteoption(voteoption)
                .build();
    }
    public GameVote toModel() {
        return GameVote.builder()
                .id(id)
                .session_id(session_id)
                .userVotes(userVotes)
                .voteoption(voteoption)
                .build();
    }

}
