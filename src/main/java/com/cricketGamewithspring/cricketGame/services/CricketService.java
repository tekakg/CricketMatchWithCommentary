package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.MatchDetail;
import com.cricketGamewithspring.cricketGame.model.Scoreboard;

public interface CricketService {

    public Scoreboard createMatch(MatchDetail matchDetail);

}
