package com.cricketGamewithspring.cricketGame.Repo.ESRepo;

import com.cricketGamewithspring.cricketGame.model.Player;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticRepo extends ElasticsearchRepository<Player,Integer> {
}
