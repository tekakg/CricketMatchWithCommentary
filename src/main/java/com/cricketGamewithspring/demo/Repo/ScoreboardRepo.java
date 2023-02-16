package com.cricketGamewithspring.demo.Repo;

import com.cricketGamewithspring.demo.model.Scoreboard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoreboardRepo extends MongoRepository<Scoreboard,Integer> {
    //In this repo I will store scoreboard
}
