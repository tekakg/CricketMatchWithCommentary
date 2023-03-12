package com.cricketGamewithspring.servicesImpTest;

import com.cricketGamewithspring.cricketGame.Repo.ESRepo.ElasticRepo;
import com.cricketGamewithspring.cricketGame.Repo.SQLRepo.PlayerRepo;
import com.cricketGamewithspring.cricketGame.model.Player;
import com.cricketGamewithspring.cricketGame.servicesImp.PlayerServiceImp;
import com.cricketGamewithspring.cricketGame.servicesImp.SequenceGeneratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = PlayerServiceImp.class)
@ExtendWith(MockitoExtension.class)
class PlayerServiceImpTest {

    @MockBean
    private PlayerRepo playerRepo;

    @InjectMocks
    private PlayerServiceImp playerServiceImp;

    @MockBean
    private SequenceGeneratorService sequenceGeneratorService;

    @MockBean
    private ElasticRepo elasticRepo;

    @Test
    void getPlayerUsingRole() throws Exception {
        List<Player> listPlayers = new ArrayList<>();
        Player nplayer = new Player();
        listPlayers.add(nplayer);
        when(playerRepo.findAllByRole(anyString())).thenReturn(listPlayers);
        List<Player> expectedPlayerList = playerServiceImp.getPlayerUsingRole("Batsman");
        Assertions.assertEquals(expectedPlayerList, listPlayers);
    }

    @Test
    void getPlayerUsingName() throws Exception {
        Player nplayer = new Player();
        when(playerRepo.findByName(anyString())).thenReturn(nplayer);
        Player expectedPlayer = playerServiceImp.getPlayerUsingName("Virat");
        Assertions.assertEquals(expectedPlayer, nplayer);
    }

    @Test
    void setPlayer() throws Exception {
        Player player1 = new Player(1, "Player1", "Batsman");
        playerServiceImp.setPlayer(player1);
        verify(playerRepo).save(player1);
    }

    @Test
    void getPlayer() throws Exception {
        Player nplayer = new Player();
        when(playerRepo.findById(11)).thenReturn(Optional.of(nplayer));
        Player expectedPlayer = playerServiceImp.getPlayer(11);
        Assertions.assertEquals(expectedPlayer, nplayer);
    }

}
