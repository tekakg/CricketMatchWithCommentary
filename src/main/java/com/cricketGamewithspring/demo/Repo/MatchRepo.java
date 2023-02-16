package com.cricketGamewithspring.demo.Repo;

import com.cricketGamewithspring.demo.model.Match;
import com.cricketGamewithspring.demo.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MatchRepo")
public interface MatchRepo extends MongoRepository<Match, Integer> {

}