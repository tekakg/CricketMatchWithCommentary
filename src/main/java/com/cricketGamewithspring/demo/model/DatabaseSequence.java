package com.cricketGamewithspring.demo.model;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "database_sequence")
public class DatabaseSequence {
    @Id
    private String id;
    private int seq;

    //getters and setters omitted
}
