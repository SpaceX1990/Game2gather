package de.szut.game2gather_backend.service;


import de.szut.game2gather_backend.entity.Player;
import de.szut.game2gather_backend.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository repository;

    public List<Player> updatePlayers(List<Player> updatedPlayers, List<Player> initialPlayers) {
        List<Player> updateVotes = new ArrayList<>();
        var updatedPlayersIsEmptyOrNull = updatedPlayers == null || updatedPlayers.isEmpty();
        var initialPlayersIsEmptyOrNull = initialPlayers == null || initialPlayers.isEmpty();
        if (!updatedPlayersIsEmptyOrNull) {
            updateVotes = saveAndGetPlayers( updatedPlayers);
        }
        if (!initialPlayersIsEmptyOrNull) {
            deleteUpdatedPlayers(initialPlayers, updatedPlayers, updatedPlayersIsEmptyOrNull);
        }
        return updateVotes;
    }

    private void deleteUpdatedPlayers(List<Player> initialPlayers, List<Player> updatedPlayers, boolean updatedPlayersIsEmptyOrNull) {
        for (Player player : initialPlayers) {
            if (updatedPlayersIsEmptyOrNull || !updatedPlayers.stream().map(Player::getId).toList().contains(player.getId())) {
                repository.delete(player);
            }
        }
    }

    private List<Player> saveAndGetPlayers(List<Player> updatedPlayers) {
        var updatePlayers = new ArrayList<Player>();
        for (Player player : updatedPlayers) {
            var existsPlayer = repository.findById(player.getId());
            if ( existsPlayer.isEmpty()) {
                updatePlayers.add(repository.save(player));
            } else {
                updatePlayers.add(existsPlayer.get());
            }
        }
        return updatePlayers;
    }
}
