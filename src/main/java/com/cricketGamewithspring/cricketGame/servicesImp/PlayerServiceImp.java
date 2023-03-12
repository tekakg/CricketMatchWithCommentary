package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.Repo.ESRepo.ElasticRepo;
import com.cricketGamewithspring.cricketGame.Repo.SQLRepo.PlayerRepo;
import com.cricketGamewithspring.cricketGame.model.Player;
import com.cricketGamewithspring.cricketGame.services.PlayerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
/**

 Implementation of PlayerService interface which provides services to manage players.
 */
public class PlayerServiceImp implements PlayerService {
    @Autowired
    private PlayerRepo playerRepo; // Dependency injection of PlayerRepo.

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService; // Dependency injection of SequenceGeneratorService.

    @Autowired
    private ElasticRepo elasticRepo; // Dependency injection of ElasticRepo.

    /**
     * Adds a new player to the player list.
     *
     * @param player: New player object.
     * @return ResponseEntity with success or error message.
     */
    public ResponseEntity<String> setPlayer(Player player) {
        if (playerRepo.findByName(player.getName()) != null) { // Check if the player already exists.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player already existed"); // Return error message.
        } else {
            player.setId((int) playerRepo.count() + 1); // Generate and set new player ID.
            playerRepo.save(player); // Save the new player to the database.
            Player savedPlayer = playerRepo.findByName(player.getName()); // Retrieve the saved player from the database.
            elasticRepo.save(savedPlayer); // Save the player to ElasticSearch.
            return ResponseEntity.ok("Player is Successfully Added to the PlayerList"); // Return success message.
        }
    }

    /**
     * Gets a player with the specified player ID.
     *
     * @param playerId: ID of the player to retrieve.
     * @return Player object with the specified ID.
     */
    public Player getPlayer(int playerId) {
        Optional<Player> player = playerRepo.findById(playerId); // Retrieve the player with the specified ID from the database.
        return player.get(); // Return the player object.
    }

    /**
     * Gets a list of players with the specified role.
     *
     * @param role: Role of the players to retrieve.
     * @return List of players with the specified role.
     */
    public List<Player> getPlayerUsingRole(String role) {
        List<Player> playerList = playerRepo.findAllByRole(role); // Retrieve a list of players with the specified role from the database.
        return playerList; // Return the list of players.
    }

    /**
     * Gets a player with the specified name.
     *
     * @param name: Name of the player to retrieve.
     * @return Player object with the specified name.
     */
    public Player getPlayerUsingName(String name) {
        Player player = playerRepo.findByName(name); // Retrieve the player with the specified name from the database.
        return player; // Return the player object.
    }
}



