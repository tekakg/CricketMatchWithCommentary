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

    public Optional<Scoreboard> createMatch(MatchDetail matchDetail) {
        // Get the player IDs for each team and retrieve all players from the repository
        List<Integer> team1PlayerId = matchDetail.getTeam1Players();
        List<Integer> team2PlayerId = matchDetail.getTeam2Players();
        List<Player> allPlayersInRep = playerRepo.findAll();

        // Validate the match details to ensure they are valid
        ValidateMatchDetails(matchDetail, team1PlayerId, team2PlayerId, allPlayersInRep);

        // Save the match details to the database
        matchDetailRepo.save(matchDetail);

        // Create empty lists to hold player information for each team
        List<Player> team1PlayersInfo = new ArrayList<>();
        List<Player> team2PlayersInfo = new ArrayList<>();

        // Create the two teams using the match details and player information
        Team team1 = this.createTeam1(matchDetail, team1PlayerId, team1PlayersInfo, allPlayersInRep);
        Team team2 = this.createTeam2(matchDetail, team2PlayerId, team2PlayersInfo, allPlayersInRep);

        // Set the total overs for the match and generate unique IDs for the match and scoreboard
        match.setTotalOvers(matchDetail.getOvers());
        match.setId(sequenceGeneratorService.generateSequence(match.SEQUENCE_NAME));
        scoreboard.setScoreBoardId(sequenceGeneratorService.generateSequence(scoreboard.SEQUENCE_NAME));
        scoreboard.setMatchVenue(matchDetail.getMatchVenue());
        scoreboard = startMatchServiceImp.startMatch(team1, team2, match, scoreboard, matchRepo, scoreboardRepo);

        // Update the player statistics for each team
        updatePlayerStats(team1, team2, team1PlayersInfo, team2PlayersInfo);
        Optional<Scoreboard> newScoreboard = scoreboardRepo.findByMatchId(scoreboard.getMatchId());
        return newScoreboard;
    }


    // It Validate match details.
    private String ValidateMatchDetails(MatchDetail matchDetail, List<Integer> team1PlayerId, List<Integer> team2PlayerId, List<Player> allPlayersInRep) {
        ValidateOversAndPlayerCount(matchDetail);
        ValidateTeamSize(matchDetail, team1PlayerId, team2PlayerId);
        ValidateTeam1PlayerId(team1PlayerId, allPlayersInRep);
        ValidateTeam2PlayerId(team2PlayerId, allPlayersInRep);
        return "done";
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

    public Team createTeam1(MatchDetail matchDetail, List<Integer> team1PlayerId, List<Player> team1PlayersInfo, List<Player> allPlayersInRep) {

        List<Player> team1Players = new ArrayList<>();
        Team team1 = Team.builder().teamName(matchDetail.getTeam1Name()).score(0).overNumber(0)
                .ballNumber(0).wicket(0).totalPlayers(matchDetail.getPlayerCount()).
                listOfPlayers(team1Players).build();

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
        List<Player> team2Players = new ArrayList<>();
        Team team2 = Team.builder().teamName(matchDetail.getTeam2Name()).score(0).overNumber(0)
                .ballNumber(0).wicket(0).totalPlayers(matchDetail.getPlayerCount()).
                listOfPlayers(team2Players).build();

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
        }
        playerRepo.saveAll(listOfPlayers);

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
        }
        playerRepo.saveAll(listOfPlayers);
    }
}
