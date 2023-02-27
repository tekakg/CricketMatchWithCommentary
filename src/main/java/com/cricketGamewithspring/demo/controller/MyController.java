package com.cricketGamewithspring.demo.controller;

import com.cricketGamewithspring.demo.model.Match;
import com.cricketGamewithspring.demo.model.MatchDetail;
import com.cricketGamewithspring.demo.model.Player;
import com.cricketGamewithspring.demo.model.Scoreboard;
import com.cricketGamewithspring.demo.services.CricketService;
import com.cricketGamewithspring.demo.services.CricketServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MyController {

    @Autowired
    private CricketServiceInt cricketService;

    @GetMapping("/")
    public String message() {
        return "Lets Start the Match";
    }

    @PostMapping("/match-info")
    public Scoreboard setMatch(@RequestBody MatchDetail matchDetail) {
        //service will create the match object and save it in MatchDao.
     //   System.out.println(matchDetail.getTeam1Players().size());
        System.out.println(matchDetail);
        return cricketService.createMatch(matchDetail);
    }

    @PostMapping("/player-info")
    public ResponseEntity<String> setPlayer(@RequestBody Player player) {
        return cricketService.setPlayer(player);
    }

//    @PostMapping("/match-started")
//    public ResponseEntity<String> startMatch() {
//        return cricketService.startMatch();
//    }


//    @GetMapping("/flip-the-coin")
//    public String getTossResult() {
//        return cricketService.getTossResult();
//    }
//

    @GetMapping("/scoreboard/{matchid}")
    public Optional<Scoreboard> getscoreboard(@PathVariable String matchid){
        Optional<Scoreboard>scoreboard=cricketService.getScoreboard(Integer.parseInt(matchid));
        return Optional.ofNullable(scoreboard.orElse(null));
    }
    @GetMapping("/match/{matchid}")
    public Optional<Match> getMatch(@PathVariable String matchid)
    {   Optional<Match>match=cricketService.getMatch(Integer.parseInt(matchid));
        return Optional.ofNullable(match.orElse(null));
    }
}