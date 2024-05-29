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
//and declares the table this entity references
@Entity(name = "Genre")

//automatically creates a Builder that can be used to build the object
@Builder

public class Genre {
    //Entity that is saved in the database

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String label;
}
