package de.szut.game2gather_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Genre {

    @Id
    private long id;

    @Enumerated
    private GenreEnum genreEnum;
}
