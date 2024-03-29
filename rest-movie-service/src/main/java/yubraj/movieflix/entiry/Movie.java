package yubraj.movieflix.entiry;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@ToString
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    private int id;

    private String title;

    @Column(name="release_year")
    private int year;

    private double rating;
    private String genre;

}
