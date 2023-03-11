package com.cricketGamewithspring.cricketGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication()
@EnableMongoRepositories(basePackages = "com.cricketGamewithspring.cricketGame.Repo.MongoRepo")
@EnableJpaRepositories(basePackages = "com.cricketGamewithspring.cricketGame.Repo.SQLRepo")

public class CricketGameWithSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CricketGameWithSpringApplication.class, args);

    }

}
