package com.cscib.videorental.core.service;

import com.cscib.videorental.data.model.Movie;
import com.cscib.videorental.data.repository.MovieRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Component
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie saveMovie(Movie movie) throws Exception {
        return Optional.of(movieRepository.save(movie)).orElseThrow(PersistenceException::new);
    }

    public Movie getMovie(String movieId){
        return movieRepository.findById(movieId)
                .orElseThrow(PersistenceException::new);
    }

    public List<Movie> getMovies() {
        return Optional.of(movieRepository.findAll()).orElseThrow(PersistenceException::new);
    }
}
