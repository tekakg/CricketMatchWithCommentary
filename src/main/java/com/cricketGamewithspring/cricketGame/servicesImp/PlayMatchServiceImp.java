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
// This class is responsible for playing a match between two teams
public class PlayMatchServiceImp implements PlayMatchService {


    // Autowiring InningServiceImp for accessing its methods
    @Autowired
    private InningServiceImp inningServiceImp;

    // This method plays the match between two teams and returns the result
    public String playMatch(Team team1, Team team2, Match match, String tossWinningTeam) {

        // Initializing an empty list to store ball-by-ball commentary
        List<Ball> ballHistory = new ArrayList<>();

        // Initializing batting and bowling teams based on the toss result
        Team BattingTeam = null;
        Team BowlingTeam = null;
        if (tossWinningTeam == team1.getTeamName()) {
            BattingTeam = team1;
            BowlingTeam = team2;
        } else {
            BattingTeam = team2;
            BowlingTeam = team1;
        }

        // Running the innings of the batting and bowling team respectively
        inningServiceImp.matchInnings(BattingTeam, BowlingTeam, match, ballHistory);
        inningServiceImp.matchInnings(BowlingTeam, BattingTeam, match, ballHistory);

        // Setting the ball-by-ball commentary to the match object
        match.setCommentary((ArrayList<Ball>) ballHistory);

        // Calculating the result of the match based on the scores of the two teams
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

