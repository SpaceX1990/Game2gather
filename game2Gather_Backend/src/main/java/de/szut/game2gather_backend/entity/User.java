package de.szut.game2gather_backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

//automatically creates getters and setters for each declared field
@Data

//automatically creates a constructor with all parameters for each declared field
@AllArgsConstructor

//automatically creates a constructor with no parameters
@NoArgsConstructor

//marks this object as entity that will be persisted in the database via jpa
@Entity

//declares the table, this entity references in the database
@Table(name = "game_User")

public class User {
    //Entity that is saved in the database

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String passwordHash;

    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @NonNull
    private String userFirstname;

    @Nullable
    private String userLastname;
}