package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.services.TossService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class TossServiceImp implements TossService {
    private int toss;

    public String getToss(Team team1, Team team2) {
        toss = (int) (Math.random() * 2);
        if (toss == 0) {
            return team1.getTeamName();
        } else
            return team2.getTeamName();
    }
}
