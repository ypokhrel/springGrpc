package com.yubraj.movieflix.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserResponseDto {
    private String loginId;
    private String name;
    private String genre;
}
