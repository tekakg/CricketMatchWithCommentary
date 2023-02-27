package com.cricketGamewithspring.demo.controller;

import com.cricketGamewithspring.demo.model.MatchDetail;
import com.cricketGamewithspring.demo.model.Player;
import com.cricketGamewithspring.demo.model.Scoreboard;
import com.cricketGamewithspring.demo.services.CricketService;
import com.cricketGamewithspring.demo.services.CricketServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}