package yubraj.movieflix.service;

import org.springframework.stereotype.Service;
import yubraj.movieflix.dao.MovieRepositiry;
import yubraj.movieflix.dto.MovieDto;
import yubraj.movieflix.entiry.Movie;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepositiry repository;

    public MovieService(MovieRepositiry repository) {
        this.repository = repository;
    }


    public List<MovieDto> getMovies(String genre) {

        List<Movie> movieList = repository.findAllByGenreOrderByYearDesc(genre);

        return movieList.stream()
                .map(movie -> {
                    MovieDto dto = new MovieDto();
                    dto.setTitle(movie.getTitle());
                    dto.setYear(movie.getYear());
                    dto.setRating(movie.getRating());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
