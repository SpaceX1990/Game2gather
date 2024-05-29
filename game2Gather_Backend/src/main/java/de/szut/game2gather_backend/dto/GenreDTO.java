package de.szut.game2gather_backend.dto;


import de.szut.game2gather_backend.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class GenreDTO {
    //DataTransferObject for Genres that is used to ensure type-safety
    //and possibly prevent code injections

    private Integer id;
    private String label;

    //create DTO from normal Genre
    public static GenreDTO fromModel(final Genre genre) {
        return GenreDTO.builder()
                .id(genre.getId())
                .label(genre.getLabel())
                .build();
    }

    //create normal Genre from DTO
    public Genre toModel() {
        return Genre.builder()
                .id(id)
                .label(label)
                .build();
    }
}
