package com.cricketGamewithspring.cricketGame;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@ComponentScan
@EnableElasticsearchRepositories(basePackages = "com.cricketGamewithspring.cricketGame.Repo.ESRepo")
public class ElasticsearchClientConfig {
    @Bean
    public RestClient getClient() {
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http"))
                .build();
        return restClient;
    }

    @Bean
    public ElasticsearchTransport getElasticsearchTransport() {
        return new RestClientTransport(getClient(), new JacksonJsonpMapper());
    }

    @Bean
    public ElasticsearchClient getElasticsearchClient() {
        ElasticsearchClient elasticsearchClient = new ElasticsearchClient(getElasticsearchTransport());
        return elasticsearchClient;
    }
}
