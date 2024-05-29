package de.szut.game2gather_backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Gamevote")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GameVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinTable(
            name = "gamevotes_game",
            joinColumns = @JoinColumn(name = "gamevote_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private Game voteoption;

    @OneToMany
    @JoinTable(
            name = "gamevote_votes",
            joinColumns = @JoinColumn(name = "gamevote_id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id")
    )
    @Nullable
    List<UserVote> userVotes;


    @JoinColumn(name = "session_id")
    private int session_id;

}
