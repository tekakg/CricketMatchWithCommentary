package com.cricketGamewithspring.demo.services;

import com.cricketGamewithspring.demo.Repo.MatchRepo;
import com.cricketGamewithspring.demo.Repo.ScoreboardRepo;
import com.cricketGamewithspring.demo.model.Match;
import com.cricketGamewithspring.demo.model.Scoreboard;
import com.cricketGamewithspring.demo.model.Team;

public interface StartMatchServiceInt {
    public Scoreboard startMatch(Team team1, Team team2, Match match, Scoreboard scoreboard, MatchRepo matchRepo,
                                 ScoreboardRepo scoreboardRepo);
}
