package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.services.RunGeneratingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class RunGeneratingServiceImp implements RunGeneratingService {
    public int generateRun() {
        int val = (int) (Math.random() * 150);
        if (val > 140)
            return 7;
        else if (val > 122 && val < 140)
            return 6;
        else if (val > 120 && val < 122)
            return 5;
        else if (val > 100 && val < 120)
            return 4;
        else {
            int runval = (int) (Math.random() * 4);
            return runval;
        }
    }
}
