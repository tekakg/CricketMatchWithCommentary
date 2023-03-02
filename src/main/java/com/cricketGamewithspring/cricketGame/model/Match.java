package com.cricketGamewithspring.cricketGame.model;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Document(collection = "Match")
@CompoundIndex(name="matchId",def = "{'id': 1}")
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

    public Match(int id, int totalOvers, String team1Name, String team2Name)
    {
        this.id=id;
        this.totalOvers=totalOvers;
        this.team1Name=team1Name;
        this.team2Name=team2Name;
    }

}
