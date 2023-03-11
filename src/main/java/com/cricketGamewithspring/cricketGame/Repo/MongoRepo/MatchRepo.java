package com.cricketGamewithspring.cricketGame.Repo.MongoRepo;

import com.cricketGamewithspring.cricketGame.model.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("MatchRepo")
public interface MatchRepo extends MongoRepository<Match, Integer> {

}