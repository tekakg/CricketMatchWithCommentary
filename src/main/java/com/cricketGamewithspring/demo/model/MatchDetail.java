package com.cricketGamewithspring.demo.model;

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
    private List<Integer> team1Players;
    private List<Integer> team2Players;
//
//    public void setTeam1Players(int[] team1Players) {
//        Team1Players = team1Players;
//    }
//
//    public int[] getTeam1Players() {
//        return Team1Players;
//    }
//
//    public void setTeam2Players(int[] team2Players)
//    {
//        Team2Players=team2Players;
//    }
//
//    public int[] getTeam2Players()
//    {
//        return Team2Players;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public int getOvers() {
//        return overs;
//    }
//
//    public int getPlayerCount() {
//        return playerCount;
//    }
//
//    public void setOvers(int overs) {
//        this.overs = overs;
//    }
//
//    public String getTeam1Name() {
//        return team1Name;
//    }
//
//    public String getTeam2Name() {
//        return team2Name;
//    }
//
//    public void setPlayerCount(int playerCount) {
//        this.playerCount = playerCount;
//    }
//
//    public void setTeam1Name(String team1Name) {
//        this.team1Name = team1Name;
//    }
//
//    public void setTeam2Name(String team2Name) {
//        this.team2Name = team2Name;
//    }
//}
}
