package de.szut.game2gather_backend.dto;

import de.szut.game2gather_backend.entity.DateVote;
import de.szut.game2gather_backend.entity.UserVote;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class DateVoteDTO {
    @Nullable
    private int id;
    @Nullable
    private int session_id;
    private LocalDateTime voteoption;
    private List<UserVote> userVotes;

    public static DateVoteDTO fromModel(DateVote dateVote) {
        return DateVoteDTO.builder()
                .id(dateVote.getId())
                .voteoption(dateVote.getVoteoption())
                .userVotes(dateVote.getUserVotes())
                .build();
    }

    public DateVote toModel(int sessionId) {
        return DateVote.builder()
                .id(id)
                .session_id(sessionId)
                .userVotes(userVotes)
                .voteoption(voteoption)
                .build();
    }
    public DateVote toModel() {
        return DateVote.builder()
                .id(id)
                .session_id(session_id)
                .userVotes(userVotes)
                .voteoption(voteoption)
                .build();
    }

}
