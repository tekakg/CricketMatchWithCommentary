package com.cricketGamewithspring.demo.services;

import com.cricketGamewithspring.demo.Repo.MatchDetailRepo;
import com.cricketGamewithspring.demo.Repo.MatchRepo;
import com.cricketGamewithspring.demo.Repo.PlayerRepo;
import com.cricketGamewithspring.demo.Repo.ScoreboardRepo;
import com.cricketGamewithspring.demo.helper.Team;
import com.cricketGamewithspring.demo.model.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Data
@Service
@RequiredArgsConstructor
@Slf4j

public class CricketService {


    private final MatchRepo matchRepo;

    private final PlayerRepo playerRepo;

    private final MatchDetailRepo matchDetailRepo;

    private final ScoreboardRepo scoreboardRepo;

    private final SequenceGeneratorService sequenceGeneratorService;

    private final Match match;

    private final Scoreboard scoreboard;


    public Scoreboard createMatch(MatchDetail matchDetail) {//Data is directly passed to the database.
        if (matchDetail.getOvers() <= 0) {
            return null;
        }
        if (matchDetail.getPlayerCount() <= 0) {
            return null;
        }
        int[] team1playerid = matchDetail.getTeam1Players();
        int[] team2playerid = matchDetail.getTeam2Players();

        for (int playerid : team1playerid) {   //checking whether all players present in database or not.
            if (playerRepo.findById(playerid) == null) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No information for the player is present");
                return null;
            }
        }
        for (int playerid : team2playerid) {
            if (playerRepo.findById(playerid) == null) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No information for the player is present");
                return null;
            }
        }
        matchDetail.setId(sequenceGeneratorService.generateSequence(matchDetail.SEQUENCE_NAME));
        //It means match details are correct and now you can create teams.
        matchDetailRepo.save(matchDetail);

        Team team1 = new Team();
        Team team2 = new Team();
        //Creating team1
        team1.setTeamName(matchDetail.getTeam1Name());
        team1.setScore(0);
        team1.setOverNumber(0);
        team1.setBallNumber(0);
        team1.setWicket(0);
        team1.setTotalPlayers(matchDetail.getPlayerCount());
        ArrayList<Player> playerteam1 = new ArrayList<>();
        for (int playerId : matchDetail.getTeam1Players()) {
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
        for (int playerId : matchDetail.getTeam2Players()) {
            Player nplayer = playerRepo.findById(playerId);
            playerteam2.add(nplayer);
        }
        team2.setListOfPlayers(playerteam2);
        match.setTotalOvers(matchDetail.getOvers());
        return this.startMatch(team1, team2);
    }

    private Scoreboard startMatch(Team team1, Team team2) {
            match.setTeam1Name(team1.getTeamName());
            match.setTeam2Name(team2.getTeamName());
            TossService tossService = new TossService();
            String tossWinningTeam = tossService.getToss(team1, team2);
            match.setTossResult(tossWinningTeam + " " + "has won the toss and elected to bat first");
            PlaymatchService playmatchService = new PlaymatchService();
            match.setMatchResult(playmatchService.playMatch(team1, team2, match, tossWinningTeam));
            match.setId(sequenceGeneratorService.generateSequence(match.SEQUENCE_NAME));
            matchRepo.save(match);
            scoreboard.setMatchId(match.getId());
            scoreboard.setTeam1(team1);
            scoreboard.setTeam2(team2);
            scoreboard.setScoreBoardId(sequenceGeneratorService.generateSequence(scoreboard.SEQUENCE_NAME));
            scoreboardRepo.save(scoreboard);
            //take help of helper function to start the game.
            return scoreboard;
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
