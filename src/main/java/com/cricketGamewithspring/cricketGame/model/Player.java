package com.cricketGamewithspring.cricketGame.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Index;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "player")
@Document(indexName = "cricketer")
public class Player {
    @Id
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
    private boolean pout;

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

    public Player(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
    public Player(Player player)
    {
        this.id= player.getId();
        this.name=player.getName();
        this.role=player.getRole();
        this.run=player.getRun();
        this.BallsFaced= player.getBallsFaced();
        this.BallsBowled= player.getBallsBowled();
        this.wickets=player.getWickets();
    }

}

