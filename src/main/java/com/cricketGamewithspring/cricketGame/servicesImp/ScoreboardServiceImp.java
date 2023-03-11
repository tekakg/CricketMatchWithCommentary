package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.Repo.MongoRepo.ScoreboardRepo;
import com.cricketGamewithspring.cricketGame.exceptionHandler.ResourceNotFound;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.model.Scoreboard;
import com.cricketGamewithspring.cricketGame.services.ScoreboardService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class ScoreboardServiceImp implements ScoreboardService {
    @Autowired
    private ScoreboardRepo scoreboardRepo;

    public Optional<Scoreboard> getScoreboard(int matchId) {

        Optional<Scoreboard> scoreboard = scoreboardRepo.findByMatchId(matchId);
        return scoreboard;
    }

    public Team getTeam1(int matchId) {
        Optional<Scoreboard> scoreboard = scoreboardRepo.findByMatchId(matchId);
        if (scoreboard.stream().findAny().isEmpty())
            throw new ResourceNotFound("No Match corresponding to this matchId");
        return scoreboard.get().getTeam1();
    }

    public Team getTeam2(int matchId) {
        Optional<Scoreboard> scoreboard = scoreboardRepo.findByMatchId(matchId);
        if (scoreboard.stream().count() == 0)
            throw new ResourceNotFound("No Match corresponding to this matchId");
        return scoreboard.get().getTeam2();
    }
}
