package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//automatically creates getters and setters for each declared field
@Data

//automatically creates a constructor with all parameters for each declared field
@AllArgsConstructor

//automatically creates a constructor with no parameters
@NoArgsConstructor

//marks this object as entity that will be persisted in the database via jpa
//and declares the table this entity references
@Entity(name = "Foodvote")

//automatically creates a Builder that can be used to build the object
@Builder
public class FoodVote {
    //Entity that is saved in the database

    //mark this field as id of entity and generate automatically on first persist
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String voteoption;

    //get all userVotes that belong to dateVotes via mappingTable "foodvote_votes"
    @OneToMany
    @JoinTable(
            name = "foodvote_votes",
            joinColumns = @JoinColumn(name = "foodvote_id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id")
    )
    List<UserVote> userVotes;

    //use this field to map each FoodVote to a specific session in the database
    @JoinColumn(name = "session_id")
    private int session_id;
}
