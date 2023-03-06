package com.cricketGamewithspring.servicesImpTest;

import com.cricketGamewithspring.cricketGame.Repo.MatchRepo;
import com.cricketGamewithspring.cricketGame.Repo.ScoreboardRepo;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.Player;
import com.cricketGamewithspring.cricketGame.model.Scoreboard;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.services.PlayMatchService;
import com.cricketGamewithspring.cricketGame.services.TossService;
import com.cricketGamewithspring.cricketGame.servicesImp.PlayMatchServiceImp;
import com.cricketGamewithspring.cricketGame.servicesImp.StartMatchServiceImp;

import com.cricketGamewithspring.cricketGame.servicesImp.TossServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = StartMatchServiceImp.class)
@ExtendWith(MockitoExtension.class)
public class StartMatchServiceImpTest {

    @InjectMocks
    StartMatchServiceImp startMatchServiceImp;

    @MockBean
    MatchRepo matchRepo;

    @MockBean
    ScoreboardRepo scoreboardRepo;

    @MockBean
    TossServiceImp tossServiceImp;

    @MockBean
    PlayMatchServiceImp playMatchServiceImp;
    @Test
    public void StartMatch() {

        Team team1 = new Team();
        Team team2 = new Team();
        team1.setTeamName("Team1");
        team2.setTeamName("Team2");
        team1.setTotalPlayers(2);
        team2.setTotalPlayers(2);
        List<Player> team1Players = new ArrayList<>();
        List<Player> team2Players = new ArrayList<>();
        Player player1 = new Player(1, "Player 1", "Batsman");
        Player player2 = new Player(2, "Player 2", "Bowler");
        Player player3 = new Player(3, "Player 3", "Batsman");
        Player player4 = new Player(4, "Player 4", "Bowler");
        team1Players.add(player1);
        team1Players.add(player2);
        team2Players.add(player3);
        team2Players.add(player4);
        team1.setListOfPlayers(team1Players);
        team2.setListOfPlayers(team2Players);
        when(tossServiceImp.getToss(team1,team2)).thenReturn("Team1");

        Match match=new Match();
        when(playMatchServiceImp.playMatch(team1, team2, match, "Team1")).thenReturn("Team 1 won");

        Scoreboard scoreboard=new Scoreboard();
        scoreboard = startMatchServiceImp.startMatch(team1, team2, match, scoreboard, matchRepo, scoreboardRepo);

        assertEquals("Team1", match.getTeam1Name());
        assertEquals("Team2", match.getTeam2Name());
        assertEquals("Team1"+" "+"has won the toss and elected to bat first",match.getTossResult());
        assertEquals("Team 1 won", match.getMatchResult());
        assertEquals(scoreboard.getMatchId(), match.getId());
        assertEquals(team1, scoreboard.getTeam1());
        assertEquals(team2, scoreboard.getTeam2());
        verify(matchRepo).save(match);
        verify(scoreboardRepo).save(scoreboard);
    }

}
