package de.szut.game2gather_backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String sessionTitle;

    @NonNull
    private boolean active;

    @NonNull
    private String sessionVoteLink;

    @Nullable
    private int maxPlayer;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_user_Id")
    private User user;

    @Nullable
    @OneToOne
    @JoinTable(name = "session_gamevotes",
     joinColumns = @JoinColumn(name = "session_id"),
    inverseJoinColumns = @JoinColumn(name = "gamevote_id"))
    private GameVote gameVote;

    /*@Nullable
    @OneToMany
            *//*TODO: @JoinTable(
                    name = "session_votes",
                    joinColumns = @JoinColumn(name = "session_id"),
                    inverseJoinColumns = @JoinColumn(name = "game_vote_id")
            )*//*
    private List<FoodVote> foodVotes;
    @Nullable
    @OneToMany
            *//*TODO: @JoinTable(
                    name = "session_votes",
                    joinColumns = @JoinColumn(name = "session_id"),
                    inverseJoinColumns = @JoinColumn(name = "game_vote_id")
            )*//*
    private List<DateVote> dateVotes;*/
}