package com.yubraj.movieflix.dao;

import com.yubraj.movieflix.entiry.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepositiry extends JpaRepository<Movie, Integer> {

    List<Movie> findAllByGenreOrderByYearDesc(String genre);

}
