package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;
import lombok.*;

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

public class Player {
    //Entity that is saved in the database

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String username;

    @JoinColumn(name = "session_id")
    private int session_id;
}