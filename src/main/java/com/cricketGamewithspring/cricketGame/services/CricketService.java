package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.MatchDetail;
import com.cricketGamewithspring.cricketGame.model.Scoreboard;

import java.util.Optional;

public interface CricketService {
     Optional<Scoreboard>createMatch(MatchDetail matchDetail);

}
