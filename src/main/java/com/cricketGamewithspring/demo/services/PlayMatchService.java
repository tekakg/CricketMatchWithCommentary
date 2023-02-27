package com.cricketGamewithspring.demo.services;

import com.cricketGamewithspring.demo.Repo.MatchRepo;
import com.cricketGamewithspring.demo.model.Ball;
import com.cricketGamewithspring.demo.model.Team;
import com.cricketGamewithspring.demo.model.Match;
import com.cricketGamewithspring.demo.model.Player;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Data
@Service
@RequiredArgsConstructor
public class PlayMatchService implements PlayMatchServiceInt {

    @Autowired
    MatchRepo matchRepo;

    public String playMatch(Team team1, Team team2, Match match, String winningTeam) {
        Team BattingTeam =null;
        Team BowlingTeam =null;
        if (winningTeam==team1.getTeamName()) {
            BattingTeam = team1;
            BowlingTeam = team2;
        } else {
            BattingTeam = team2;
            BowlingTeam = team1;
        }

        ArrayList<Ball> ballHistory = new ArrayList<>();
        Player playerNumber1 = null;
        Player playerNumber2 = null;
        playerNumber1 = BattingTeam.getListOfPlayers().get(0);
        playerNumber2 = BattingTeam.getListOfPlayers().get(1);
        Player Striker = playerNumber1;
        Player nonStriker = playerNumber2;
        Player Bowler = BowlingTeam.listOfPlayers.get(0);
        int flag = 0;
        int overnum = 0;
        int ballnum = 0;
        RandomFunctionServiceInt randomFunctionService=new RandomFunctionService();
        for (overnum = 0; overnum < match.getTotalOvers(); overnum++) {
            for (ballnum = 0; ballnum < 6; ballnum++) {
                Bowler.incrementBallsBowled();
                int run = randomFunctionService.randomFunction();
                Ball nball = new Ball(overnum, ballnum + 1, Bowler, Striker, run, 1);
                ballHistory.add(nball);
                if (run == 7) {
                    Striker.incrementBallsFaced();
                    BattingTeam.incrementwicket();
                    Bowler.incrementWickets();
                    if (BattingTeam.getWicket() == (BattingTeam.getTotalPlayers() - 1)) {

                        flag = 1;
                        break;
                    } else {
                        for (Player player : BattingTeam.listOfPlayers) {
                            if (Striker == player) {
                                player.setOutOrNot(true);
                                Striker = BattingTeam.getNewBatsman(nonStriker, BattingTeam);
                                break;
                            }
                        }
                    }
                } else {
                    BattingTeam.incrementrun(run);
                    Striker.incrementrun(run);
                    Striker.incrementBallsFaced();
                    if (run % 2 == 1) {
                        Player temp = Striker;
                        Striker = nonStriker;
                        nonStriker = temp;
                        //Strike change.
                    } else {

                    }
                }
            }
            Player temp = Striker;
            Striker = nonStriker;
            nonStriker = temp;
            int index = BowlingTeam.listOfPlayers.indexOf(Bowler);
            index++;
            index = (int) (index % (BowlingTeam.getTotalPlayers()));
            Bowler = BowlingTeam.listOfPlayers.get(index);
            if (flag == 1) {
                break;
            }
        }
        BattingTeam.setOverNumber(overnum);
        BattingTeam.setBallNumber(ballnum);
        overnum = 0;
        ballnum = 0;
        flag = 0;
        playerNumber1 = BowlingTeam.listOfPlayers.get(0);
        playerNumber2 = BowlingTeam.listOfPlayers.get(1);
        Striker = playerNumber1;
        nonStriker = playerNumber2;
        Bowler = BattingTeam.listOfPlayers.get(0);
        for (overnum = 0; overnum < match.getTotalOvers(); overnum++) {
            for (ballnum = 0; ballnum < 6; ballnum++) {
                int run = randomFunctionService.randomFunction();
                Bowler.incrementBallsBowled();
                Ball nball = new Ball(overnum, ballnum + 1, Bowler, Striker, run, 2);
                ballHistory.add(nball);
                //System.out.println(run);
                if (run == 7) {
                    Striker.incrementBallsFaced();
                    BowlingTeam.incrementwicket();
                    Bowler.incrementWickets();
                    if (BowlingTeam.getWicket() == BowlingTeam.getTotalPlayers() - 1) {
                        flag = 1;
                        break;
                    } else {
                        for (Player player : BowlingTeam.listOfPlayers) {
                            if (player == Striker) {
                                Striker.setOutOrNot(true);
                                Striker = BowlingTeam.getNewBatsman(nonStriker, BowlingTeam);
                                break;
                            }
                        }
                    }
                } else {
                    if (BowlingTeam.getScore() > BattingTeam.getScore()) {
                        flag = 1;
                        break;
                    }
                    BowlingTeam.incrementrun(run);
                    Striker.incrementrun(run);
                    Striker.incrementBallsFaced();
                    if (run % 2 == 1) {
                        Player temp = Striker;
                        Striker = nonStriker;
                        nonStriker = temp;
                        //Strike change.
                    } else {

                    }
                }
            }// changing the strike at over change.
            Player temp = Striker;
            Striker = nonStriker;
            nonStriker = temp;
            int index = BattingTeam.listOfPlayers.indexOf(Bowler);
            index++;
            index = (int) (index % (BattingTeam.getTotalPlayers()));
            Bowler = BattingTeam.listOfPlayers.get(index);
            if (flag == 1) {
                break;
            }
        }
        BowlingTeam.setBallNumber(ballnum);
        BowlingTeam.setOverNumber(overnum);
        match.setCommentary(ballHistory);
        String result;
        if (BattingTeam.getScore() > BowlingTeam.getScore()) {
            result = BattingTeam.getTeamName() + " " + "has won the match";
        } else if (BattingTeam.getScore() < BowlingTeam.getScore()) {
            result = BowlingTeam.getTeamName() + " "+"has won the match";
        } else {
            result = "MATCH DRAWN";
        }
        return result;
    }
}
