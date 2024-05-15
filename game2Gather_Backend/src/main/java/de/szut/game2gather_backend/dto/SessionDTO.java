package de.szut.game2gather_backend.dto;

import de.szut.game2gather_backend.entity.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionDTO {

    private int id;
    private String sessionTitle;
    private boolean active;
    private String sessionVoteLink;
    private Integer maxPlayer;
    private User user;
    private GameVote gameVote;
    private DateVote dateVote;
    private FoodVote foodVote;

    public static SessionDTO ofEntity(Session session) {
        return SessionDTO.builder()
                .id(session.getId())
                .sessionTitle(session.getSessionTitle())
                .active(session.isActive())
                .maxPlayer(session.getMaxPlayer())
                .build();
    }

/*
    public Game toEntity() {
        return Game.builder()
                .id(id)
                .title(title)
                .genre(genre)
                .maxPlayer(maximumPlayers)
                .minPlayer(minimumPlayers)
                .tags(user != null ? user : null)
                .build();
    }
*/

}
