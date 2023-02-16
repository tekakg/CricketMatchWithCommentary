package com.cricketGamewithspring.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//This is just for taking data from postman.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "MatchDetails")
public class MatchDetail {

    @Transient
    public static final String SEQUENCE_NAME = "matchDetail_sequence";
    @Id
    private int id;
    private int overs;
    private int playerCount;
    @Field("Team1Name")
    private String team1Name;
    @Field("Team2Name")
    private String team2Name;
    private int[] playerTeam1id;
    private int[] playerTeam2id;
}
