package com.yubraj.movieflix.service;

import com.yubraj.movieflix.common.Genre;
import com.yubraj.movieflix.dto.RecommendedMovie;
import com.yubraj.movieflix.dto.UserGenre;
import com.yubraj.movieflix.movie.MovieSearchRequest;
import com.yubraj.movieflix.movie.MovieSearchResponse;
import com.yubraj.movieflix.movie.MovieServiceGrpc.MovieServiceBlockingStub;
import com.yubraj.movieflix.user.UserGenreUpdateRequest;
import com.yubraj.movieflix.user.UserResponse;
import com.yubraj.movieflix.user.UserSearchRequest;
import com.yubraj.movieflix.user.UserServiceGrpc.UserServiceBlockingStub;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMovieService {

  @GrpcClient("user-service")
  private UserServiceBlockingStub userStub;

  @GrpcClient("movie-service")
  private MovieServiceBlockingStub movieStub;

  public List<RecommendedMovie> getRecommendedMovies(String loginId) {
    UserSearchRequest userSearchRequest = UserSearchRequest.newBuilder().setLoginId(loginId).build();
    UserResponse userGenre = userStub.getUserGenre(userSearchRequest);
    MovieSearchRequest movieSearchRequest = MovieSearchRequest.newBuilder().setGenre(userGenre.getGenre()).build();
    MovieSearchResponse movies = movieStub.getMovies(movieSearchRequest);

    List<RecommendedMovie> collect = movies.getMovieList().stream()
            .map(movie -> {
              RecommendedMovie rm = new RecommendedMovie();
              rm.setTitle(movie.getTitle());
              rm.setYear(movie.getYear());
              rm.setRating(movie.getRating());
              return rm;
            })
            .collect(Collectors.toList());

    return collect;
  }

  public void setUserGenre(UserGenre userGenre) {
      UserGenreUpdateRequest userGenreUpdateRequest = UserGenreUpdateRequest.newBuilder().setGenre(Genre.valueOf(userGenre.getGenre().toUpperCase())).setLoginId(userGenre.getLoginId()).build();
      userStub.updateUserGenre(userGenreUpdateRequest);
  }

}
