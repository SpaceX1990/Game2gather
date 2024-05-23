package de.szut.game2gather_backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String sessionTitle;

    @NonNull
    private boolean active;

    @Nullable
    private String sessionVoteLink;

    @Nullable
    private int maxPlayer;

    @NonNull
    @Column(name = "fk_user_Id")
    private int userId;

    @Nullable
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false , name = "fk_user_Id")
    private User user;

    @Nullable
    @OneToMany(mappedBy = "session_id")
    private List<GameVote> gameVotes;

    @Nullable
    @OneToMany(mappedBy = "session_id")
    private List<FoodVote> foodVotes;

    @Nullable
    @OneToMany(mappedBy = "session_id")
    private List<DateVote> dateVotes;
}