package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "DateVote")
public class DateVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "fk_session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "fk_player_id")
    private Player player;

    private Date voteOption;

    private VoteEnum votevalue;

}
