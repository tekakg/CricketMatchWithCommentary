package com.cricketGamewithspring.cricketGame.controller;

import com.cricketGamewithspring.cricketGame.exceptionHandler.ResourceNotFound;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.MatchDetail;
import com.cricketGamewithspring.cricketGame.model.Player;
import com.cricketGamewithspring.cricketGame.model.Scoreboard;
import com.cricketGamewithspring.cricketGame.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/set-player-info")
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
    @GetMapping("/player-info-using-name/{playerName}")
    public Player getPlayerUsingName(@PathVariable String playerName)
    {
        return playerService.getPlayerUsingName(playerName);
    }
    @GetMapping("/players-info-using-role/{playerRole}")
    public List<Player> getPlayerUsingRole(@PathVariable String playerRole)
    {
        return playerService.getPlayerUsingRole(playerRole);
    }
    @GetMapping("/team1/{matchId}")
    public Team getTeam1(@PathVariable String matchId)
    {
        return scoreboardService.getTeam1(Integer.parseInt(matchId));
    }
    @GetMapping("/team2/{matchId}")
    public Team getTeam2(@PathVariable String matchId)
    {
        return scoreboardService.getTeam2(Integer.parseInt(matchId));
    }
}