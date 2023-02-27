package com.cricketGamewithspring.demo.services;

import com.cricketGamewithspring.demo.helper.Team;
import com.cricketGamewithspring.demo.model.Match;

public interface PlayMatchServiceInt {

    public String playMatch(Team team1, Team team2, Match match, String winningTeam);
}
