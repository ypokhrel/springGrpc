package yubraj.movieflix.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import yubraj.movieflix.dto.MovieDto;
import yubraj.movieflix.service.MovieService;

import java.util.List;

@RestController
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/userMovies/{genre}")
    public List<MovieDto> getUserMovieList(@PathVariable String genre) {
        return service.getMovies(genre);
    }

}
