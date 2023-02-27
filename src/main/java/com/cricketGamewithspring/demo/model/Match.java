package com.cricketGamewithspring.demo.model;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Document(collection = "Match")
public class Match {
    @Transient
    public static final String SEQUENCE_NAME = "match_sequence";
    @Id
    private int id;
    private int totalOvers;
    private String team1Name;
    private String team2Name;
    private String tossResult;
    private String MatchResult;
    private ArrayList<Ball> commentary;

    public void setCommentary(ArrayList<Ball> commentary) {
        this.commentary = commentary;
    }

    public ArrayList<Ball> getCommentary() {
        return commentary;
    }
}
