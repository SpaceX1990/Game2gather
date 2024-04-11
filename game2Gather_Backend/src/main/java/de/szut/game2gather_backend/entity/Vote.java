package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "fk_player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "fk_game_id")
    private Game game;

    private Date dateOption;

    private String foodOption;

    private VoteEnum votevalue;

}
