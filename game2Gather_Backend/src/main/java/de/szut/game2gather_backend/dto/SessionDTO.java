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
    private User user;
    private List<GameVoteDTO> gameVotes;
    private List<DateVoteDTO> dateVotes;
    private List<FoodVoteDTO> foodVotes;

    public static SessionDTO ofEntity(Session session) {
        return SessionDTO.builder()
                .id(session.getId())
                .sessionTitle(session.getSessionTitle())
                .active(session.isActive())
                .sessionVoteLink(session.getSessionVoteLink())
                .user(session.getUser())
                .maxPlayer(session.getMaxPlayer())
                .gameVotes(session.getGameVotes() != null ? session.getGameVotes().stream().map(GameVoteDTO::toDTO).toList() : null)
                .dateVotes(session.getDateVotes() != null ? session.getDateVotes().stream().map(DateVoteDTO::toDTO).toList() : null)
                .foodVotes(session.getFoodVotes() != null ? session.getFoodVotes().stream().map(FoodVoteDTO::toDTO).toList() : null)
                .build();
    }


    public Session toEntity() {
        return Session.builder()
                .id(id)
                .sessionTitle(sessionTitle)
                .active(active)
                .sessionVoteLink(sessionVoteLink)
                .user(user)
                .maxPlayer(maxPlayer)
                .gameVotes(gameVotes.stream().map(vote -> vote.toEntity(id)).toList())
                .dateVotes(dateVotes.stream().map(vote -> vote.toEntity(id)).toList())
                .foodVotes(foodVotes.stream().map(vote -> vote.toEntity(id)).toList())
                .build();
    }

}
