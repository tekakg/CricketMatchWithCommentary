package com.cricketGamewithspring.cricketGame.serviceInt;

import com.cricketGamewithspring.cricketGame.model.Ball;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.Team;

import java.util.List;

public interface SecondInningServiceInt {

    public List<Team> secondInnings(Team team1, Team team2, Match match, List<Ball> ballHistory);
}
