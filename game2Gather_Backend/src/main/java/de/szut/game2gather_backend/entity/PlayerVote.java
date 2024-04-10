package de.szut.game2gather_backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlayerVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "date_vote_option_id")
    private DateVoteOption dateVoteOption;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "food_vote_option_id")
    private FoodVoteOption foodVoteOption;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "game_vote_option_id")
    private GameVoteOption gameVoteOption;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @NonNull
    @Enumerated(EnumType.STRING)
    private VoteEnum vote;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;
}
