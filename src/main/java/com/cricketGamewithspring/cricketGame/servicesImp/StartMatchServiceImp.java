package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.Repo.MatchRepo;
import com.cricketGamewithspring.cricketGame.Repo.ScoreboardRepo;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.Scoreboard;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.services.PlayMatchService;
import com.cricketGamewithspring.cricketGame.services.StartMatchService;
import com.cricketGamewithspring.cricketGame.services.TossService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class StartMatchServiceImp implements StartMatchService {
    public Scoreboard startMatch(Team team1, Team team2, Match match, Scoreboard scoreboard, MatchRepo matchRepo, ScoreboardRepo scoreboardRepo) {
        match.setTeam1Name(team1.getTeamName());
        match.setTeam2Name(team2.getTeamName());
        TossService tossService = new TossServiceImp();
        String tossWinningTeam = tossService.getToss(team1, team2);
        match.setTossResult(tossWinningTeam + " " + "has won the toss and elected to bat first");
        PlayMatchService playMatchService = new PlayMatchServiceImp();
        match.setMatchResult(playMatchService.playMatch(team1, team2, match, tossWinningTeam));
        matchRepo.save(match);
        scoreboard.setMatchId(match.getId());
        scoreboard.setTeam1(team1);
        scoreboard.setTeam2(team2);
        scoreboardRepo.save(scoreboard);
        //take help of helper function to start the game.
        return scoreboard;
    }
}
