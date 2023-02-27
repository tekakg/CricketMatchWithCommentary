package com.cricketGamewithspring.demo.services;

import com.cricketGamewithspring.demo.model.Team;
import com.cricketGamewithspring.demo.model.Scoreboard;

import java.util.Optional;

public interface ScoreboardServiceInt {
    public Optional<Scoreboard> getScoreboard (int matchId);
    public Team getTeam1(int matchId);

    public Team getTeam2(int matchId);

}
