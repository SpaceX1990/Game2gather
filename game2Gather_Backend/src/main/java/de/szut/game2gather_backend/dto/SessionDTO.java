package de.szut.game2gather_backend.dto;

import de.szut.game2gather_backend.entity.Session;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.List;

//automatically creates getters and setters for each declared field
@Data

//automatically creates a Builder that can be used to build the object
@Builder

public class SessionDTO {
    //DataTransferObject for Sessions that is used to ensure type-safety
    //and possibly prevent code injections

    @Nullable
    private int id;
    private String sessionTitle;
    private boolean active;
    private String sessionVoteLink;
    private Integer maxPlayer;
    private int userId;
    private List<PlayerDTO> players;
    private List<GameVoteDTO> gameVotes;
    private List<DateVoteDTO> dateVotes;
    private List<FoodVoteDTO> foodVotes;

    //create DTO from normal Session
    public static SessionDTO fromModel(Session session) {
        return SessionDTO.builder()
                .id(session.getId())
                .sessionTitle(session.getSessionTitle())
                .active(session.isActive())
                .sessionVoteLink(session.getSessionVoteLink())
                .userId(session.getUserId())
                .maxPlayer(session.getMaxPlayer())
                .players(session.getPlayers() != null ? session.getPlayers().stream().map(PlayerDTO::fromModel).toList() : null)
                .gameVotes(session.getGameVotes() != null ? session.getGameVotes().stream().map(GameVoteDTO::fromModel).toList() : null)
                .dateVotes(session.getDateVotes() != null ? session.getDateVotes().stream().map(DateVoteDTO::fromModel).toList() : null)
                .foodVotes(session.getFoodVotes() != null ? session.getFoodVotes().stream().map(FoodVoteDTO::fromModel).toList() : null)
                .build();
    }

    //create normal Session from DTO
    public Session toModel() {
        return Session.builder()
                .id(id)
                .sessionTitle(sessionTitle)
                .active(active)
                .sessionVoteLink(sessionVoteLink)
                .userId(userId)
                .maxPlayer(maxPlayer)
                .players(players != null ? players.stream().map(player -> player.toModel(id)).toList() : null)
                .gameVotes(gameVotes != null ? gameVotes.stream().map(vote -> vote.toModel(id)).toList() : null)
                .dateVotes(dateVotes != null ? dateVotes.stream().map(vote -> vote.toModel(id)).toList() : null)
                .foodVotes(foodVotes != null ? foodVotes.stream().map(vote -> vote.toModel(id)).toList() : null)
                .build();
    }

}
