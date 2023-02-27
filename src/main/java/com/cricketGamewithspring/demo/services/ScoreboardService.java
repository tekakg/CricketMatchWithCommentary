package com.cricketGamewithspring.demo.services;

import com.cricketGamewithspring.demo.Repo.ScoreboardRepo;
import com.cricketGamewithspring.demo.model.Team;
import com.cricketGamewithspring.demo.model.Scoreboard;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class ScoreboardService implements ScoreboardServiceInt {

    @Autowired
    ScoreboardRepo scoreboardRepo;
    public Optional<Scoreboard> getScoreboard (int matchId)
    {

        Optional<Scoreboard> scoreboard=scoreboardRepo.findByMatchId(matchId);
        return scoreboard;
    }
    public Team getTeam1(int matchId)
    {
        Team team=scoreboardRepo.findByMatchId(matchId).get().getTeam1();
        return team;
    }
    public Team getTeam2(int matchId)
    {
        Team team=scoreboardRepo.findByMatchId(matchId).get().getTeam2();
        return team;
    }
}
