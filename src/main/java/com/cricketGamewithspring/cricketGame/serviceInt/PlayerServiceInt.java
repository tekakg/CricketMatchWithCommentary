package com.cricketGamewithspring.cricketGame.serviceInt;

import com.cricketGamewithspring.cricketGame.model.Player;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlayerServiceInt {
    public ResponseEntity<String> setPlayer(Player player);

    public List<Player> getPlayerUsingRole(String role);

    public Player getPlayerUsingName(String name);
}
