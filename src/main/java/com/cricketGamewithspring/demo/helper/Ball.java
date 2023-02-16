package com.cricketGamewithspring.demo.helper;

import com.cricketGamewithspring.demo.model.Player;
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
    private Player bowler;
    private Player batsman;
    private String run;

    private int Inning;

    public Ball(int overNumber,int ballNumber, Player bowler , Player batsman ,  int run,int Inning)
    {
        this.overNumber=overNumber;
        this.ballNumber=ballNumber;
        this.bowler=bowler;
        this.batsman=batsman;
        if(run>6)
            this.run="W";
        else
            this.run=Integer.toString(run);
        this.Inning=Inning;
    }

    public int getBallNumber() {
        return ballNumber;
    }

    public int getOverNumber() {
        return overNumber;
    }

    public Player getBatsman() {
        return batsman;
    }

    public Player getBowler() {
        return bowler;
    }

    public String getRun() {
        return run;
    }
}

