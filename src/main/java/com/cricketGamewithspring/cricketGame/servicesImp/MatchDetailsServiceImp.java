package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.Repo.MatchRepo;
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
public class MatchDetailsServiceImp implements MatchDetailService {

    @Autowired
    MatchRepo matchRepo;

    public Optional<Match> getMatch(int matchId) {
        Optional<Match> match = matchRepo.findById(matchId);
        return match;
    }

}
