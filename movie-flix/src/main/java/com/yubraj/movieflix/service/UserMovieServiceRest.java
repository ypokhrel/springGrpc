package com.yubraj.movieflix.service;

import com.yubraj.movieflix.dto.RecommendedMovie;
import com.yubraj.movieflix.dto.UserResponseDto;
import com.yubraj.movieflix.user.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserMovieServiceRest {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rest.user-service-url}")
    String userServiceUrl;

    @Value("${rest.movie-service-url}")
    String movieServiceUrl;


    public List<RecommendedMovie> getRecommendedMovies(String loginId) {

        ResponseEntity<UserResponseDto> response = restTemplate.getForEntity(userServiceUrl + "/user/" + loginId, UserResponseDto.class);
        UserResponseDto responseBody = response.getBody();
        ResponseEntity<RecommendedMovie[]> moviesEntity = restTemplate.getForEntity(movieServiceUrl + "/userMovies/" + responseBody.getGenre().toUpperCase(), RecommendedMovie[].class);
        return Arrays.asList(moviesEntity.getBody());
    }

}
