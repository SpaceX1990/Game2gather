package entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String title;
    @NonNull
    private int minPlayer;
    @Nullable
    private int maxPlayer;
    @Nullable
    @ManyToMany
    @JoinTable(
            name = "game_tags",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )    private Set<Tag> tags;
    @Nullable
    private String genre;
    @Nullable
    private byte[] imageBytes;
    @Nullable
    @OneToMany (mappedBy="game")
    private List<Comment> comments = new ArrayList<>();
}
