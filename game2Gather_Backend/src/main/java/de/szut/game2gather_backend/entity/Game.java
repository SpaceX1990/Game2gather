package de.szut.game2gather_backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//automatically creates getters and setters for each declared field
@Data

//automatically creates a constructor with all parameters for each declared field
@AllArgsConstructor

//automatically creates a constructor with no parameters
@NoArgsConstructor

//marks this object as entity that will be persisted in the database via jpa
@Entity

//automatically creates a Builder that can be used to build the object
@Builder

public class Game {
    //Entity that is saved in the database

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String title;

    @NonNull
    private Integer minPlayer;

    @Nullable
    private Integer maxPlayer;

    @Nullable
    @ManyToMany
    @JoinTable(
            name = "game_tags",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @Nullable
    @ManyToOne()
    @JoinTable(
            name = "game_genre",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Genre genre;

    @Nullable
    private byte[] imageBytes;
}
