package com.cricketGamewithspring.cricketGame.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Index;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Index(name = "playerId")
    private int id;
    @Index(name = "playerName")
    private String name;
    @Index(name = "playerRole")
    private String role;
    private int run;
    private int BallsFaced;
    private int wickets;
    private int BallsBowled;
    private boolean outOrNot;

    public void incrementRun(int run) {
        this.run += run;
    }

    public void incrementBallsFaced() {
        this.BallsFaced++;
    }

    public void incrementWickets() {
        this.wickets++;
    }

    public void incrementBallsBowled() {
        this.BallsBowled++;
    }
}

