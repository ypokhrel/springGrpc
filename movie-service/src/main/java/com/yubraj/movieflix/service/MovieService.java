package com.yubraj.movieflix.service;

import com.yubraj.movieflix.dao.MovieRepositiry;
import com.yubraj.movieflix.entiry.Movie;
import com.yubraj.movieflix.movie.MovieDto;
import com.yubraj.movieflix.movie.MovieSearchRequest;
import com.yubraj.movieflix.movie.MovieSearchResponse;
import com.yubraj.movieflix.movie.MovieServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class MovieService extends MovieServiceGrpc.MovieServiceImplBase {

    @Autowired
    MovieRepositiry repositiry;

    @Override
    public void getMovies(MovieSearchRequest request, StreamObserver<MovieSearchResponse> responseObserver) {

    System.out.println(request.getGenre().toString());
        List<Movie> movieList = repositiry.findAllByGenreOrderByYearDesc(request.getGenre().toString());
        List<MovieDto> movieDtos = movieList.stream().map(movie ->
                        MovieDto.newBuilder()
                                .setTitle(movie.getTitle())
                                .setYear(movie.getYear())
                                .setRating(movie.getRating())
                                .build())
                .collect(Collectors.toList());


        responseObserver.onNext(MovieSearchResponse.newBuilder().addAllMovie(movieDtos).build());
        responseObserver.onCompleted();
    }
}
