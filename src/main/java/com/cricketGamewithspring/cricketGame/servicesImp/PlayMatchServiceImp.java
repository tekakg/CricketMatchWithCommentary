package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.model.Ball;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.services.PlayMatchService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class PlayMatchServiceImp implements PlayMatchService {


    @Autowired
    private InningServiceImp inningServiceImp;

    public String playMatch(Team team1, Team team2, Match match, String tossWinningTeam) {
        List<Ball> ballHistory = new ArrayList<>();
        Team BattingTeam=null;
        Team BowlingTeam=null;
        if (tossWinningTeam == team1.getTeamName()) {
            BattingTeam = team1;
            BowlingTeam = team2;
        } else {
            BattingTeam = team2;
            BowlingTeam = team1;
        }
        inningServiceImp.matchInnings(BattingTeam,BowlingTeam,match,ballHistory);
        inningServiceImp.matchInnings(BowlingTeam,BattingTeam,match,ballHistory);
        match.setCommentary((ArrayList<Ball>) ballHistory);

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
