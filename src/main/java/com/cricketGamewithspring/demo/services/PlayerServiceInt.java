package com.cricketGamewithspring.demo.services;

import com.cricketGamewithspring.demo.model.Player;
import org.springframework.http.ResponseEntity;

public interface PlayerServiceInt {
    public ResponseEntity<String> setPlayer(Player player);
}
