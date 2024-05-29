package de.szut.game2gather_backend.repository;


import de.szut.game2gather_backend.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository-Interface to persist and manage Objects of type Genre in database
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
