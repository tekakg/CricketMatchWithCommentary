package com.cricketGamewithspring.servicesImpTest;

import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.servicesImp.PlayerServiceImp;
import com.cricketGamewithspring.cricketGame.servicesImp.TossServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = TossServiceImp.class)
@ExtendWith(MockitoExtension.class)
public class TossServiceImpTest {
    @InjectMocks
    TossServiceImp tossServiceImp;

    @Test
    void testGetToss() throws Exception{
        // Arrange
        Team team1 = new Team("Team1",0,0,0,0,0);
        Team team2 = new Team("Team2",0,0,0,0,0);

        // Act
        String result = tossServiceImp.getToss(team1, team2);

        // Assert
        assertTrue(result.equals(team1.getTeamName()) || result.equals(team2.getTeamName()));
    }
}
