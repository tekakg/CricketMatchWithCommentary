package com.cricketGamewithspring.cricketGame.model;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Document(collection = "Scoreboard")
@CompoundIndex(name="matchId",def = "{'matchId': 1}")
public class Scoreboard {
    @Transient
    public static final String SEQUENCE_NAME = "scoreboard_sequence";
    @Id
    private int scoreBoardId;
    private int matchId;
    private Team team1;

    private Team team2;

}
