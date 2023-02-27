package com.cricketGamewithspring.demo.services;

import com.cricketGamewithspring.demo.Repo.PlayerRepo;
import com.cricketGamewithspring.demo.model.Player;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class PlayerService implements  PlayerServiceInt{
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
}
