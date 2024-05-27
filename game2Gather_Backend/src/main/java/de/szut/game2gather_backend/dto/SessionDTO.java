package de.szut.game2gather_backend.dto;

import de.szut.game2gather_backend.entity.*;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SessionDTO {

    @Nullable
    private int id;
    private String sessionTitle;
    private boolean active;
    private String sessionVoteLink;
    private Integer maxPlayer;
    private int userId;
    private List<GameVoteDTO> gameVotes;
    private List<DateVoteDTO> dateVotes;
    private List<FoodVoteDTO> foodVotes;

    public static SessionDTO ofModel(Session session) {
        return SessionDTO.builder()
                .id(session.getId())
                .sessionTitle(session.getSessionTitle())
                .active(session.isActive())
                .sessionVoteLink(session.getSessionVoteLink())
                .userId(session.getUserId())
                .maxPlayer(session.getMaxPlayer())
                .gameVotes(session.getGameVotes() != null ? session.getGameVotes().stream().map(GameVoteDTO::ofModel).toList() : null)
                .dateVotes(session.getDateVotes() != null ? session.getDateVotes().stream().map(DateVoteDTO::fromModel).toList() : null)
                .foodVotes(session.getFoodVotes() != null ? session.getFoodVotes().stream().map(FoodVoteDTO::fromModel).toList() : null)
                .build();
    }


    public Session toModel() {
        return Session.builder()
                .id(id)
                .sessionTitle(sessionTitle)
                .active(active)
                .sessionVoteLink(sessionVoteLink)
                .userId(userId)
                .maxPlayer(maxPlayer)
                .gameVotes(gameVotes != null ? gameVotes.stream().map(vote -> vote.toModel(id)).toList() : null )
                .dateVotes(dateVotes != null ? dateVotes.stream().map(vote -> vote.toModel(id)).toList() : null)
                .foodVotes(foodVotes != null ? foodVotes.stream().map(vote -> vote.toModel(id)).toList() : null)
                .build();
    }

}
