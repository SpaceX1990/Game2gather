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

    //mark this field as id of entity  and generate automatically on first persist
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

    //this field is used to mark which user the session belongs to
    @NonNull
    @Column(name = "fk_user_Id")
    private int userId;

    //this field uses the userId to get the user, that owns the session
    @Nullable
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false , name = "fk_user_Id")
    private User user;

    //uses the session_id property on Player to get each that belongs to session
    @Nullable
    @OneToMany(mappedBy = "session_id")
    private List<Player> players;

    //uses the session_id property on GameVote to get each that belongs to session
    @Nullable
    @OneToMany(mappedBy = "session_id")
    private List<GameVote> gameVotes;

    //uses the session_id property on FoodVote to get each that belongs to session
    @Nullable
    @OneToMany(mappedBy = "session_id")
    private List<FoodVote> foodVotes;

    //uses the session_id property on DateVote to get each that belongs to session
    @Nullable
    @OneToMany(mappedBy = "session_id")
    private List<DateVote> dateVotes;
}