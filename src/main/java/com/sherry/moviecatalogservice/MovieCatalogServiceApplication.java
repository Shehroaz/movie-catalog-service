package com.sherry.moviecatalogservice;

import com.sherry.moviecatalogservice.models.CatalogItem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogServiceApplication {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public List<CatalogItem> getListOfCatalogItems(){
        return new ArrayList<>();
    }

    @Bean
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogServiceApplication.class, args);
    }

}
