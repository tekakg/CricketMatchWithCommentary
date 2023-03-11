package com.cricketGamewithspring.cricketGame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Ball {
    private int overNumber;
    private int ballNumber;
    private String bowler;
    private String batsman;
    private String run;
    public Ball(int overNumber, int ballNumber, String bowler, String batsman, int run) {
        this.overNumber = overNumber;
        this.ballNumber = ballNumber;
        this.bowler = bowler;
        this.batsman = batsman;
        if (run > 6)
            this.run = "W";
        else
            this.run = Integer.toString(run);
    }

    public int getBallNumber() {
        return ballNumber;
    }

    public int getOverNumber() {
        return overNumber;
    }

    public String getBatsman() {
        return batsman;
    }

    public String getBowler() {
        return bowler;
    }

    public String getRun() {
        return run;
    }
}

