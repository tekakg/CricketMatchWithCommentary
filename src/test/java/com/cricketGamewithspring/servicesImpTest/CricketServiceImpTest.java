package com.cricketGamewithspring.servicesImpTest;

import com.cricketGamewithspring.cricketGame.Repo.MatchDetailRepo;
import com.cricketGamewithspring.cricketGame.Repo.MatchRepo;
import com.cricketGamewithspring.cricketGame.Repo.PlayerRepo;
import com.cricketGamewithspring.cricketGame.Repo.ScoreboardRepo;
import com.cricketGamewithspring.cricketGame.model.*;
import com.cricketGamewithspring.cricketGame.servicesImp.CricketServiceImp;
import com.cricketGamewithspring.cricketGame.servicesImp.MatchDetailsServiceImp;
import com.cricketGamewithspring.cricketGame.servicesImp.SequenceGeneratorService;
import com.cricketGamewithspring.cricketGame.servicesImp.StartMatchServiceImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CricketServiceImp.class)
@ExtendWith(MockitoExtension.class)
public class CricketServiceImpTest {

    @InjectMocks
    private CricketServiceImp cricketServiceImp;
    @MockBean
    private MatchRepo matchRepo;
    @MockBean
    private PlayerRepo playerRepo;
    @MockBean
    private MatchDetailRepo matchDetailRepo;
    @MockBean
    private ScoreboardRepo scoreboardRepo;
    @MockBean
    private SequenceGeneratorService sequenceGeneratorService;
    @MockBean
    private Match match;
    @MockBean
    private Scoreboard scoreboard;
    @MockBean
    private StartMatchServiceImp startMatchServiceImp;


    @Test
    public void createMatch() {
        MatchDetail matchDetail = new MatchDetail();
        matchDetail.setId(1);
        matchDetail.setOvers(10);
        matchDetail.setPlayerCount(5);
        matchDetail.setTeam1Name("Team 1");
        matchDetail.setTeam2Name("Team 2");
        List<Integer> team1Players = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> team2Players = Arrays.asList(6, 7, 8, 9, 10);
        matchDetail.setTeam1Players(team1Players);
        matchDetail.setTeam2Players(team2Players);

        List<Player> players = new ArrayList<Player>();
        for (int i = 1; i <= 10; i++) {
            Player player = new Player();
            player.setId(i);
            player.setName("Player " + i);
            players.add(player);
            when(playerRepo.findById(i)).thenReturn(Optional.of(player));
            when(playerRepo.countById(i)).thenReturn(1);
        }
        Team team1=new Team();
        Team team2=new Team();
        when(sequenceGeneratorService.generateSequence("match_sequence")).thenReturn(1);
        when(sequenceGeneratorService.generateSequence("scoreboard_sequence")).thenReturn(2);
        Scoreboard nScoreboard=new Scoreboard();
        nScoreboard.setScoreBoardId(1);
        Match nmatch=new Match();
        when(startMatchServiceImp.startMatch(team1,team2,match,scoreboard,matchRepo,scoreboardRepo)).thenReturn(nScoreboard);
        Scoreboard output=cricketServiceImp.createMatch(matchDetail);

        verify(playerRepo).countById(1);
        verify(playerRepo).countById(2);
        verify(playerRepo).countById(3);
        verify(playerRepo).countById(4);
        verify(playerRepo).countById(5);
        verify(playerRepo).countById(6);
        verify(playerRepo).countById(7);
        verify(playerRepo).countById(8);
        verify(playerRepo).countById(9);
        verify(playerRepo).countById(10);

        verify(playerRepo).findById(1);
        verify(playerRepo).findById(2);
        verify(playerRepo).findById(3);
        verify(playerRepo).findById(4);
        verify(playerRepo).findById(5);
        verify(playerRepo).findById(6);
        verify(playerRepo).findById(7);
        verify(playerRepo).findById(8);
        verify(playerRepo).findById(9);
        verify(playerRepo).findById(10);

        verify(matchDetailRepo).save(matchDetail);


    }
}
