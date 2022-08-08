package com.yubraj.movieflix.controller;

import com.yubraj.movieflix.dto.UserGenreUpdateRequest;
import com.yubraj.movieflix.dto.UserSearchRequest;
import com.yubraj.movieflix.dto.UserSearchResponse;
import com.yubraj.movieflix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final
    UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user/{loginId}")
    public UserSearchResponse getUserGenre(@PathVariable String loginId) {
        UserSearchRequest request = new UserSearchRequest();
        request.setLogin_id(loginId);
        return service.getUserGenre(request);
    }


    @PutMapping("/user")
    public void updateUserGenre(UserGenreUpdateRequest request) {
        service.updateUserGenre(request);
    }

}
