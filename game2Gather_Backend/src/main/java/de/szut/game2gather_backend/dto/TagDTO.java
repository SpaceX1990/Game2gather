package de.szut.game2gather_backend.dto;


import de.szut.game2gather_backend.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

//automatically creates a constructor with all parameters for each declared field
@AllArgsConstructor

//automatically creates getters and setters for each declared field
@Data

//automatically creates a Builder that can be used to build the object
@Builder

public class TagDTO {
    //DataTransferObject for Tags that is used to ensure type-safety
    //and possibly prevent code injections

    private Integer id;
    private String label;

    //create DTO from normal Tag
    public static TagDTO fromModel(Tag genre) {
        return TagDTO.builder()
                .id(genre.getId())
                .label(genre.getLabel())
                .build();
    }

    //create normal Tag from DTO
    public Tag toModel() {
        return Tag.builder()
                .id(id)
                .label(label)
                .build();
    }
}
