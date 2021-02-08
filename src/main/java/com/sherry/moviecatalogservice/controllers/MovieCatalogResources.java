package com.sherry.moviecatalogservice.controllers;

import com.sherry.moviecatalogservice.models.CatalogItem;
import com.sherry.moviecatalogservice.models.Movie;
import com.sherry.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
      RestTemplate restTemplate = new RestTemplate();

        //get all related movie ID'S
        List<Rating> ratings = Arrays.asList(
                new Rating("1234" , 4),
                new Rating("1235" , 3)
        );
        //for each movie ID, get call info service and get details
       return ratings.stream().map(rating -> {
          Movie movie =  restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId() , Movie.class);
           return new CatalogItem(movie.getName() , "see it" ,rating.getRating());
               })
               .collect(Collectors.toList());

        //put them all together
        /*
        in this we create hard coded ratings then we call though RestTemplate the other microservice
        and getting the response the RestTemplate unmarshall the String into required object
         */




    }

}
