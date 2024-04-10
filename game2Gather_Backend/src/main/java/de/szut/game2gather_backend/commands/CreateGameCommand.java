package de.szut.game2gather_backend.commands;
import lombok.Data;

import java.util.List;

@Data
public class CreateGameCommand {

    private int id;
    private String title;
    private int minimumPlayers;
    private int maximumPlayers;
    private List<Integer> tags;
    private long genre;

}
