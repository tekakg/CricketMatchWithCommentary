package com.cricketGamewithspring.ControllerTest;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cricketGamewithspring.cricketGame.controller.MyController;
import com.cricketGamewithspring.cricketGame.model.*;
import com.cricketGamewithspring.cricketGame.services.CricketService;
import com.cricketGamewithspring.cricketGame.services.MatchDetailService;
import com.cricketGamewithspring.cricketGame.services.PlayerService;
import com.cricketGamewithspring.cricketGame.services.ScoreboardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@WebMvcTest(MyController.class)
@ContextConfiguration(classes= MyController.class)
class MyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CricketService cricketService;
    @MockBean
    private ScoreboardService scoreboardService;
    @MockBean
    private MatchDetailService matchService;
    @MockBean
    private PlayerService playerService;

    @Test
    void setMatch() throws Exception {
        Scoreboard scoreboard = new Scoreboard(10, 10);
        when(cricketService.createMatch(new MatchDetail())).thenReturn(scoreboard);
        String expectedResult = objectMapper.writeValueAsString(scoreboard);
        mockMvc.perform(MockMvcRequestBuilders.post("/match-info")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new MatchDetail())))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(expectedResult));

    }

    @Test
    void getScoreboard() throws Exception {
        Scoreboard scoreboard = new Scoreboard(10, 10);
        when(scoreboardService.getScoreboard(10)).thenReturn(Optional.of(scoreboard));
        String expectedResult=objectMapper.writeValueAsString(scoreboard);
        mockMvc.perform(MockMvcRequestBuilders.get("/scoreboard/{matchId}",10))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResult));


    }

    @Test
    void getMatch() throws Exception{
        Match match=new Match(2,20,"Tem1","Team2");
        when(matchService.getMatch(2)).thenReturn(Optional.of(match));
        String expectedResult=objectMapper.writeValueAsString(match);
        mockMvc.perform(MockMvcRequestBuilders.get("/match-history/{matchId}",2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResult));
    }

    @Test
    void getPlayerUsingName() throws Exception{
        Player player=new Player(2,"Player1","Batsman",20,10,2,20,false);
        when(playerService.getPlayerUsingName("Player1")).thenReturn(player);
        String expectedResult=objectMapper.writeValueAsString(player);
        mockMvc.perform(MockMvcRequestBuilders.get("/player-info-using-name/{playerName}","Player1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResult));
    }

    @Test
    void getPlayerUsingRole() throws Exception{
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player(1,"Player1","Bowler"));
        playerList.add(new Player(2,"Player2","Bowler"));
        when(playerService.getPlayerUsingRole("Bowler")).thenReturn(playerList);
        String expectedResult = objectMapper.writeValueAsString(playerList);
        mockMvc.perform(MockMvcRequestBuilders.get("/players-info-using-role/{playerRole}","Bowler"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResult));
    }

    @Test
    void getTeam1() throws Exception{
        Team team=new Team("Team1",11,150,8,10,4);
        when(scoreboardService.getTeam1(1)).thenReturn(team);
        String expectedResult=objectMapper.writeValueAsString(team);
        mockMvc.perform((MockMvcRequestBuilders.get("/team1/{matchId}",1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.content().json(expectedResult)));

    }
    @Test
    void getTeam2() throws Exception {
        Team team=new Team("Team2",11,112,8,11,4);
        when(scoreboardService.getTeam2(2)).thenReturn(team);
        String expectedResult=objectMapper.writeValueAsString(team);
        mockMvc.perform((MockMvcRequestBuilders.get("/team2/{matchId}",2)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.content().json(expectedResult)));
    }
    @Test
    void setPlayer() throws Exception{
        ResponseEntity<String>responseEntity=ResponseEntity.ok("Player is Successfully Added to the PlayerList");
        Player player=new Player(1,"Player1","Batsman");
        when(playerService.setPlayer(player)).thenReturn(responseEntity);
        String expectedResult=objectMapper.writeValueAsString(responseEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/set-player-info")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new MatchDetail())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        }
}