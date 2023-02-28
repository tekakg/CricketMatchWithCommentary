package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.Repo.MatchDetailRepo;
import com.cricketGamewithspring.cricketGame.Repo.MatchRepo;
import com.cricketGamewithspring.cricketGame.Repo.PlayerRepo;
import com.cricketGamewithspring.cricketGame.Repo.ScoreboardRepo;
import com.cricketGamewithspring.cricketGame.exceptionHandler.ResourceNotFound;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.model.*;
import com.cricketGamewithspring.cricketGame.serviceInt.CricketServiceInt;
import com.cricketGamewithspring.cricketGame.serviceInt.StartMatchServiceInt;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
@Slf4j

public class CricketService implements CricketServiceInt {
    private final MatchRepo matchRepo;
    private final PlayerRepo playerRepo;
    private final MatchDetailRepo matchDetailRepo;
    private final ScoreboardRepo scoreboardRepo;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final Match match;
    private final Scoreboard scoreboard;

    public Scoreboard createMatch(MatchDetail matchDetail) {//Data is directly passed to the database.
        if (matchDetail.getOvers() <= 0) {
            throw new ResourceNotFound("Wrong Input:" + " " + "Overs should not be less than or equal to 0.");
        }
        if (matchDetail.getPlayerCount() <= 0) {
            throw new ResourceNotFound("Wrong Input:" + " " + "PlayerCount should not be less than or equal to 0.");
        }
        List<Integer> team1PlayerId = matchDetail.getTeam1Players();
        List<Integer> team2PlayerId = matchDetail.getTeam2Players();

        if (team1PlayerId.size() != matchDetail.getPlayerCount()) {
            throw new ResourceNotFound("Wrong Input:" + " " + "team1Players size should be equal to playerCount");
        }

        if (team2PlayerId.size() != matchDetail.getPlayerCount()) {
            throw new ResourceNotFound("Wrong Input:" + " " + "team2Players size should be equal to playerCount");
        }

        for (int playerId : team1PlayerId) {   //checking whether all players present in database or not.
            if (playerRepo.countById(playerId) == 0) {
                throw new ResourceNotFound("Wrong Input:" + " " + "playerNumber" + " " + playerId + " " + "is not found.");
            }
        }

        for (int playerId : team2PlayerId) {
            if (playerRepo.countById(playerId) == 0) {
                throw new ResourceNotFound("Wrong Input:" + " " + "playerNumber" + " " + playerId + " " + "is not found.");
            }
        }
        matchDetail.setId(sequenceGeneratorService.generateSequence(matchDetail.SEQUENCE_NAME));
        matchDetailRepo.save(matchDetail);
        Team team1 = new Team();
        Team team2 = new Team();
        team1.setTeamName(matchDetail.getTeam1Name());
        team1.setScore(0);
        team1.setOverNumber(0);
        team1.setBallNumber(0);
        team1.setWicket(0);
        team1.setTotalPlayers(matchDetail.getPlayerCount());
        ArrayList<Player> playerTeam1 = new ArrayList<>();
        for (int playerId : matchDetail.getTeam1Players()) {
            Optional<Player> nPlayer = playerRepo.findById(playerId);
            playerTeam1.add(nPlayer.get());

        }
        team1.setListOfPlayers(playerTeam1);
        team2.setTeamName(matchDetail.getTeam2Name());
        team2.setScore(0);
        team2.setBallNumber(0);
        team2.setOverNumber(0);
        team1.setWicket(0);
        team2.setTotalPlayers(matchDetail.getPlayerCount());
        ArrayList<Player> playerTeam2 = new ArrayList<>();
        for (int playerId : matchDetail.getTeam2Players()) {
            Optional<Player> nPlayer = playerRepo.findById(playerId);
            playerTeam2.add(nPlayer.get());
        }
        team2.setListOfPlayers(playerTeam2);
        match.setTotalOvers(matchDetail.getOvers());
        StartMatchServiceInt startMatchService = new StartMatchService();
        match.setId(sequenceGeneratorService.generateSequence(match.SEQUENCE_NAME));
        scoreboard.setScoreBoardId(sequenceGeneratorService.generateSequence(scoreboard.SEQUENCE_NAME));
        return startMatchService.startMatch(team1, team2, match, scoreboard, matchRepo, scoreboardRepo);
    }
}
