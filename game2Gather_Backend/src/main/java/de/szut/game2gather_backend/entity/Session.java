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
    @ManyToMany
    @JoinTable(
            name = "session_votes",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id")
    )
    private List<Vote> votes;
}