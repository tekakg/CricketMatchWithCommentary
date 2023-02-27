package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.Ball;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.Team;

import java.util.List;

public interface FirstInningServiceInt {
    public List<Team> firstInnings(Team team1, Team team2, Match match, String tossWinningTeam, List<Ball>ballHistory);
}
