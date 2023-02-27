package com.cricketGamewithspring.cricketGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@SpringBootApplication
public class CricketGameWithSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CricketGameWithSpringApplication.class, args);

    }

}
