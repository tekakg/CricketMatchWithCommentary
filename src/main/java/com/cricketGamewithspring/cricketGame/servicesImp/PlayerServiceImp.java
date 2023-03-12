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
public class PlayerServiceImp implements PlayerService {
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private ElasticRepo elasticRepo;

    public ResponseEntity<String> setPlayer(Player player) {
        if (playerRepo.findByName(player.getName()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player already existed");
        } else {
            player.setId((int) playerRepo.count() + 1);
            playerRepo.save(player);
            Player savedPlayer = playerRepo.findByName(player.getName());
            elasticRepo.save(savedPlayer);
            return ResponseEntity.ok("Player is Successfully Added to the PlayerList");
        }
    }

    public Player getPlayer(int playerId) {
        Optional<Player> player = playerRepo.findById(playerId);
        return player.get();
    }

    public List<Player> getPlayerUsingRole(String role) {
        List<Player> playerList = playerRepo.findAllByRole(role);
        return playerList;
    }

    public Player getPlayerUsingName(String name) {
        Player player = playerRepo.findByName(name);
        return player;
    }
}
