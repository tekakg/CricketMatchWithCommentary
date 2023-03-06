package com.cricketGamewithspring.servicesImpTest;


import java.util.Optional;
import com.cricketGamewithspring.cricketGame.Repo.ScoreboardRepo;
import com.cricketGamewithspring.cricketGame.model.Scoreboard;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.servicesImp.ScoreboardServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ScoreboardServiceImp.class)
@ExtendWith(MockitoExtension.class)
public class ScoreboardServiceImpTest {
    @InjectMocks
    ScoreboardServiceImp scoreboardServiceImp;

    @MockBean
    ScoreboardRepo scoreboardRepo;


    @Test
    void getScoreboard() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        when(scoreboardRepo.findByMatchId(anyInt())).thenReturn(Optional.of(scoreboard));
        Optional<Scoreboard> nScoreboard=scoreboardServiceImp.getScoreboard(11);
        Assertions.assertEquals(scoreboard, nScoreboard.get());
    }

    @Test
    void getTeam1() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        Team team = new Team();
        team.setScore(100);
        scoreboard.setTeam1(team);
        when(scoreboardRepo.findByMatchId(anyInt())).thenReturn(Optional.of(scoreboard));
        Team nteam = scoreboardServiceImp.getTeam1(11);
        Assertions.assertEquals(nteam.getScore(), 100);

    }

    @Test
    void getTeam2() throws Exception {
        Scoreboard scoreboard=new Scoreboard();
        Team team=new Team();
        team.setScore(200);
        scoreboard.setTeam2(team);
        when(scoreboardRepo.findByMatchId(anyInt())).thenReturn(Optional.of(scoreboard));
        Team nteam=scoreboardServiceImp.getTeam2(11);
        Assertions.assertEquals(nteam.getScore(),200);
    }
}
