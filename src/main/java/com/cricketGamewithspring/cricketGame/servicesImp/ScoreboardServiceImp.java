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
/**

 Implementation of ScoreboardService interface
 */
public class ScoreboardServiceImp implements ScoreboardService {

    /**

     Scoreboard Repository to interact with Scoreboard data
     */
    @Autowired
    private ScoreboardRepo scoreboardRepo;
    /**

     Returns the scoreboard of the match with the given matchId

     @param matchId the id of the match whose scoreboard is requested

     @return an Optional containing the scoreboard if it exists, otherwise empty Optional
     */
    public Optional<Scoreboard> getScoreboard(int matchId) {

        Optional<Scoreboard> scoreboard = scoreboardRepo.findByMatchId(matchId);
        return scoreboard;
    }

    /**

     Returns the Team1 object of the match with the given matchId
     @param matchId the id of the match whose Team1 is requested
     @return the Team1 object of the match
     @throws ResourceNotFound if no match with the given matchId exists in the database
     */
    public Team getTeam1(int matchId) {
        Optional<Scoreboard> scoreboard = scoreboardRepo.findByMatchId(matchId);
        if (scoreboard.stream().findAny().isEmpty())
            throw new ResourceNotFound("No Match corresponding to this matchId");
        return scoreboard.get().getTeam1();
    }
    /**

     Returns the Team2 object of the match with the given matchId
     @param matchId the id of the match whose Team2 is requested
     @return the Team2 object of the match
     @throws ResourceNotFound if no match with the given matchId exists in the database
     */
    public Team getTeam2(int matchId) {
        Optional<Scoreboard> scoreboard = scoreboardRepo.findByMatchId(matchId);
        if (scoreboard.stream().count() == 0)
            throw new ResourceNotFound("No Match corresponding to this matchId");
        return scoreboard.get().getTeam2();
    }
}


