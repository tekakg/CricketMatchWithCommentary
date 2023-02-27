package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.model.Scoreboard;

import java.util.Optional;

public interface ScoreboardServiceInt {
    public Optional<Scoreboard> getScoreboard (int matchId);
    public Team getTeam1(int matchId);
    public Team getTeam2(int matchId);

}
