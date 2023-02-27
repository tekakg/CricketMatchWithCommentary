package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.Repo.PlayerRepo;
import com.cricketGamewithspring.cricketGame.model.Player;
import com.cricketGamewithspring.cricketGame.serviceInt.PlayerServiceInt;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class PlayerService implements PlayerServiceInt {
    @Autowired
    PlayerRepo playerRepo;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    public ResponseEntity<String> setPlayer(Player player) {//Data is directly passed to the database.
        if (playerRepo.findByName(player.getName()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player already existed");
        }
        player.setId(sequenceGeneratorService.generateSequence(Player.SEQUENCE_NAME));
        playerRepo.save(player);
        return ResponseEntity.ok("Player is Successfully Added to the PlayerList");
    }
    public Player getPlayer(int playerId)
    {
        Player player=playerRepo.findById(playerId);
        return player;
    }
    public List<Player> getPlayerUsingRole(String role)
    {
        List<Player> playerList=playerRepo.findAllByRole(role);
        return playerList;
    }
    public Player getPlayerUsingName(String name)
    {
        Player player=playerRepo.findByName(name);
        return player;
    }
}
