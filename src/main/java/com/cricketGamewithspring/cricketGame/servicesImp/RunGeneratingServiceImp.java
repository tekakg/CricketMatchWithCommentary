package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.services.RunGeneratingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
/**

 This class is responsible for generating random types of runs in a cricket match
 */
public class RunGeneratingServiceImp implements RunGeneratingService {

    /**
     * Generates a random type of run for a ball in a cricket match based on a certain probability distribution
     *
     * @return the type of run generated
     */
    public RunType generateRunType() {
        int val = (int) (Math.random() * 150); // generate a random number between 0 and 149
        if (val > 140) // 1% probability of getting a wicket
            return RunType.WICKET;
        else if (val > 130 && val < 140) // 6% probability of getting a six
            return RunType.SIX;
        else if (val == 121) // 0.67% probability of getting a five
            return RunType.FIVE;
        else if (val >= 122 && val <= 129) // 4% probability of getting a wide
            return RunType.WIDE;
        else if (val == 130) // 0.67% probability of getting a no ball
            return RunType.NOBALL;
        else if (val > 100 && val < 120) // 13.33% probability of getting a four
            return RunType.FOUR;
        else {
            int runlevel = (int) (Math.random() * 4); // generate a random number between 0 and 3
            if (runlevel == 0) // 25% probability of getting zero
                return RunType.ZERO;
            else if (runlevel == 1) // 25% probability of getting one
                return RunType.ONE;
            else if (runlevel == 2) // 25% probability of getting two
                return RunType.TWO;
            else // 25% probability of getting three
                return RunType.THREE;
        }
    }
}
