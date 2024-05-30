package de.szut.game2gather_backend.service;


import de.szut.game2gather_backend.entity.Player;
import de.szut.game2gather_backend.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//marks this class so that a bean that gets created on application-build
//and that can then be injected into other useCases via Autowiring or Constructor injection
@Service

//automatically creates a constructor for each field declared as final as parameter
//so that they can be injected from the springboot / bean context
@RequiredArgsConstructor

public class PlayerService {
    //Service that is used to manage Player-Objects in database

    private final PlayerRepository repository;

    public List<Player> updatePlayers(List<Player> updatedPlayers, List<Player> initialPlayers) {
        List<Player> updateVotes = new ArrayList<>();
        var updatedPlayersIsEmptyOrNull = updatedPlayers == null || updatedPlayers.isEmpty();
        var initialPlayersIsEmptyOrNull = initialPlayers == null || initialPlayers.isEmpty();
        //if updated player-list exists and isn't empty,
        if (!updatedPlayersIsEmptyOrNull) {
            updateVotes = saveAndGetPlayers(updatedPlayers);
        }
        //delete all players that don't exist in updated player-list but in saved player-list, if the players already exist
        if (!initialPlayersIsEmptyOrNull) {
            deleteUpdatedPlayers(initialPlayers, updatedPlayers, updatedPlayersIsEmptyOrNull);
        }
        return updateVotes;
    }

    private void deleteUpdatedPlayers(List<Player> initialPlayers, List<Player> updatedPlayers, boolean updatedPlayersIsEmptyOrNull) {
        //iterate through players of updated player-list
        for (Player player : initialPlayers) {
            //delete player that exists in initial player-list if it doesn't exist on updated player-list
            if (updatedPlayersIsEmptyOrNull || !updatedPlayers.stream().map(Player::getId).toList().contains(player.getId())) {
                repository.delete(player);
            }
        }
    }

    private List<Player> saveAndGetPlayers(List<Player> updatedPlayers) {
        var updatePlayers = new ArrayList<Player>();
        //iterate through players of updated player-list
        for (Player player : updatedPlayers) {
            var existsPlayer = repository.findById(player.getId());
            //if player isn't saved, save player and write saved player into list, else write already saved player into list
            if (existsPlayer.isEmpty()) {
                updatePlayers.add(repository.save(player));
            } else {
                updatePlayers.add(existsPlayer.get());
            }
        }
        return updatePlayers;
    }
}
