package de.szut.game2gather_backend.dto;


import de.szut.game2gather_backend.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class GenreDTO {
    private Integer id;
    private String label;

    public static GenreDTO ofEntity(final Genre genre) {
        return GenreDTO.builder()
                .id(genre.getId())
                .label(genre.getLabel())
                .build();
    }

    public Genre toEntity() {
        return Genre.builder()
                .id(id)
                .label(label)
                .build();
    }
}
