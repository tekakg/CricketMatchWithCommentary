package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.services.RunGeneratingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class RunGeneratingServiceImp implements RunGeneratingService {

    public RunType generateRunType() {
        int val = (int) (Math.random() * 150);
        if (val > 140)
            return RunType.WICKET;
        else if (val > 130 && val < 140)
            return RunType.SIX;
        else if (val == 121)
            return RunType.FIVE;
        else if (val >=122 && val<=129)
            return RunType.WIDE;
        else if (val==130)
            return RunType.NOBALL;
        else if (val > 100 && val < 120)
            return RunType.FOUR;
        else {
            int runlevel = (int) (Math.random() * 4);

            if (runlevel == 0)
                return RunType.ZERO;
            else if (runlevel == 1)
                return RunType.ONE;
            else if (runlevel == 2)
                return RunType.TWO;
            else
                return RunType.THREE;
        }
    }
}
