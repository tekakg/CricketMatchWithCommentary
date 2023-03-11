package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.Player;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlayerService {
    ResponseEntity<String> setPlayer(Player player);

    List<Player> getPlayerUsingRole(String role);

    Player getPlayerUsingName(String name);
}
