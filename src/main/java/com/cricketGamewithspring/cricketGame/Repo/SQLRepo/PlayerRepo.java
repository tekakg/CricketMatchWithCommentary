package com.cricketGamewithspring.cricketGame.Repo.SQLRepo;

import com.cricketGamewithspring.cricketGame.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {
    @Query(value = "SELECT * FROM Player p WHERE p.name = :name", nativeQuery = true)
    Player findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM Player p WHERE p.role = :role", nativeQuery = true)
    List<Player> findAllByRole(@Param("role") String role);

    int countById(int id);

}
