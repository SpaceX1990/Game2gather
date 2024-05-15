package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "FoodVote")
public class FoodVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "fk_session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "fk_player_id")
    private Player player;

    private String voteOption;

    private VoteEnum votevalue;

}
