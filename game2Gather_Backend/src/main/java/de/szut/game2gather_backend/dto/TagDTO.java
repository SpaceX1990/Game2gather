package de.szut.game2gather_backend.dto;


import de.szut.game2gather_backend.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class TagDTO {
    private Integer id;
    private String label;

    public static TagDTO ofEntity(Tag genre) {
        return TagDTO.builder()
                .id(genre.getId())
                .label(genre.getLabel())
                .build();
    }

    public Tag toEntity() {
        return Tag.builder()
                .id(id)
                .label(label)
                .build();
    }
}
