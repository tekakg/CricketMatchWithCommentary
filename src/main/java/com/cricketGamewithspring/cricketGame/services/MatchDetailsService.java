package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.Repo.MatchRepo;
import com.cricketGamewithspring.cricketGame.model.Match;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class MatchDetailsService implements  MatchDetailServiceInt{

    @Autowired
    MatchRepo matchRepo;
    public Optional<Match> getMatch (int matchId)
    {
        Optional<Match>match=matchRepo.findById(matchId);
        return match;
    }

}
