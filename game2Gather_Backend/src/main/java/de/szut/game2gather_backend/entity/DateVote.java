package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

//automatically creates getters and setters for each declared field
@Data

//automatically creates a constructor with all parameters for each declared field
@AllArgsConstructor

//automatically creates a constructor with no parameters
@NoArgsConstructor

//marks this object as entity that will be persisted in the database via jpa
//and declares the table this entity references
@Entity(name = "Datevote")

//automatically creates a Builder that can be used to build the object
@Builder

public class DateVote {
    //Entity that is saved in the database

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime voteoption;

    @OneToMany
    @JoinTable(
            name = "datevote_votes",
            joinColumns = @JoinColumn(name = "datevote_id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id")
    )
    List<UserVote> userVotes;

    @JoinColumn(name = "session_id")
    private int session_id;
}
