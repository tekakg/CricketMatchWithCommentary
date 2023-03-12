package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.Repo.MongoRepo.MatchRepo;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.services.MatchDetailService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
/**

 Implementation of MatchDetailService interface.

 Provides functionality to retrieve match details from the MatchRepo.
 */
public class MatchDetailsServiceImp implements MatchDetailService {

    // Dependency injection of MatchRepo
    @Autowired
    private MatchRepo matchRepo;

    /**

     Retrieves the match details from the MatchRepo based on the given match ID.
     @param matchId ID of the match to retrieve details for.
     @return Optional containing the Match object if found, otherwise an empty Optional.
     */
    public Optional<Match> getMatch(int matchId) {
        Optional<Match> match = matchRepo.findById(matchId);
        return match;
    }
}
