package com.cricketGamewithspring.cricketGame.Repo.MongoRepo;

import com.cricketGamewithspring.cricketGame.model.Scoreboard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreboardRepo extends MongoRepository<Scoreboard, Integer> {
    Optional<Scoreboard> findByMatchId(int matchid);
    //In this repo I will store scoreboard
}
