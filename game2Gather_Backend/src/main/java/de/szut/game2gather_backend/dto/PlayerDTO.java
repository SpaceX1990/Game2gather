package de.szut.game2gather_backend.dto;


import de.szut.game2gather_backend.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class PlayerDTO {
    private int id;
    private String username;
    private int session_id;

    public static PlayerDTO fromModel(Player player) {
        return PlayerDTO.builder()
                .id(player.getId())
                .username(player.getUsername())
                .session_id(player.getSession_id())
                .build();
    }

    public Player toModel(int session_id) {
        return Player.builder()
                .id(session_id)
                .username(username)
                .session_id(session_id)
                .build();
    }
}
