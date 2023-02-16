package com.cricketGamewithspring.demo.services;

import com.cricketGamewithspring.demo.Repo.MatchDetailRepo;
import com.cricketGamewithspring.demo.Repo.MatchRepo;
import com.cricketGamewithspring.demo.Repo.PlayerRepo;
import com.cricketGamewithspring.demo.Repo.ScoreboardRepo;
import com.cricketGamewithspring.demo.helper.Team;
import com.cricketGamewithspring.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CricketService {

    @Autowired
    MatchRepo matchRepo;
    @Autowired
    PlayerRepo playerRepo;
    @Autowired
    MatchDetailRepo matchDetailRepo;
    @Autowired
    ScoreboardRepo scoreboardRepo;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    Match match = new Match();
    Scoreboard scoreboard = new Scoreboard();
    Team team1 = new Team();
    Team team2 = new Team();

    public ResponseEntity<String> createMatch(MatchDetail matchDetail) {//Data is directly passed to the database.
        if (matchDetail.getOvers() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Overs can't be less than or equal to 0");
        }
        if (matchDetail.getPlayerCount() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PlayerCount can't be less than or equal to 0");
        }

//        if(matchDetail.getTeam1Name().equals(matchDetail.getTeam2Name())==true)
//        {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team1Name can't be similar to Team2Name");
//        }
        int[] team1playerid = matchDetail.getPlayerTeam1id();
        int[] team2playerid = matchDetail.getPlayerTeam2id();

        for (int playerid : team1playerid) {   //checking whether all players present in database or not.
            if (playerRepo.findById(playerid) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No information for the player is present");
            }
        }
        for (int playerid : team2playerid) {
            if (playerRepo.findById(playerid) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No information for the player is present");
            }
        }
        matchDetail.setId(sequenceGeneratorService.generateSequence(matchDetail.SEQUENCE_NAME));
        //It means match details are correct and now you can create teams.
        matchDetailRepo.save(matchDetail);
        //Creating team1
        team1.setTeamName(matchDetail.getTeam1Name());
        team1.setScore(0);
        team1.setOverNumber(0);
        team1.setBallNumber(0);
        team1.setWicket(0);
        team1.setTotalPlayers(matchDetail.getPlayerCount());
        ArrayList<Player> playerteam1 = new ArrayList<>();
        for (int playerId : team1playerid) {
            Player nplayer = playerRepo.findById(playerId);
            playerteam1.add(nplayer);

        }
        team1.setListOfPlayers(playerteam1);

        //Creating team2
        team2.setTeamName(matchDetail.getTeam2Name());
        team2.setScore(0);
        team2.setBallNumber(0);
        team2.setOverNumber(0);
        team1.setWicket(0);
        team2.setTotalPlayers(matchDetail.getPlayerCount());
        ArrayList<Player> playerteam2 = new ArrayList<>();
        for (int playerId : team2playerid) {
            Player nplayer = playerRepo.findById(playerId);
            playerteam2.add(nplayer);
        }
        team2.setListOfPlayers(playerteam2);

        match.setTotalOvers(matchDetail.getOvers());
        return ResponseEntity.ok("Both Teams have successfully created");

    }

    public ResponseEntity<String> startMatch() {

        match.setTeam1Name(team1.getTeamName());
        match.setTeam2Name(team2.getTeamName());
        Helperfun helperfun = new Helperfun();
        match.setTossResult(helperfun.getToss());
        match.setMatchResult(helperfun.playMatch(team1, team2, match));
        match.setId(sequenceGeneratorService.generateSequence(match.SEQUENCE_NAME));
        matchRepo.save(match);
        scoreboard.setMatchId(match.getId());
        scoreboard.setTeam1(team1);
        scoreboard.setTeam2(team2);
        scoreboard.setScoreBoardId(sequenceGeneratorService.generateSequence(scoreboard.SEQUENCE_NAME));
        scoreboardRepo.save(scoreboard);
        //take help of helper function to start the game.
        return ResponseEntity.ok("Match has Started");
    }

    public ResponseEntity<String> setPlayer(Player player) {//Data is directly passed to the database.
        if (playerRepo.findByName(player.getName()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player already existed");
        }
        player.setId(sequenceGeneratorService.generateSequence(Player.SEQUENCE_NAME));
        playerRepo.save(player);
        return ResponseEntity.ok("Player is Successfully Added to the PlayerList");
    }
}
