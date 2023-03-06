package com.cricketGamewithspring.servicesImpTest;

import com.cricketGamewithspring.cricketGame.Repo.MatchRepo;
import com.cricketGamewithspring.cricketGame.servicesImp.MatchDetailsServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = MatchDetailsServiceImp.class)
@ExtendWith(MockitoExtension.class)
public class MatchDetailsServiceImpTest {

    @InjectMocks
    MatchDetailsServiceImp matchDetailsServiceImp;

    @MockBean
    MatchRepo matchRepo;

    @Test
    void getMatch() throws Exception {
        matchDetailsServiceImp.getMatch(11);
        verify(matchRepo).findById(11);
    }

}
