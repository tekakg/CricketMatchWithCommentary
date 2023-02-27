package com.cricketGamewithspring.demo.Repo;

import com.cricketGamewithspring.demo.model.Scoreboard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ScoreboardRepo extends MongoRepository<Scoreboard, Integer> {
    Optional<Scoreboard> findByMatchId(int matchid);
    //In this repo I will store scoreboard
}
