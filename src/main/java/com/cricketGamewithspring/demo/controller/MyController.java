package com.cricketGamewithspring.demo.controller;

import com.cricketGamewithspring.demo.model.MatchDetail;
import com.cricketGamewithspring.demo.model.Player;
import com.cricketGamewithspring.demo.services.CricketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private CricketService cricketService;

    @GetMapping("/")
    public String message() {
        return "Lets Start the Match";
    }

    @PostMapping("/match-info")
    public ResponseEntity<String> setMatch(@RequestBody MatchDetail matchDetail) {
        //service will create the match object and save it in CricketDao.
        return cricketService.createMatch(matchDetail);
    }
    @PostMapping("/player-info")
    public ResponseEntity<String> setPlayer(@RequestBody Player player){
        return cricketService.setPlayer(player);
    }

    @PostMapping("/match-started")
    public ResponseEntity<String>startMatch()
    {
        return cricketService.startMatch();
    }


//    @GetMapping("/flip-the-coin")
//    public String getTossResult() {
//        return cricketService.getTossResult();
//    }
//
}