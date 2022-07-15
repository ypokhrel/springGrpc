package com.yubraj.movieflix.controller;


import com.yubraj.movieflix.dto.RecommendedMovie;
import com.yubraj.movieflix.dto.UserGenre;
import com.yubraj.movieflix.service.UserMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    UserMovieService service;

    @GetMapping("/user/{loginId}")
    public List<RecommendedMovie> getMovies(@PathVariable String loginId) {
        return service.getRecommendedMovies(loginId);
    }


    @PutMapping("/user")
    public void setUserGenre(@RequestBody UserGenre userGenre) {
        service.setUserGenre(userGenre);
    }

}
