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
    //DataTransferObject for Games that is used to ensure type-safety
    //and possibly prevent code injections

    private int id;
    private String title;
    private int minPlayer;
    private Integer maxPlayer;
    private List<Tag> tags;
    private Genre genre;

    //create DTO from normal Game
    public static GameDTO fromModel(Game game) {
        return GameDTO.builder()
                .id(game.getId())
                .title(game.getTitle())
                .genre(game.getGenre())
                .maxPlayer(game.getMaxPlayer())
                .minPlayer(game.getMinPlayer())
                .tags(game.getTags() != null ? game.getTags() : null)
                .build();
    }

    //create normal Game from DTO
    public Game toModel() {
        return Game.builder()
                .id(id)
                .title(title)
                .genre(genre)
                .maxPlayer(maxPlayer)
                .minPlayer(minPlayer)
                .tags(tags != null ? tags : null)
                .build();
    }

}
