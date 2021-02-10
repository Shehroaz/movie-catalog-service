package com.sherry.moviecatalogservice.controllers;

import com.sherry.moviecatalogservice.models.CatalogItem;
import com.sherry.moviecatalogservice.models.Movie;
import com.sherry.moviecatalogservice.models.Rating;
import com.sherry.moviecatalogservice.models.UserRating;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {
    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsData/users/" + userId , UserRating.class);
        List<Rating> list = userRating.getUserRatings();
        for(Rating rating : list){
            System.out.println(rating.getMovieId());
        }


        return userRating.getUserRatings().stream().map(rating -> {
          Movie movie =  restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId() , Movie.class);
            return new CatalogItem(movie.getName(), "see this movie", rating.getRating());

        })
                .collect(Collectors.toList());

    }

}
