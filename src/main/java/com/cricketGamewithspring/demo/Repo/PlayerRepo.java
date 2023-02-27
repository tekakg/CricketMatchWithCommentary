package com.cricketGamewithspring.demo.Repo;

import com.cricketGamewithspring.demo.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("PlayerRepo")
public interface PlayerRepo extends MongoRepository<Player, Integer> {
    Player findByName(String name);

    Player findById(int id);

}
