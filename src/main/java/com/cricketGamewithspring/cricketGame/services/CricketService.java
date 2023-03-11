package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.MatchDetail;
import com.cricketGamewithspring.cricketGame.model.Scoreboard;

public interface CricketService {
     Scoreboard createMatch(MatchDetail matchDetail);

}
