package com.sherry.moviecatalogservice;

import com.sherry.moviecatalogservice.models.CatalogItem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MovieCatalogServiceApplication {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public List<CatalogItem> getListOfCatalogItems(){
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogServiceApplication.class, args);
    }

}
