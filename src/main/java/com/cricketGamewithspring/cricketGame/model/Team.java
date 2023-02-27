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

    public void incrementwicket() {
        this.wicket++;
    }

    public void incrementrun(int run) {
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
}