package com.cricketGamewithspring.demo.helper;

import com.cricketGamewithspring.demo.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private String teamName;
    private int totalPlayers;
    public List<Player> listOfPlayers = new ArrayList<>();
    //public int playerNumber;
//    public List<Ball> BattingBalls = new ArrayList<>();//when Team is batting.
//    public List<Ball> BowlingBalls = new ArrayList<>();// when Team is balling.
    private int score;
    private int wicket;
    private int overNumber;
    private int ballNumber;

    public void incrementwicket() {
        this.wicket++;
    }

    public void incrementrun(int run) {
        this.score+=run;
    }

    public Player getNewBatsman(Player nonStriker,Team BattingTeam) {
        Player nwPlayer=null;
        for (Player player : BattingTeam.getListOfPlayers()) {
            if (player.isOutOrNot()== false && player != nonStriker) {
                {
                    nwPlayer = player;
                    break;
                }
            }
        }
        return nwPlayer;
    }

//    public Team(String teamName, int totalPlayers, List<Player> listOfPlayers) {
//        this.teamName = teamName;
//        this.totalPlayers = totalPlayers;
//        this.listOfPlayers = listOfPlayers;
//    }
//
//    //    public void setTotalPlayers(int val)
////    {
////        this.totalPlayers=val;
////    }
////    public int getTotalPlayers()
////    {
////        return totalPlayers;
////    }
//
//    public Player getNewBatsman(Player nonStriker) {
//        Player nwPlayer = null;
//        for (Player player : listOfPlayers) {
//            if (player.outOrNot == false && player != nonStriker) {
//                {
//                    nwPlayer = player;
//                    break;
//                }
//            }
//        }
//        return nwPlayer;
//    }
//
//    public void incrementrun(int run) {
//        this.score += run;
//    }
//
//    public void incrementwicket() {
//        this.wicket += 1;
//    }
//
//
//    public void endInning() {
////        System.out.println("The Inning has ended");
//        int finalRun = this.getFinalScore();
////        System.out.println("The Score is " + finalRun);
//    }
//
//    public int getFinalScore() {
//        return this.score;
//    }
//
//    public int getWicket() {
//        return this.wicket;
//    }
//
//    public String getTeamName() {
//        return teamName;
//    }
//
//    public void setTeamName(String teamName) {
//        this.teamName = teamName;
//    }
//
//}

}