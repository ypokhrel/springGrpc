package com.yubraj.movieflix.dto;

import com.yubraj.movieflix.enums.Genre;
import lombok.Data;

@Data
public class UserGenreUpdateRequest {
    String loginId;
    Genre genre;
}
