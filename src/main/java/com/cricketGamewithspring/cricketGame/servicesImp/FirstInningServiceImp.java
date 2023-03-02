package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.model.Ball;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.Player;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.services.FirstInningService;
import com.cricketGamewithspring.cricketGame.services.RandomFunctionService;

import java.util.ArrayList;
import java.util.List;

public class FirstInningServiceImp implements FirstInningService {
    public List<Team> firstInnings(Team team1, Team team2, Match match, String tossWinningTeam, List<Ball> ballHistory) {
        Team BattingTeam = null;
        Team BowlingTeam = null;
        if (tossWinningTeam == team1.getTeamName()) {
            BattingTeam = team1;
            BowlingTeam = team2;
        } else {
            BattingTeam = team2;
            BowlingTeam = team1;
        }
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
        RandomFunctionService randomFunctionService = new RandomFunctionServiceImp();
        for (overnum = 0; overnum < match.getTotalOvers(); overnum++) {
            for (ballnum = 0; ballnum < 6; ballnum++) {
                Bowler.incrementBallsBowled();
                int run = randomFunctionService.randomFunction();
                Ball nball = new Ball(overnum, ballnum + 1, Bowler, Striker, run, 1);
                ballHistory.add(nball);
                if (run == 7) {
                    Striker.incrementBallsFaced();
                    BattingTeam.incrementWicket();
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
                    BattingTeam.incrementRun(run);
                    Striker.incrementRun(run);
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
        List<Team> nList = new ArrayList<>();
        nList.add(BattingTeam);
        nList.add(BowlingTeam);
        return nList;
    }
}
