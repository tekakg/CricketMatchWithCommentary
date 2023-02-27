package com.cricketGamewithspring.cricketGame.serviceInt;

import com.cricketGamewithspring.cricketGame.model.MatchDetail;
import com.cricketGamewithspring.cricketGame.model.Scoreboard;

public interface CricketServiceInt {

    public Scoreboard createMatch(MatchDetail matchDetail);

}
