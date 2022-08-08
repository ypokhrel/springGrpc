package com.yubraj.movieflix.service;

import com.yubraj.movieflix.dao.UserRepository;
import com.yubraj.movieflix.dto.UserGenreUpdateRequest;
import com.yubraj.movieflix.dto.UserSearchRequest;
import com.yubraj.movieflix.dto.UserSearchResponse;
import com.yubraj.movieflix.enums.Genre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService{

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserSearchResponse getUserGenre(UserSearchRequest request) {
        UserSearchResponse response = new UserSearchResponse();
        this.repository.findById(request.getLogin_id())
                .ifPresent( user -> {
                    response.setLoginId(user.getLogin());
                     response.setName(user.getName());
                     response.setGenre(Genre.valueOf(user.getGenre()));
                });

        return response;
    }

    @Transactional
    public void updateUserGenre(UserGenreUpdateRequest request) {
        UserSearchResponse response = new UserSearchResponse();
        this.repository.findById(request.getLoginId())
                .ifPresent(user -> {
                    user.setGenre(request.getGenre().toString());
                            response.setLoginId(user.getLogin());
                            response.setName(user.getName());
                            response.setGenre(Genre.valueOf(user.getGenre()));
                });

    }
}
