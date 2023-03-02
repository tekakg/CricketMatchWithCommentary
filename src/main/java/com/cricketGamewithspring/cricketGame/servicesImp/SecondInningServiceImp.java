package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.model.Ball;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.Player;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.services.RandomFunctionService;
import com.cricketGamewithspring.cricketGame.services.SecondInningService;

import java.util.ArrayList;
import java.util.List;

public class SecondInningServiceImp implements SecondInningService {
    @Override
    public List<Team> secondInnings(Team team1, Team team2, Match match, List<Ball> ballHistory) {
        Team BowlingTeam = team2;
        Team BattingTeam = team1;
        int overnum = 0;
        int ballnum = 0;
        int flag = 0;
        Player playerNumber1 = BowlingTeam.listOfPlayers.get(0);
        Player playerNumber2 = BowlingTeam.listOfPlayers.get(1);
        Player Striker = playerNumber1;
        Player nonStriker = playerNumber2;
        Player Bowler = BattingTeam.listOfPlayers.get(0);
        RandomFunctionService randomFunctionService = new RandomFunctionServiceImp();
        for (overnum = 0; overnum < match.getTotalOvers(); overnum++) {
            for (ballnum = 0; ballnum < 6; ballnum++) {
                int run = randomFunctionService.randomFunction();
                Bowler.incrementBallsBowled();
                Ball nball = new Ball(overnum, ballnum + 1, Bowler, Striker, run, 2);
                ballHistory.add(nball);
                //System.out.println(run);
                if (run == 7) {
                    Striker.incrementBallsFaced();
                    BowlingTeam.incrementWicket();
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
                    BowlingTeam.incrementRun(run);
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
        match.setCommentary((ArrayList<Ball>) ballHistory);
        List<Team> nList = new ArrayList<>();
        nList.add(BattingTeam);
        nList.add(BowlingTeam);
        return nList;
    }
}
