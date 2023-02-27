package com.cricketGamewithspring.cricketGame.serviceInt;

import com.cricketGamewithspring.cricketGame.model.Match;

import java.util.Optional;

public interface MatchDetailServiceInt {
    Optional<Match> getMatch(int parseInt);
}
