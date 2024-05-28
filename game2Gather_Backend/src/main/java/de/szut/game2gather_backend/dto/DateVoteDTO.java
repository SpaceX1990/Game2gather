package de.szut.game2gather_backend.dto;

import de.szut.game2gather_backend.entity.DateVote;
import de.szut.game2gather_backend.entity.Vote;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class DateVoteDTO {
    @Nullable
    private int id;
    private LocalDateTime voteoption;
    private List<Vote> votes;

    public static DateVoteDTO fromModel(DateVote dateVote) {
        return DateVoteDTO.builder()
                .id(dateVote.getId())
                .voteoption(dateVote.getVoteoption())
                .votes(dateVote.getVotes())
                .build();
    }

    public DateVote toModel(int sessionId) {
        return DateVote.builder()
                .id(id)
                .session_id(sessionId)
                .votes(votes)
                .voteoption(voteoption)
                .build();
    }

}
