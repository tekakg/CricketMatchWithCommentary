package com.cricketGamewithspring.demo.services;

import com.cricketGamewithspring.demo.model.Match;
import com.cricketGamewithspring.demo.model.MatchDetail;
import com.cricketGamewithspring.demo.model.Player;
import com.cricketGamewithspring.demo.model.Scoreboard;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CricketServiceInt {

    public Scoreboard createMatch(MatchDetail matchDetail);
    public ResponseEntity<String> setPlayer(Player player);

    Optional<Scoreboard> getScoreboard(int parseInt);

    Optional<Match> getMatch(int parseInt);
}
