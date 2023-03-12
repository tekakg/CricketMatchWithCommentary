package com.cricketGamewithspring.servicesImpTest;

import com.cricketGamewithspring.cricketGame.Repo.MongoRepo.MatchDetailRepo;
import com.cricketGamewithspring.cricketGame.Repo.MongoRepo.MatchRepo;
import com.cricketGamewithspring.cricketGame.Repo.SQLRepo.PlayerRepo;
import com.cricketGamewithspring.cricketGame.Repo.MongoRepo.ScoreboardRepo;
import com.cricketGamewithspring.cricketGame.model.*;
import com.cricketGamewithspring.cricketGame.servicesImp.CricketServiceImp;
import com.cricketGamewithspring.cricketGame.servicesImp.SequenceGeneratorService;
import com.cricketGamewithspring.cricketGame.servicesImp.StartMatchServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
    public void createMatch() throws Exception{
        MatchDetail matchDetail = new MatchDetail();
        matchDetail.setOvers(10);
        matchDetail.setPlayerCount(2);
        matchDetail.setTeam1Name("Team 1");
        matchDetail.setTeam2Name("Team 2");
        List<Integer> team1Players = Arrays.asList(1, 2);
        List<Integer> team2Players = Arrays.asList(3,4);
        matchDetail.setTeam1Players(team1Players);
        matchDetail.setTeam2Players(team2Players);
        matchDetailRepo.save(matchDetail);
        verify(matchDetailRepo).save(matchDetail);

        List<Player> players = new ArrayList<Player>();
        players.add(new Player(1,"arpit","batsman"));
        players.add(new Player(2,"aditya","bowler"));
        players.add(new Player(3,"aman","batsman"));
        players.add(new Player(4,"apoorv","bowler"));
        Team team1 = new Team("Australia",5,0,0,0,0);
        Team team2 = new Team("India",5,0,0,0,0);
        when(sequenceGeneratorService.generateSequence("match_sequence")).thenReturn(1);
        when(sequenceGeneratorService.generateSequence("scoreboard_sequence")).thenReturn(2);
        when(playerRepo.findAll()).thenReturn(players);
        Scoreboard newScoreboard = new Scoreboard();
        newScoreboard.setScoreBoardId(1);
        Match newmatch = new Match();
        when(startMatchServiceImp.startMatch(any(), any(), any(), any(), any(), any())).thenReturn(newScoreboard);
        when(scoreboardRepo.findByMatchId(anyInt())).thenReturn(Optional.of(newScoreboard));
        Optional<Scoreboard> output = cricketServiceImp.createMatch(matchDetail);
        Assertions.assertEquals(newScoreboard,output.get());

    }

}
