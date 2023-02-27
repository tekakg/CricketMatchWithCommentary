package com.cricketGamewithspring.cricketGame.Repo;

import com.cricketGamewithspring.cricketGame.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PlayerRepo")
public interface PlayerRepo extends MongoRepository<Player, Integer> {
    Player findByName(String name);

    Player findById(int id);

    List<Player> findAllByRole(String role);

}
