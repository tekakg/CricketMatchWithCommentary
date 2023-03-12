package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.Repo.MongoRepo.MatchRepo;
import com.cricketGamewithspring.cricketGame.Repo.MongoRepo.ScoreboardRepo;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.Scoreboard;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.services.StartMatchService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class StartMatchServiceImp implements StartMatchService {

    @Autowired
    private TossServiceImp tossServiceImp;
    @Autowired
    private PlayMatchServiceImp playMatchServiceImp;

    public Scoreboard startMatch(Team team1, Team team2, Match match, Scoreboard scoreboard, MatchRepo matchRepo, ScoreboardRepo scoreboardRepo) {
        match.setTeam1Name(team1.getTeamName());
        match.setTeam2Name(team2.getTeamName());
        String tossWinningTeam = tossServiceImp.getToss(team1, team2);
        match.setTossResult(tossWinningTeam + " " + "has won the toss and elected to bat first");
        String matchWinningTeam = playMatchServiceImp.playMatch(team1, team2, match, tossWinningTeam);
        match.setMatchResult(matchWinningTeam);
        matchRepo.save(match);
        scoreboard.setMatchId(match.getId());
        scoreboard.setTeam1(team1);
        scoreboard.setTeam2(team2);
        scoreboard.setMatchResult(matchWinningTeam);
        scoreboardRepo.save(scoreboard);
        return scoreboard;
    }
}
