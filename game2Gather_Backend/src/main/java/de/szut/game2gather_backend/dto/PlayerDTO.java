package de.szut.game2gather_backend.dto;


import de.szut.game2gather_backend.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

//automatically creates a constructor with all parameters for each declared field
@AllArgsConstructor

//automatically creates getters and setters for each declared field
@Data

//automatically creates a Builder that can be used to build the object
@Builder

public class PlayerDTO {
    //DataTransferObject for Players that is used to ensure type-safety
    //and possibly prevent code injections

    private int id;
    private String username;
    private int session_id;

    //create DTO from normal Player
    public static PlayerDTO fromModel(Player player) {
        return PlayerDTO.builder()
                .id(player.getId())
                .username(player.getUsername())
                .session_id(player.getSession_id())
                .build();
    }

    //create normal Player from DTO
    public Player toModel(int session_id) {
        return Player.builder()
                .id(id)
                .username(username)
                .session_id(session_id)
                .build();
    }
}
