package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.model.Scoreboard;

import java.util.Optional;

public interface ScoreboardService {
    Optional<Scoreboard> getScoreboard(int matchId);

    Team getTeam1(int matchId);

    Team getTeam2(int matchId);

}
