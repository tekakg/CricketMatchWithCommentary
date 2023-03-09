package com.cricketGamewithspring.cricketGame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Team {
    private String teamName;
    private int totalPlayers;
    public List<Player> listOfPlayers = new ArrayList<>();

    private int score;
    private int wicket;
    private int overNumber;
    private int ballNumber;

    public void incrementWicket() {
        this.wicket++;
    }

    public void incrementRun(int run) {
        this.score += run;
    }

    public Player getNewBatsman(Player nonStriker, Team BattingTeam) {
        Player nwPlayer = null;
        for (Player player : BattingTeam.getListOfPlayers()) {
            if (player.isOutOrNot() == false && player != nonStriker) {
                {
                    nwPlayer = player;
                    break;
                }
            }
        }
        return nwPlayer;
    }

    public Team(String teamName, int totalPlayers, int score, int wicket, int overNumber, int ballNumber) {
        this.teamName = teamName;
        this.totalPlayers = totalPlayers;
        this.score = score;
        this.wicket = wicket;
        this.overNumber = overNumber;
        this.ballNumber = ballNumber;
    }
}