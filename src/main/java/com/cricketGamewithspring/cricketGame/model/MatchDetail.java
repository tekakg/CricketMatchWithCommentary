package com.cricketGamewithspring.cricketGame.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.util.List;

//This is just for taking data from postman.

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
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
    private List<Integer> Team1Players;
    private List<Integer> Team2Players;
}