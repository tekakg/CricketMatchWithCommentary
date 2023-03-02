package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.Player;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlayerService {
    public ResponseEntity<String> setPlayer(Player player);

    public List<Player> getPlayerUsingRole(String role);

    public Player getPlayerUsingName(String name);
}
