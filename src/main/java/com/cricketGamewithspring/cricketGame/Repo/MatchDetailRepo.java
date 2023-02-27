package com.cricketGamewithspring.cricketGame.Repo;

import com.cricketGamewithspring.cricketGame.model.MatchDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("MatchDetailRepo")
public interface MatchDetailRepo extends MongoRepository<MatchDetail, Integer> {
}
