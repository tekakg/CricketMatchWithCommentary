package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.Repo.MatchRepo;
import com.cricketGamewithspring.cricketGame.Repo.ScoreboardRepo;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.Scoreboard;
import com.cricketGamewithspring.cricketGame.model.Team;

public interface StartMatchService {
    public Scoreboard startMatch(Team team1, Team team2, Match match, Scoreboard scoreboard, MatchRepo matchRepo,
                                 ScoreboardRepo scoreboardRepo);
}
