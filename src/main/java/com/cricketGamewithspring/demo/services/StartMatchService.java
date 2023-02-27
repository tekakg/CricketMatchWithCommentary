package com.cricketGamewithspring.demo.services;

import com.cricketGamewithspring.demo.Repo.MatchRepo;
import com.cricketGamewithspring.demo.Repo.ScoreboardRepo;
import com.cricketGamewithspring.demo.model.Match;
import com.cricketGamewithspring.demo.model.Scoreboard;
import com.cricketGamewithspring.demo.model.Team;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class StartMatchService implements StartMatchServiceInt{
   public Scoreboard startMatch(Team team1, Team team2, Match match,Scoreboard scoreboard,MatchRepo matchRepo,ScoreboardRepo scoreboardRepo) {
        match.setTeam1Name(team1.getTeamName());
        match.setTeam2Name(team2.getTeamName());
        TossServiceInt tossService = new TossService();
        String tossWinningTeam = tossService.getToss(team1, team2);
        match.setTossResult(tossWinningTeam + " " + "has won the toss and elected to bat first");
        PlayMatchServiceInt playMatchService= new PlayMatchService();
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
