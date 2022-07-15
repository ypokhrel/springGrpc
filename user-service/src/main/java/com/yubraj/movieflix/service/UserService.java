package com.yubraj.movieflix.service;

import com.yubraj.movieflix.common.Genre;
import com.yubraj.movieflix.dao.UserRepository;
import com.yubraj.movieflix.entity.User;
import com.yubraj.movieflix.user.UserGenreUpdateRequest;
import com.yubraj.movieflix.user.UserResponse;
import com.yubraj.movieflix.user.UserSearchRequest;
import com.yubraj.movieflix.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    UserRepository repository;

    @Override
    public void getUserGenre(UserSearchRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder builder = UserResponse.newBuilder();
        this.repository.findById(request.getLoginId())
                .ifPresent( user -> {
                    builder.setLoginId(user.getLogin())
                            .setName(user.getName())
                            .setGenre(Genre.valueOf(user.getGenre()));
                });

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void updateUserGenre(UserGenreUpdateRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder builder = UserResponse.newBuilder();
        this.repository.findById(request.getLoginId())
                .ifPresent(user -> {
                    user.setGenre(request.getGenre().toString());
                    builder.setLoginId(user.getLogin())
                            .setName(user.getName())
                            .setGenre(Genre.valueOf(user.getGenre()));
                });

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();

    }
}
