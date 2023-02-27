package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.model.Match;

public interface PlayMatchServiceInt {

    public String playMatch(Team team1, Team team2, Match match, String winningTeam);
}
