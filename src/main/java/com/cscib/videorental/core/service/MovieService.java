package com.cscib.videorental.core.service;

import com.cscib.videorental.data.model.Movie;
import com.cscib.videorental.data.repositories.MovieRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie saveMovie(Movie movie){
        return null;
    }

    public Movie getMovie(String movieId){
        return null;
    }


    public List<Movie> getMovies() {

        return null;
    }
}
