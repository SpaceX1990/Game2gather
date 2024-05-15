package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Gamevote")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    List<Vote> votes;

}
