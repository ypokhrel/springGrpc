package yubraj.movieflix.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yubraj.movieflix.entiry.Movie;

import java.util.List;

@Repository
public interface MovieRepositiry extends JpaRepository<Movie, Integer> {

    List<Movie> findAllByGenreOrderByYearDesc(String genre);

}
