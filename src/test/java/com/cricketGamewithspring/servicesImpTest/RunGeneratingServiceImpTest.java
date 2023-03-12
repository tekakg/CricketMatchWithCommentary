package com.cricketGamewithspring.servicesImpTest;

import com.cricketGamewithspring.cricketGame.servicesImp.RunGeneratingServiceImp;
import com.cricketGamewithspring.cricketGame.servicesImp.RunType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = RunGeneratingServiceImp.class)
@ExtendWith(MockitoExtension.class)
public class RunGeneratingServiceImpTest {

    @InjectMocks
    private RunGeneratingServiceImp runGeneratingServiceImp;

    @Test
    void randomFunction() throws Exception {
        // Act
        RunType runType = runGeneratingServiceImp.generateRunType();
        int result=runType.getRun();
        Assertions.assertNotNull(runType);
        if(result==2)
        {
            Assertions.assertEquals(runType.toString(),"2");
        }
        if(result==3)
        {
            Assertions.assertEquals(runType.toString(),"3");
        }
        if(result==4)
        {
            Assertions.assertEquals(runType.toString(),"4");
        }
        if(result==5)
        {
            Assertions.assertEquals(runType.toString(),"5");
        }
        if(result==6)
        {
            Assertions.assertEquals(runType.toString(),"6");
        }

        Assertions.assertTrue(result >= 0 && result <= 7);
    }

}
