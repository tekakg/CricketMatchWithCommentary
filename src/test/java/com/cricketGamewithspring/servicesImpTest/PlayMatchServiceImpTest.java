package com.cricketGamewithspring.servicesImpTest;

import com.cricketGamewithspring.cricketGame.Repo.MatchRepo;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.servicesImp.PlayMatchServiceImp;
import com.cricketGamewithspring.cricketGame.servicesImp.PlayerServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
@SpringBootTest(classes = PlayMatchServiceImp.class)
@ExtendWith(MockitoExtension.class)
public class PlayMatchServiceImpTest {

    @MockBean
    MatchRepo matchRepo;

    @InjectMocks
    PlayMatchServiceImp playMatchService;

    @Test
    void PlayMatch() {

    }
}
