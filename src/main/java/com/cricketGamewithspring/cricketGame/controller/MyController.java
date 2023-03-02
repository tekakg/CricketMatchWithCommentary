package com.cricketGamewithspring.cricketGame.controller;

import com.cricketGamewithspring.cricketGame.exceptionHandler.ResourceNotFound;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.MatchDetail;
import com.cricketGamewithspring.cricketGame.model.Player;
import com.cricketGamewithspring.cricketGame.model.Scoreboard;
import com.cricketGamewithspring.cricketGame.services.CricketService;
import com.cricketGamewithspring.cricketGame.services.MatchDetailService;
import com.cricketGamewithspring.cricketGame.services.PlayerService;
import com.cricketGamewithspring.cricketGame.services.ScoreboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MyController {

    @Autowired
    private CricketService cricketService;

    @Autowired
    private ScoreboardService scoreboardService;

    @Autowired
    private MatchDetailService matchService;

    @Autowired
    private PlayerService playerService;

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
        if (scoreboard == null) {
            throw new ResourceNotFound("No scoreboard corresponding to this matchId");
        }
        return Optional.ofNullable(scoreboard.orElse(null));
    }

    @GetMapping("/match-history/{matchId}")
    public Match getMatch(@PathVariable String matchId) {
        Optional<Match> match = matchService.getMatch(Integer.parseInt(matchId));
        if (match.stream().count() == 0)
            throw new ResourceNotFound("No match with this matchId");
        return match.get();
    }

    @GetMapping("/player-info-using-name/{playerName}")
    public Player getPlayerUsingName(@PathVariable String playerName) {
        if (playerService.getPlayerUsingName(playerName) == null)
            throw new ResourceNotFound("No player with this name");
        return playerService.getPlayerUsingName(playerName);
    }

    @GetMapping("/players-info-using-role/{playerRole}")
    public List<Player> getPlayerUsingRole(@PathVariable String playerRole) {
        if (playerService.getPlayerUsingRole(playerRole) == null)
            throw new ResourceNotFound("No player with this role");
        return playerService.getPlayerUsingRole(playerRole);
    }

    @GetMapping("/team1/{matchId}")
    public Team getTeam1(@PathVariable String matchId) {
        return scoreboardService.getTeam1(Integer.parseInt(matchId));
    }

    @GetMapping("/team2/{matchId}")
    public Team getTeam2(@PathVariable String matchId) {
        return scoreboardService.getTeam2(Integer.parseInt(matchId));
    }
}