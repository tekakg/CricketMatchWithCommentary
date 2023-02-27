package com.cricketGamewithspring.demo.services;

import com.cricketGamewithspring.demo.Repo.MatchRepo;
import com.cricketGamewithspring.demo.model.Match;
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
