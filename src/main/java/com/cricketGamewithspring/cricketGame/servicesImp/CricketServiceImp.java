package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.Repo.MongoRepo.MatchDetailRepo;
import com.cricketGamewithspring.cricketGame.Repo.MongoRepo.MatchRepo;
import com.cricketGamewithspring.cricketGame.Repo.SQLRepo.PlayerRepo;
import com.cricketGamewithspring.cricketGame.Repo.MongoRepo.ScoreboardRepo;
import com.cricketGamewithspring.cricketGame.exceptionHandler.ResourceNotFound;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.model.*;
import com.cricketGamewithspring.cricketGame.services.CricketService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class CricketServiceImp implements CricketService {
    @Autowired
    private MatchRepo matchRepo;
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private MatchDetailRepo matchDetailRepo;
    @Autowired
    private ScoreboardRepo scoreboardRepo;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private StartMatchServiceImp startMatchServiceImp;
    @Autowired
    private Match match;
    @Autowired
    private Scoreboard scoreboard;

    public Scoreboard createMatch(MatchDetail matchDetail) {

        List<Integer> team1PlayerId = matchDetail.getTeam1Players();
        List<Integer> team2PlayerId = matchDetail.getTeam2Players();
        List<Player> allPlayersInRep = playerRepo.findAll();
        ValidateMatchDetails(matchDetail, team1PlayerId, team2PlayerId, allPlayersInRep);
        matchDetail.setId(sequenceGeneratorService.generateSequence(matchDetail.SEQUENCE_NAME));
        matchDetailRepo.save(matchDetail);

        List<Player> team1PlayersInfo = new ArrayList<>();
        List<Player> team2PlayersInfo = new ArrayList<>();
        Team team1 = createTeam1(matchDetail, team1PlayerId, team1PlayersInfo, allPlayersInRep);
        Team team2 = createTeam2(matchDetail, team2PlayerId, team2PlayersInfo, allPlayersInRep);

        match.setTotalOvers(matchDetail.getOvers());
        match.setId(sequenceGeneratorService.generateSequence(match.SEQUENCE_NAME));
        scoreboard.setScoreBoardId(sequenceGeneratorService.generateSequence(scoreboard.SEQUENCE_NAME));
        scoreboard = startMatchServiceImp.startMatch(team1, team2, match, scoreboard, matchRepo, scoreboardRepo);
        updatePlayerStats(team1, team2, team1PlayersInfo, team2PlayersInfo);
        return scoreboard;
    }


    private void ValidateMatchDetails(MatchDetail matchDetail, List<Integer> team1PlayerId, List<Integer> team2PlayerId, List<Player> allPlayersInRep) {

        ValidateOversAndPlayerCount(matchDetail);
        ValidateTeamSize(matchDetail, team1PlayerId, team2PlayerId);
        ValidateTeam1PlayerId(team1PlayerId, allPlayersInRep);
        ValidateTeam2PlayerId(team2PlayerId, allPlayersInRep);
    }

    private void ValidateOversAndPlayerCount(MatchDetail matchDetail) {
        if (matchDetail.getOvers() <= 0) {
            throw new ResourceNotFound("Wrong Input:" + " " + "Overs should not be less than or equal to 0.");
        }
        if (matchDetail.getPlayerCount() <= 0) {
            throw new ResourceNotFound("Wrong Input:" + " " + "PlayerCount should not be less than or equal to 0.");
        }
    }

    private void ValidateTeamSize(MatchDetail matchDetail, List<Integer> team1PlayerId, List<Integer> team2PlayerId) {
        if (team1PlayerId.size() != matchDetail.getPlayerCount()) {
            throw new ResourceNotFound("Wrong Input:" + " " + "team1Players size should be equal to playerCount");
        }

        if (team2PlayerId.size() != matchDetail.getPlayerCount()) {
            throw new ResourceNotFound("Wrong Input:" + " " + "team2Players size should be equal to playerCount");
        }
    }

    private void ValidateTeam1PlayerId(List<Integer> team1PlayerId, List<Player> allPlayersInRep) {
        Boolean doesPlayerExist = false;
        for (int playerId : team1PlayerId) {
            doesPlayerExist = false;
            for (Player player : allPlayersInRep) {
                if (player.getId() == playerId) {
                    doesPlayerExist = true;
                }
            }
            if (!doesPlayerExist) {
                throw new ResourceNotFound("Wrong Input:" + " " + "playerNumber" + " " + playerId + " " + "is not found.");
            }
        }
    }

    private void ValidateTeam2PlayerId(List<Integer> team2PlayerId, List<Player> allPlayersInRep) {
        Boolean doesPlayerExist = false;
        for (int playerId : team2PlayerId) {
            doesPlayerExist = false;
            for (Player player : allPlayersInRep) {
                if (player.getId() == playerId) {
                    doesPlayerExist = true;
                }
            }
            if (!doesPlayerExist) {
                throw new ResourceNotFound("Wrong Input:" + " " + "playerNumber" + " " + playerId + " " + "is not found.");
            }
        }
    }

    private Team createTeam1(MatchDetail matchDetail, List<Integer> team1PlayerId, List<Player> team1PlayersInfo, List<Player> allPlayersInRep) {
        Team team1 = new Team();
        team1.setTeamName(matchDetail.getTeam1Name());
        team1.setScore(0);
        team1.setOverNumber(0);
        team1.setBallNumber(0);
        team1.setWicket(0);
        team1.setTotalPlayers(matchDetail.getPlayerCount());
        List<Player> team1Players = new ArrayList<>();
        for (int playerId : team1PlayerId) {
            for (Player player : allPlayersInRep) {
                if (player.getId() == playerId) {
                    Player newPlayer = new Player(player);
                    team1PlayersInfo.add(newPlayer);
                    player.setRun(0);
                    player.setBallsFaced(0);
                    player.setBallsBowled(0);
                    player.setWickets(0);
                    team1Players.add(player);
                }
            }
        }
        team1.setListOfPlayers(team1Players);
        return team1;
    }

    private Team createTeam2(MatchDetail matchDetail, List<Integer> team2PlayerId, List<Player> team2PlayersInfo, List<Player> allPlayersInRep) {
        Team team2 = new Team();
        team2.setTeamName(matchDetail.getTeam2Name());
        team2.setScore(0);
        team2.setBallNumber(0);
        team2.setOverNumber(0);
        team2.setWicket(0);
        team2.setTotalPlayers(matchDetail.getPlayerCount());
        List<Player> team2Players = new ArrayList<>();

        for (int playerId : team2PlayerId) {
            for (Player player : allPlayersInRep) {
                if (player.getId() == playerId) {
                    Player newPlayer = new Player(player);
                    team2PlayersInfo.add(newPlayer);
                    player.setRun(0);
                    player.setBallsFaced(0);
                    player.setBallsBowled(0);
                    player.setWickets(0);
                    team2Players.add(player);
                }
            }
        }
        team2.setListOfPlayers(team2Players);
        return team2;
    }

    private void updatePlayerStats(Team team1, Team team2, List<Player> team1PlayersInfo, List<Player> team2PlayersInfo) {
        List<Player> listOfPlayers = team1.getListOfPlayers();
        int playerNum = 0;
        for (Player player : listOfPlayers) {
            Player playerStats = team1PlayersInfo.get(playerNum);
            player.incrementRun(playerStats.getRun());
            player.setWickets(player.getWickets() + playerStats.getWickets());
            player.setBallsFaced(player.getBallsFaced() + playerStats.getBallsFaced());
            player.setBallsBowled(player.getBallsBowled() + playerStats.getBallsBowled());
            player.setPout(false);
            playerNum++;
            playerRepo.save(player);
        }
        listOfPlayers.clear();
        listOfPlayers = team2.getListOfPlayers();
        playerNum = 0;
        for (Player player : listOfPlayers) {
            Player playerStats = team2PlayersInfo.get(playerNum);
            player.incrementRun(playerStats.getRun());
            player.setWickets(player.getWickets() + playerStats.getWickets());
            player.setBallsFaced(player.getBallsFaced() + playerStats.getBallsFaced());
            player.setBallsBowled(player.getBallsBowled() + playerStats.getBallsBowled());
            player.setPout(false);
            playerNum++;
            playerRepo.save(player);
        }
    }
}
