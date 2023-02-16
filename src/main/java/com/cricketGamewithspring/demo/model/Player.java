package com.cricketGamewithspring.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Document(collection="PlayerList")
public class Player {
    @Transient
    public static final String SEQUENCE_NAME = "players_sequence";
    @Id
    private int id;
    @Field("name")
    private String name;
    @Field("role")
    private String role;
    private int run;
    private int BallsFaced;
    private int wickets;
    private int BallsBowled;
    private boolean outOrNot;

    public void incrementrun(int run) {
        this.run += run;
    }
    public void incrementBallsFaced() {
        this.BallsFaced++;
    }
    public void incrementWickets(){
        this.wickets++;
    }
    public void incrementBallsBowled(){
        this.BallsBowled++;
    }
}

