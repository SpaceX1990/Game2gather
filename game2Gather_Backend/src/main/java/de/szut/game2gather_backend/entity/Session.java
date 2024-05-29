package de.szut.game2gather_backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//automatically creates getters and setters for each declared field
@Data

//automatically creates a constructor with all parameters for each declared field
@AllArgsConstructor

//automatically creates a constructor with no parameters
@NoArgsConstructor

//marks this object as entity that will be persisted in the database via jpa
@Entity

//automatically creates a Builder that can be used to build the object
@Builder

public class Session {
    //Entity that is saved in the database

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
    private List<Player> players;

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