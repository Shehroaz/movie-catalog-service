package com.sherry.moviecatalogservice.controllers;

import com.sherry.moviecatalogservice.models.CatalogItem;
import com.sherry.moviecatalogservice.models.Movie;
import com.sherry.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private List<CatalogItem> list;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        List<Rating> ratings = Arrays.asList(
                new Rating("1234" , 4),
                new Rating("1235" , 3)
        );

        for (Rating rating : ratings){
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId() , Movie.class);
            list.add(new CatalogItem(movie.getName() , "see on your choice" , rating.getRating()));
        }
        return list;

//       return ratings.stream().map(rating -> {
//          Movie movie =  restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId() , Movie.class);
//           return new CatalogItem(movie.getName() , "see it" ,rating.getRating());
//               })
//               .collect(Collectors.toList());

    }

}
