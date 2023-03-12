package com.cricketGamewithspring.servicesImpTest;

import com.cricketGamewithspring.cricketGame.model.DatabaseSequence;
import com.cricketGamewithspring.cricketGame.servicesImp.SequenceGeneratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UpdateDefinition;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SequenceGeneratorService.class)
@ExtendWith(MockitoExtension.class)
public class SequenceGeneratorServiceTest {
    @InjectMocks
    private SequenceGeneratorService sequenceGeneratorService;

    @MockBean
    private MongoOperations mongoOperations;

    @Test
    void generateSequence() {
        // Given
        String seqName = "database_sequence";
        DatabaseSequence counter = new DatabaseSequence(seqName, 1);
        when(mongoOperations.findAndModify((Query) any(), (UpdateDefinition) any(), (Class<Object>) any(), any())).thenReturn(
                counter);

    }
}

