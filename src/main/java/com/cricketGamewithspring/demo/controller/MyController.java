package com.cricketGamewithspring.demo.controller;

import com.cricketGamewithspring.demo.exceptionHandler.ResourceNotFound;
import com.cricketGamewithspring.demo.model.Match;
import com.cricketGamewithspring.demo.model.MatchDetail;
import com.cricketGamewithspring.demo.model.Player;
import com.cricketGamewithspring.demo.model.Scoreboard;
import com.cricketGamewithspring.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MyController {

    @Autowired
    private CricketServiceInt cricketService;

    @Autowired
    private ScoreboardServiceInt scoreboardService;

    @Autowired
    private MatchDetailServiceInt matchService;

    @Autowired
    private PlayerServiceInt playerService;

    @GetMapping("/")
    public String message() {
        return "Lets Start the Match";
    }

    @PostMapping("/match-info")
    public Scoreboard setMatch(@RequestBody MatchDetail matchDetail) {
        return cricketService.createMatch(matchDetail);
    }

    @PostMapping("/player-info")
    public ResponseEntity<String> setPlayer(@RequestBody Player player) {
        return playerService.setPlayer(player);
    }


    @GetMapping("/scoreboard/{matchId}")
    public Optional<Scoreboard> getScoreboard(@PathVariable String matchId) {
        Optional<Scoreboard> scoreboard = scoreboardService.getScoreboard(Integer.parseInt(matchId));
        if(scoreboard==null)
        {
            throw new ResourceNotFound("No scoreboard corresponding to this matchId");
        }
        return Optional.ofNullable(scoreboard.orElse(null));
    }

    @GetMapping("/match-history/{matchId}")
    public Optional<Match>getMatch(@PathVariable String matchId) {
        Optional<Match> match = matchService.getMatch(Integer.parseInt(matchId));
        return Optional.ofNullable(match.orElse(null));
    }
}