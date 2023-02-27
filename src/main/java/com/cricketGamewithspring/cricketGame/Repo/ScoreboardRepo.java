package com.cricketGamewithspring.cricketGame.Repo;

import com.cricketGamewithspring.cricketGame.model.Scoreboard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ScoreboardRepo extends MongoRepository<Scoreboard, Integer> {
    Optional<Scoreboard> findByMatchId(int matchid);
    //In this repo I will store scoreboard
}
