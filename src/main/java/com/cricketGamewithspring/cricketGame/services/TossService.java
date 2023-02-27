package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.serviceInt.TossServiceInt;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class TossService implements TossServiceInt {
    private int toss;
    public String getToss(Team team1, Team team2) {
        toss = (int) (Math.random() * 2);
        if (toss == 0) {
            return team1.getTeamName();
        } else
            return team2.getTeamName();
    }
}
