package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.Repo.MatchRepo;
import com.cricketGamewithspring.cricketGame.model.Ball;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.serviceInt.FirstInningServiceInt;
import com.cricketGamewithspring.cricketGame.serviceInt.PlayMatchServiceInt;
import com.cricketGamewithspring.cricketGame.serviceInt.SecondInningServiceInt;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class PlayMatchService implements PlayMatchServiceInt {

    @Autowired
    MatchRepo matchRepo;

    public String playMatch(Team team1, Team team2, Match match, String tossWinningTeam) {
        ArrayList<Ball> ballHistory = new ArrayList<>();
        FirstInningServiceInt firstInningService = new FirstInningService();
        List<Team> output = firstInningService.firstInnings(team1, team2, match, tossWinningTeam, ballHistory);
        SecondInningServiceInt secondInningService = new SecondInningService();
        output = secondInningService.secondInnings(output.get(0), output.get(1), match, ballHistory);

        Team BattingTeam = output.get(0);
        Team BowlingTeam = output.get(1);
        String result;
        if (BattingTeam.getScore() > BowlingTeam.getScore()) {
            result = BattingTeam.getTeamName() + " " + "has won the match";
        } else if (BattingTeam.getScore() < BowlingTeam.getScore()) {
            result = BowlingTeam.getTeamName() + " " + "has won the match";
        } else {
            result = "MATCH DRAWN";
        }
        return result;
    }
}
