package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.services.TossService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
/**

 This class implements the TossService interface.

 It generates a random number and assigns the toss to one of the two teams based on the outcome of the random number.
 */
public class TossServiceImp implements TossService {

    private int toss;

    /**

     This method gets the toss result by generating a random number and assigning the toss to one of the two teams.
     @param team1 the first team in the match
     @param team2 the second team in the match
     @return the name of the team that won the toss
     */
    public String getToss(Team team1, Team team2) {
        toss = (int) (Math.random() * 2);
        if (toss == 0) {
            return team1.getTeamName();
        } else {
            return team2.getTeamName();
        }
    }
}
