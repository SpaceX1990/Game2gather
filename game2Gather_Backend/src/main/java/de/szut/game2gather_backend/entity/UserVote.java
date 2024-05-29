package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//automatically creates getters and setters for each declared field
@Data

//automatically creates a constructor with all parameters for each declared field
@AllArgsConstructor

//automatically creates a constructor with no parameters
@NoArgsConstructor

//marks this object as entity that will be persisted in the database via jpa
//and declares the table this entity references
@Entity(name = "vote")

public class UserVote {
    //Entity that is saved in the database

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinTable(
            name = "vote_player",
            joinColumns = @JoinColumn(name = "vote_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Player player;

    @Enumerated(EnumType.STRING)
    private VoteValueEnum votevalue;
}
