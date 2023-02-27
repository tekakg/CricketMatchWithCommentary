package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.Match;

import java.util.Optional;

public interface MatchDetailServiceInt {
    Optional<Match> getMatch(int parseInt);
}
