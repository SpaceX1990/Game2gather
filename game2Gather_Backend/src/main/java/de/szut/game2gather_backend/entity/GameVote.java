package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Gamevote")
public class GameVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinTable(
            name = "gamevote_player",
            joinColumns = @JoinColumn(name = "gamevote_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Player player;

    @ManyToOne
    @JoinTable(name = "game",
    joinColumns = @JoinColumn(name = "voteoption"))
    private Game voteoption;

    private VoteEnum votevalue;


}
