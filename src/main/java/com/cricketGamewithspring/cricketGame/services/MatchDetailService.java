package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.Match;

import java.util.Optional;

public interface MatchDetailService {
    public Optional<Match> getMatch(int parseInt);
}
