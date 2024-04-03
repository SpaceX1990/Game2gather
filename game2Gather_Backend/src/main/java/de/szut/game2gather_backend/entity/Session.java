package de.szut.game2gather_backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Session")
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
    private Integer maxPlayer;

    @NonNull
    private User user;

    @NonNull
    @OneToMany(mappedBy = "session")
    private List<Player> playerList = new ArrayList<>();

    @NonNull
    @OneToMany(mappedBy = "session")
    private List<GameVote> gameVotes = new ArrayList<>();

    @Nullable
    @OneToMany(mappedBy = "session")
    private List<FoodVote> foodVotes = new ArrayList<>();

    @Nullable
    @OneToMany(mappedBy = "session")
    private List<DateVote> dateVotes = new ArrayList<>();
}
