package de.szut.game2gather_backend.dto;

import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.entity.GameVote;
import de.szut.game2gather_backend.entity.Vote;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Builder
public class GameVoteDTO {
    @Nullable
    private int id;
    @Nullable
    private Game voteoption;
    private List<Vote> votes;

    public static GameVoteDTO toDTO(GameVote gameVote) {
        return GameVoteDTO.builder()
                .id(gameVote.getId())
                .voteoption(gameVote.getVoteoption())
                .votes(gameVote.getVotes())
                .build();
    }

    public GameVote toEntity(int sessionId) {
        return GameVote.builder()
                .id(id)
                .session_id(sessionId)
                .votes(votes)
                .voteoption(voteoption)
                .build();
    }

}
