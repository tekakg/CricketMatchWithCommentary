package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.Match;

import java.util.Optional;

public interface MatchDetailService {
    Optional<Match> getMatch(int parseInt);
}
