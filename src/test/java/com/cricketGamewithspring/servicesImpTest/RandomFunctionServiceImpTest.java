package com.cricketGamewithspring.servicesImpTest;

import com.cricketGamewithspring.cricketGame.servicesImp.RandomFunctionServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = RandomFunctionServiceImp.class)
@ExtendWith(MockitoExtension.class)
public class RandomFunctionServiceImpTest {

    @InjectMocks
    private RandomFunctionServiceImp randomFunctionServiceImp;

    @Test
    void randomFunction() throws Exception {
        // Act
        int result = randomFunctionServiceImp.randomFunction();

        // Assert
        assertTrue(result >= 0 && result <= 7);
    }

}
