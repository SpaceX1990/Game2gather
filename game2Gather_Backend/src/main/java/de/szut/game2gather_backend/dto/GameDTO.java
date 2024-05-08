package de.szut.game2gather_backend.dto;

import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.entity.Genre;
import de.szut.game2gather_backend.entity.Tag;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GameDTO {

    private int id;
    private String title;
    private int minimumPlayers;
    private Integer maximumPlayers;
    private List<Tag> tags;
    private Genre genre;

    public static GameDTO ofEntity(Game game) {
        return GameDTO.builder()
                .id(game.getId())
                .title(game.getTitle())
                .genre(game.getGenre())
                .maximumPlayers(game.getMaxPlayer())
                .minimumPlayers(game.getMinPlayer())
                .tags(game.getTags() != null ? game.getTags() : null)
                .build();
    }

    public Game toEntity() {
        return Game.builder()
                .id(id)
                .title(title)
                .genre(genre)
                .maxPlayer(maximumPlayers)
                .minPlayer(minimumPlayers)
                .tags(tags != null ? tags : null)
                .build();
    }

}