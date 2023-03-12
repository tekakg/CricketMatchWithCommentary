package com.cricketGamewithspring.cricketGame.model;

import com.cricketGamewithspring.cricketGame.servicesImp.RunType;
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
    private RunType run;

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

    public RunType getRun() {
        return run;
    }

    public Ball(int overNumber, int ballNumber)
    {
        this.ballNumber=ballNumber;
        this.overNumber=overNumber;
    }
}

