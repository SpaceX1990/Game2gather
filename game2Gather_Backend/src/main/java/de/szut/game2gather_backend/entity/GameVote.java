package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "GameVote")
public class GameVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "fk_session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "fk_player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "fk_game_id")
    private Game voteOption;

    private VoteEnum votevalue;



}
