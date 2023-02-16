package com.cricketGamewithspring.demo.Repo;

import com.cricketGamewithspring.demo.model.MatchDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("MatchDetailRepo")
public interface MatchDetailRepo extends MongoRepository<MatchDetail,Integer> {
}
