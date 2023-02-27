package com.cricketGamewithspring.demo.services;

import com.cricketGamewithspring.demo.model.Match;

import java.util.Optional;

public interface MatchDetailServiceInt {
    Optional<Match> getMatch(int parseInt);
}
