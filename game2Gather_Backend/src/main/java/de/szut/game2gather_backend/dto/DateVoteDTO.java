package de.szut.game2gather_backend.dto;

import de.szut.game2gather_backend.entity.DateVote;
import de.szut.game2gather_backend.entity.UserVote;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class DateVoteDTO {
    //DataTransferObject for DateVotes that is used to ensure type-safety
    //and possibly prevent code injections

    @Nullable
    private int id;
    @Nullable
    private int session_id;
    private LocalDateTime voteoption;
    private List<UserVote> userVotes;

    //create DTO from normal DateVote
    public static DateVoteDTO fromModel(DateVote dateVote) {
        return DateVoteDTO.builder()
                .id(dateVote.getId())
                .voteoption(dateVote.getVoteoption())
                .userVotes(dateVote.getUserVotes())
                .build();
    }

    //create normal DateVote from DTO
    public DateVote toModel(int sessionId) {
        return DateVote.builder()
                .id(id)
                .session_id(sessionId)
                .userVotes(userVotes)
                .voteoption(voteoption)
                .build();
    }

    //create normal DateVote from DTO
    public DateVote toModel() {
        return DateVote.builder()
                .id(id)
                .session_id(session_id)
                .userVotes(userVotes)
                .voteoption(voteoption)
                .build();
    }

}
